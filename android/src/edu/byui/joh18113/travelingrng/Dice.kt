package edu.byui.joh18113.travelingrng

interface Dice {
    fun roll()
    val sides: Int
    var currentSide: Int
}