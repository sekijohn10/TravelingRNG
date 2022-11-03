package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils

class CounterScreen(val game: Main) : Screen {
    var camera : OrthographicCamera = OrthographicCamera()
    init {
        camera.setToOrtho(false)
    }

    override fun show() {
        //TODO("Not yet implemented")
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0.4f, 0.5f, 0f, 1f)

        camera.update()
        game.batch?.projectionMatrix ?: camera.combined
        game.batch?.begin()
        game.font?.draw(game.batch, "Welcome", 100f, 150f)
        game.font?.draw(game.batch, "Tap anywhere to quit!", 100f, 100f)
        game.batch?.end()

        if (Gdx.input.isTouched){
            game.screen = MainMenuScreen(game)
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
