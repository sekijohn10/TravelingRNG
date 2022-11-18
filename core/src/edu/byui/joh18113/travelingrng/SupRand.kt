package edu.byui.joh18113.travelingrng

import java.lang.System.currentTimeMillis


object SupRand {
    var seedL: Long = currentTimeMillis()
    var seedI: Int = currentTimeMillis().toInt()

    fun getRand(a: Int, b: Int): Int {
        val n = b - a
        seedI = seedI.xor(currentTimeMillis().toInt().shl(1)) + 11
        return if((seedI % (n + 1L)) + a >= a) {(seedI % (n + 1)) + a} else {
            getRand(a,b)
        }
    }

    fun getRand(a: Long, b: Long): Long {
        val n = b - a
        seedL = seedL.xor(currentTimeMillis().shl(1)) + 11L
        return if((seedL % (n + 1L)) + a >= a) {(seedL % (n + 1L)) + a} else {
            getRand(a,b)
        }
    }
}
