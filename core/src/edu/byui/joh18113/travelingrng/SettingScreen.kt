package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator


class SettingScreen(val game: Main) : Screen {
    private var camera: OrthographicCamera? = null

    private var numDiceBound: Rectangle? = null
    private var numCoinsBound: Rectangle? = null
    private var randomBound: Rectangle? = null

    private var touchLocation: Vector3? = null

    private var generator: FreeTypeFontGenerator? = null
    private var parameter: FreeTypeFontGenerator.FreeTypeFontParameter? = null
    private var fontWhite: BitmapFont? = null
    private var fontRed: BitmapFont? = null
    private var fontGreen: BitmapFont? = null
    private var fontCyan: BitmapFont? = null

    override fun show() {
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera?.update()

        numDiceBound = Rectangle(
            0f, ((Gdx.graphics.height.toFloat()) / 2f) - 150f, 200f, 40f
        )
        numCoinsBound = Rectangle(
            0f, numDiceBound!!.y - 100f, 200f, 40f
        )
        randomBound = Rectangle(
            -50f, numCoinsBound!!.y - 100f, 500f, 40f
        )

        touchLocation = Vector3()

        generator = FreeTypeFontGenerator(Gdx.files.internal("Text/MyFont.TTF"))
        parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter?.size = 36
        parameter?.color = Color.RED
        fontRed = generator?.generateFont(parameter)
        parameter?.color = Color.GREEN
        fontGreen = generator?.generateFont(parameter)
        parameter?.color = Color.WHITE
        fontWhite = generator?.generateFont(parameter)
        parameter?.color = Color.CYAN
        fontCyan = generator?.generateFont(parameter)
    }

    private fun handleInput() {
        if (Gdx.input.justTouched()) {
            camera?.unproject(
                touchLocation?.set(
                    Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f
                )
            )

            if (numDiceBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                if (touchLocation!!.x < (numDiceBound!!.x + numDiceBound!!.width / 2) && Settings.numDice > 1) Settings.numDice -= 1
                else if (touchLocation!!.x > (numDiceBound!!.x + numDiceBound!!.width / 2)) Settings.numDice += 1
            }
            if (numCoinsBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                if (touchLocation!!.x < (numCoinsBound!!.x + numCoinsBound!!.width / 2) && Settings.numCoin > 1) Settings.numCoin -= 1
                else if (touchLocation!!.x > (numCoinsBound!!.x + numCoinsBound!!.width / 2)) Settings.numCoin += 1
            }
            if (randomBound!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                if (touchLocation!!.x < (randomBound!!.x + randomBound!!.width / 2)) Settings.usingRand =
                    1
                else if (touchLocation!!.x > (randomBound!!.x + randomBound!!.width / 2)) Settings.usingRand =
                    2
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K)) game.screen = MainMenuScreen(game)
    }

    override fun render(delta: Float) {
        handleInput()
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        game.sBatch?.projectionMatrix = camera?.combined
        game.sBatch?.begin()
        fontWhite?.draw(
            game.sBatch,
            "Number of Dice",
            -300f + 2.5f,
            numDiceBound!!.y + numDiceBound!!.height - 2f
        )
        fontRed?.draw(
            game.sBatch, "Less", numDiceBound!!.x, numDiceBound!!.y + numDiceBound!!.height - 2f
        )
        fontGreen?.draw(
            game.sBatch,
            "More",
            numDiceBound!!.x + numDiceBound!!.width / 2,
            numDiceBound!!.y + numDiceBound!!.height - 2f
        )
        fontCyan?.draw(
            game.sBatch,
            Settings.numDice.toString(),
            numDiceBound!!.x + numDiceBound!!.width,
            numDiceBound!!.y + numDiceBound!!.height - 2f
        )
        fontWhite?.draw(
            game.sBatch,
            "Number of Coins",
            -300f + 2.5f,
            numCoinsBound!!.y + numCoinsBound!!.height - 2f
        )
        fontRed?.draw(
            game.sBatch, "Less", numCoinsBound!!.x, numCoinsBound!!.y + numCoinsBound!!.height - 2f
        )
        fontGreen?.draw(
            game.sBatch,
            "More",
            numCoinsBound!!.x + numCoinsBound!!.width / 2,
            numCoinsBound!!.y + numCoinsBound!!.height - 2f
        )
        fontCyan?.draw(
            game.sBatch,
            Settings.numCoin.toString(),
            numCoinsBound!!.x + numCoinsBound!!.width,
            numCoinsBound!!.y + numCoinsBound!!.height - 2f
        )
        fontWhite?.draw(
            game.sBatch,
            "Which random generator",
            -500f + 2.5f,
            randomBound!!.y + randomBound!!.height - 2f
        )
        fontRed?.draw(
            game.sBatch,
            "System_Default",
            randomBound!!.x,
            randomBound!!.y + randomBound!!.height - 2f
        )
        fontGreen?.draw(
            game.sBatch,
            "TravelingRNG",
            randomBound!!.x + randomBound!!.width / 2,
            randomBound!!.y + randomBound!!.height - 2f
        )
        fontCyan?.draw(
            game.sBatch,
            if (Settings.usingRand == 1) "System_Default" else "TravelingRNG",
            randomBound!!.x - 25f,
            randomBound!!.y + randomBound!!.height - 2f - 50f
        )
        fontCyan?.draw(
            game.sBatch,
            "Press 'K' to go back to Main Menu on any screen",
            -500f + 2.5f,
            randomBound!!.y - 100f
        )
        fontCyan?.draw(
            game.sBatch,
            "Press 'SPACE' for roll, flip, and draw actions",
            -500f + 2.5f,
            randomBound!!.y - 200f
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
        generator?.dispose()
    }

}
