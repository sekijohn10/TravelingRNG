package edu.byui.joh18113.travelingrng


class Die(override val sides: Int) : Dice {
    override fun roll(): String {
        return "Roll" + (Rand.getRand(1, sides)).toString()
        }
}