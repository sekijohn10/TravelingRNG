package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils

class MainMenuScreen(val game: Main) : Screen {
    var camera : OrthographicCamera = OrthographicCamera()
    init {
        camera.setToOrtho(false)
    }

    override fun show() {
        //TODO("Not yet implemented")
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)

        camera.update()
        game.batch?.projectionMatrix ?: camera.combined
        game.batch?.begin()
        game.font?.draw(game.batch, "Welcome", 100f, 150f)
        game.font?.draw(game.batch, "Tap anywhere to begin!", 100f, 100f)
        game.batch?.end()

        if (Gdx.input.isTouched){
            game.screen = CounterScreen(game)
            dispose()
        }
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
        //TODO("Not yet implemented")
    }

}

