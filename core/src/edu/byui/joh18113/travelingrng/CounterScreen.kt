package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3


class CounterScreen(val game: Main) : Screen {
    var camera: OrthographicCamera? = null
    var titleBound : Rectangle? = null
    var diceBound: Rectangle? = null
    var coinBound: Rectangle? = null
    var deckBound: Rectangle? = null
    var counterBound: Rectangle? = null
    var settingsBound: Rectangle? = null
    var touchLocation: Vector3? = null

    override fun show() {
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera?.update()

        titleBound = Rectangle(
            (-(Gdx.graphics.width.toFloat()) / 2f) - 5f,
            ((Gdx.graphics.height.toFloat()) / 2f) - 175f,
            Assets.titleTexture.width.toFloat() + 10f,
            Assets.titleTexture.height.toFloat() + 10f
        )
        diceBound = Rectangle(
            (-(Gdx.graphics.width.toFloat()) / 2f) - 5f,
            titleBound!!.y -155f,
            Assets.diceTexture.width.toFloat() + 10f,
            Assets.diceTexture.height.toFloat() + 10f
        )
        coinBound = Rectangle(
            (-(Gdx.graphics.width.toFloat()) / 2f) - 5f,
            diceBound!!.y - 155f,
            Assets.coinTexture.width.toFloat() + 10f,
            Assets.coinTexture.height.toFloat() + 10f
        )
        deckBound = Rectangle(
            (-(Gdx.graphics.width.toFloat()) / 2f) - 5f,
            coinBound!!.y - 155f,
            Assets.coinTexture.width.toFloat() + 10f,
            Assets.coinTexture.height.toFloat() + 10f
        )
        counterBound = Rectangle(
            (-(Gdx.graphics.width.toFloat()) / 2f) - 5f,
            deckBound!!.y - 155f,
            Assets.coinTexture.width.toFloat() + 10f,
            Assets.coinTexture.height.toFloat() + 10f
        )
        settingsBound = Rectangle(
            (-(Gdx.graphics.width.toFloat()) / 2f) - 5f,
            counterBound!!.y - 155f,
            Assets.coinTexture.width.toFloat() + 10f,
            Assets.coinTexture.height.toFloat() + 10f
        )
        touchLocation = Vector3()

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
            if (coinBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
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
            }
        }
    }

    override fun render(delta: Float) {
        handleInput()
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        game.sBatch?.setProjectionMatrix(camera?.combined)
        game.sBatch?.begin()
        game.sBatch?.draw(
            Assets.titleTexture,
            titleBound!!.x + 2.5f,
            titleBound!!.y + 2.5f
        )
        game.sBatch?.draw(
            Assets.diceTexture,
            diceBound!!.x + 2.5f,
            diceBound!!.y + 2.5f
        )
        game.sBatch?.draw(
            Assets.coinTexture,
            coinBound!!.x + 2.5f,
            coinBound!!.y + 2.5f
        )
        game.sBatch?.draw(
            Assets.deckTexture,
            deckBound!!.x + 2.5f,
            deckBound!!.y + 2.5f
        )
        game.sBatch?.draw(
            Assets.counterTexture,
            counterBound!!.x + 2.5f,
            counterBound!!.y + 2.5f
        )
        game.sBatch?.draw(
            Assets.settingTexture,
            settingsBound!!.x + 2.5f,
            settingsBound!!.y + 2.5f
        )
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

    }

}
