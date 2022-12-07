package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3


class CounterScreen(val game: Main) : Screen {
    private var camera: OrthographicCamera? = null

    private var titleBound: Rectangle? = null
    private var counterBound1: Rectangle? = null
    private var incrementBound1: Rectangle? = null
    private var decrementBound1: Rectangle? = null
    private var counterBound2: Rectangle? = null
    private var incrementBound2: Rectangle? = null
    private var decrementBound2: Rectangle? = null
    private var resetBound1: Rectangle? = null
    private var resetBound2: Rectangle? = null

    private var touchLocation: Vector3? = null

    private var generator: FreeTypeFontGenerator? = null
    private var parameter: FreeTypeFontGenerator.FreeTypeFontParameter? = null
    private var fontRed: BitmapFont? = null
    private var fontGreen: BitmapFont? = null
    private var fontWhite: BitmapFont? = null
    private var fontCyan: BitmapFont? = null

    private var count1 = Counter(0)
    private var count2 = Counter(0)


    override fun show() {
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera?.update()

        titleBound = Rectangle(
            -310f,
            ((Gdx.graphics.height.toFloat()) / 2f) - 155f,
            Assets.counterTexture.width.toFloat(),
            Assets.counterTexture.height.toFloat()
        )
        counterBound1 = Rectangle(
            -125f, titleBound!!.y - 120f, 150f, 70f
        )
        incrementBound1 = Rectangle(
            30f, counterBound1!!.y - 80f, 410f, 70f
        )
        decrementBound1 = Rectangle(
            -430f, incrementBound1!!.y, 410f, 70f
        )
        counterBound2 = Rectangle(
            -210f, decrementBound1!!.y - 200f, 150f, 70f
        )
        incrementBound2 = Rectangle(
            30f, counterBound2!!.y - 80f, 410f, 70f
        )
        decrementBound2 = Rectangle(
            -430f, incrementBound2!!.y, 410f, 70f
        )
        resetBound1 = Rectangle(
            -(Gdx.graphics.width.toFloat()) / 2f, counterBound1!!.y, 300f, 70f
        )
        resetBound2 = Rectangle(
            -(Gdx.graphics.width.toFloat()) / 2f, counterBound2!!.y, 375f, 70f
        )

        touchLocation = Vector3()

        generator = FreeTypeFontGenerator(Gdx.files.internal("Text/MyFont.TTF"))
        parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter?.size = 70
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
            if (resetBound1!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                count1.reset()
            }
            if (resetBound2!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                count2.reset()
            }


            if (incrementBound1!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                val widthInterval = incrementBound1!!.width / 16
                if (touchLocation!!.x <= (incrementBound1!!.x + widthInterval * 1.5)) count1.add(1)
                else if (touchLocation!!.x > (incrementBound1!!.x + widthInterval * 1.5) && touchLocation!!.x <= (incrementBound1!!.x + widthInterval * 4)) count1.add(
                    5
                )
                else if (touchLocation!!.x > (incrementBound1!!.x + widthInterval * 4) && touchLocation!!.x <= (incrementBound1!!.x + widthInterval * 8)) count1.add(
                    10
                )
                else if (touchLocation!!.x > (incrementBound1!!.x + widthInterval * 8) && touchLocation!!.x <= (incrementBound1!!.x + widthInterval * 12.5)) count1.add(
                    20
                )
                else if (touchLocation!!.x > (incrementBound1!!.x + widthInterval * 12.5)) count1.add(
                    50
                )
            }
            if (decrementBound1!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                val widthInterval = decrementBound1!!.width / 16
                if (touchLocation!!.x <= (decrementBound1!!.x + widthInterval * 3.5)) count1.sub(50)
                else if (touchLocation!!.x > (decrementBound1!!.x + widthInterval * 3.5) && touchLocation!!.x <= (decrementBound1!!.x + widthInterval * 8)) count1.sub(
                    20
                )
                else if (touchLocation!!.x > (decrementBound1!!.x + widthInterval * 8) && touchLocation!!.x <= (decrementBound1!!.x + widthInterval * 12)) count1.sub(
                    10
                )
                else if (touchLocation!!.x > (decrementBound1!!.x + widthInterval * 12) && touchLocation!!.x <= (decrementBound1!!.x + widthInterval * 14)) count1.sub(
                    5
                )
                else if (touchLocation!!.x > (decrementBound1!!.x + widthInterval * 14)) count1.sub(
                    1
                )
            }
            if (incrementBound2!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                val widthInterval = incrementBound2!!.width / 16
                if (touchLocation!!.x <= (incrementBound2!!.x + widthInterval * 1.5)) count2.add(1)
                else if (touchLocation!!.x > (incrementBound2!!.x + widthInterval * 1.5) && touchLocation!!.x <= (incrementBound2!!.x + widthInterval * 4)) count2.add(
                    5
                )
                else if (touchLocation!!.x > (incrementBound2!!.x + widthInterval * 4) && touchLocation!!.x <= (incrementBound2!!.x + widthInterval * 8)) count2.add(
                    10
                )
                else if (touchLocation!!.x > (incrementBound2!!.x + widthInterval * 8) && touchLocation!!.x <= (incrementBound2!!.x + widthInterval * 12.5)) count2.add(
                    20
                )
                else if (touchLocation!!.x > (incrementBound2!!.x + widthInterval * 12.5)) count2.add(
                    50
                )
            }
            if (decrementBound2!!.contains(touchLocation!!.x, touchLocation!!.y)) {
                val widthInterval = decrementBound2!!.width / 16
                if (touchLocation!!.x <= (decrementBound2!!.x + widthInterval * 3.5)) count2.sub(50)
                else if (touchLocation!!.x > (decrementBound2!!.x + widthInterval * 3.5) && touchLocation!!.x <= (decrementBound2!!.x + widthInterval * 8)) count2.sub(
                    20
                )
                else if (touchLocation!!.x > (decrementBound2!!.x + widthInterval * 8) && touchLocation!!.x <= (decrementBound2!!.x + widthInterval * 12)) count2.sub(
                    10
                )
                else if (touchLocation!!.x > (decrementBound2!!.x + widthInterval * 12) && touchLocation!!.x <= (decrementBound2!!.x + widthInterval * 14)) count2.sub(
                    5
                )
                else if (touchLocation!!.x > (decrementBound2!!.x + widthInterval * 14)) count2.sub(
                    1
                )
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
        game.sBatch?.draw(
            Assets.counterTexture, titleBound!!.x, titleBound!!.y
        )
        fontWhite?.draw(
            game.sBatch,
            "First   ${count1.count}",
            counterBound1!!.x,
            counterBound1!!.y + counterBound1!!.height
        )
        fontGreen?.draw(
            game.sBatch,
            "1  5  10  20  50",
            incrementBound1!!.x,
            incrementBound1!!.y + incrementBound1!!.height
        )
        fontRed?.draw(
            game.sBatch,
            "50  20  10  5  1",
            decrementBound1!!.x,
            decrementBound1!!.y + decrementBound1!!.height
        )
        fontWhite?.draw(
            game.sBatch,
            "Second   ${count2.count}",
            counterBound2!!.x,
            counterBound2!!.y + counterBound2!!.height
        )
        fontGreen?.draw(
            game.sBatch,
            "1  5  10  20  50",
            incrementBound2!!.x,
            incrementBound2!!.y + incrementBound2!!.height
        )
        fontRed?.draw(
            game.sBatch,
            "50  20  10  5  1",
            decrementBound2!!.x,
            decrementBound2!!.y + decrementBound2!!.height
        )
        fontCyan?.draw(
            game.sBatch, "Reset First", resetBound1!!.x, resetBound1!!.y + resetBound1!!.height
        )
        fontCyan?.draw(
            game.sBatch, "Reset Second", resetBound2!!.x, resetBound2!!.y + resetBound2!!.height
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
