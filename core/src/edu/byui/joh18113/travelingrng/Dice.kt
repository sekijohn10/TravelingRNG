package edu.byui.joh18113.travelingrng

interface Dice {
    fun roll() : String
    val sides: Int
    var currentSide: Int
}