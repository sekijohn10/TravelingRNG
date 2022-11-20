package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.Model

object Assets {


    val diceTexture: Texture = Texture(Gdx.files.internal("Text/RollDice.png"))
    val settingTexture: Texture = Texture(Gdx.files.internal("Text/Setting.png"))
    val deckTexture: Texture = Texture(Gdx.files.internal("Text/DrawCard.png"))
    val counterTexture: Texture = Texture(Gdx.files.internal("Text/NumberCounter1.png"))
    val coinTexture: Texture = Texture(Gdx.files.internal("Text/FlipACoin1.png"))
    val models: AssetManager = AssetManager()

    init {
        models.load("Coin/PirateCoin.g3db", Model().javaClass)
        models.load("Deck/cards.g3db", Model().javaClass)
        models.load("Dice6/dice.g3db", Model().javaClass)
    }

    fun dispose() {
        models.dispose()
        diceTexture.dispose()
        settingTexture.dispose()
        deckTexture.dispose()
        counterTexture.dispose()
        coinTexture.dispose()
    }
}

