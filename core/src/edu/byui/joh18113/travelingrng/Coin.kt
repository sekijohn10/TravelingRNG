package edu.byui.joh18113.travelingrng


class Coin {
    fun flipCoin() : String{
        return  if(Rand.getRand(0, 1) == 1) "FlipHeads" else "FlipTails"
    }
}