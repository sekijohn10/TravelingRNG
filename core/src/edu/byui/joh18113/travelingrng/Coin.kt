package edu.byui.joh18113.travelingrng


class Coin {
    private var value : Boolean = Rand.getRand(0, 1) == 1
    fun flipCoin() : Boolean{
        //TODO
        value = (Rand.getRand(0, 1) == 1)
        return value
    }
}