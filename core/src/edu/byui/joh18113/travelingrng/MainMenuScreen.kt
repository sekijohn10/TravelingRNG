package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3


class MainMenuScreen(val game: Main) : Screen {
    var camera: OrthographicCamera? = null
    var environment: Environment? = null
    val instances: ArrayList<ModelInstance> = ArrayList()
    var loading: Boolean? = null
    var diceBound: Rectangle? = null
    var coinBound: Rectangle? = null
    var deckBound: Rectangle? = null
    var counterBound: Rectangle? = null
    var settingsBound: Rectangle? = null
    var touchLocation: Vector3? = null

    override fun show() {
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera?.update()

        environment = Environment()
        environment?.set(ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.2f, -0.8f, -0.2f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, 1.2f, 0.8f, 0.2f))

        loading = true
        diceBound = Rectangle(
            (-(Gdx.graphics.width.toFloat())/2f) - 5f, ((Gdx.graphics.height.toFloat())/2f) -155f,
            Assets.diceTexture.width.toFloat() + 10f,
            Assets.diceTexture.height.toFloat() + 10f
        )
        /*coinBound = Rectangle(
            0f,
            diceBound!!.height.toFloat(),
            Assets.coinTexture.width.toFloat() + 10f,
            Assets.coinTexture.height.toFloat() + 10f
        )*/
        touchLocation = Vector3()

    }

    private fun load() {
        loading = false
    }

    private fun handleInput() {
        if (Gdx.input.justTouched()) {
            camera?.unproject(
                touchLocation?.set(
                    Gdx.input.getX().toFloat(), Gdx.input.getY().toFloat(), 0f
                )
            );

            if (diceBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                game.setScreen(DiceScreen(game))
                return
            }
            /*if (coinBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                game.setScreen(CoinScreen(game))
                return
            }
            if (counterBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                 game.setScreen(CounterScreen(game))
                 return
             }
             if (settingsBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                 game.setScreen(SettingScreen(game))
                 return
             }
             if (deckBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                 game.setScreen(DeckScreen(game))
                 return
             }*/
        }
    }

    override fun render(delta: Float) {
        if (loading!! && Assets.models.update()) {
            load()
        }
        handleInput()
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        game.sBatch?.setProjectionMatrix(camera?.combined)
        game.sBatch?.begin()
        game.sBatch?.draw(
            Assets.diceTexture, -(Gdx.graphics.width.toFloat())/2f, ((Gdx.graphics.height.toFloat())/2f) -150f
        )
        /*game.sBatch?.draw(
            Assets.coinTexture, 5f, 5f
        )*/
        game.sBatch?.end()
    }


    override fun resize(width: Int, height: Int) {
        //TODO("Not yet implemented")
    }

    override fun pause() {
        //TODO("Not yet implemented")
    }

    override fun resume() {
        //TODO("Not yet implemented")
    }

    override fun hide() {
        //TODO("Not yet implemented")
    }

    override fun dispose() {
        instances.clear()
    }

}

