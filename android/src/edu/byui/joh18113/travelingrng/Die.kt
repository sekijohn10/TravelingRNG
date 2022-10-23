package edu.byui.joh18113.travelingrng

class Die(override val sides: Int, override var currentSide: Int) : Dice {
    override fun roll() {
        currentSide = Rand.getRand(1, sides)
    }
}