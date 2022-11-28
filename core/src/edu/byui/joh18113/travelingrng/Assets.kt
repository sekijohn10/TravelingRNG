package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.Model

object Assets {

    val diceTexture: Texture = Texture(Gdx.files.internal("Text/RollDice.png"))
    val settingTexture: Texture = Texture(Gdx.files.internal("Text/Settings.png"))
    val deckTexture: Texture = Texture(Gdx.files.internal("Text/DrawCards.png"))
    val counterTexture: Texture = Texture(Gdx.files.internal("Text/Counter.png"))
    val coinTexture: Texture = Texture(Gdx.files.internal("Text/FlipCoins.png"))
    val titleTexture: Texture = Texture(Gdx.files.internal("Text/TravelingRNG.png"))
    val models: AssetManager = AssetManager()

    init {
        models.load("Models.g3db", Model().javaClass)
    }

    fun dispose() {
        models.dispose()
        diceTexture.dispose()
        settingTexture.dispose()
        deckTexture.dispose()
        counterTexture.dispose()
        coinTexture.dispose()
        titleTexture.dispose()
    }
}

