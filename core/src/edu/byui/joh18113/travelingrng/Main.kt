package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g3d.ModelBatch

class Main : Game() {
    var font: BitmapFont? = null
    var mBatch : ModelBatch? = null

    override fun create() {
        font = BitmapFont()
        mBatch = ModelBatch()
        this.setScreen(MainMenuScreen(this))
    }

    override fun dispose() {
        font!!.dispose()
        mBatch!!.dispose()
        super.dispose()
    }
}