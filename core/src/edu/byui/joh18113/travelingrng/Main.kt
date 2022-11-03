package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class Main : Game() {
    var batch: SpriteBatch? = null
    var font: BitmapFont? = null

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        this.setScreen(MainMenuScreen(this))
    }

    override fun dispose() {
        batch!!.dispose()
        font!!.dispose()
    }
}