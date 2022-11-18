package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder

class Die(override val sides: Int, override var currentSide: Int) : Dice {
    override fun roll() {
        currentSide = Rand.getRand(1, sides)
    }

}