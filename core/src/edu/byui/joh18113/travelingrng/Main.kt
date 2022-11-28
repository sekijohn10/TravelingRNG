package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g3d.ModelBatch

class Main : Game() {
    var font: BitmapFont? = null
    var mBatch : ModelBatch? = null
    var sBatch : SpriteBatch? = null
    val assets = Assets

    override fun create() {
        font = BitmapFont()
        mBatch = ModelBatch()
        sBatch = SpriteBatch()
        this.setScreen(MainMenuScreen(this))
    }

    override fun dispose() {
        font!!.dispose()
        mBatch!!.dispose()
        sBatch!!.dispose()
        assets.dispose()
        super.dispose()
    }
}