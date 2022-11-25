package edu.byui.joh18113.travelingrng


class Coin {
    private var value : Boolean = Rand.getRand(0, 1) == 1
    fun flipCoin() : String{
        value = (Rand.getRand(0, 1) == 1)
        return  if(value) "FlipHeads" else "FlipTails"
    }
}