package edu.byui.joh18113.travelingrng

data class Counter(var count: Int = 0) {
    fun add(a: Int = 1) {
        count += a
    }

    fun sub(a: Int = 1) {
        count -= a
    }

    fun reset(a: Int = 0) {
        count = a
    }
}