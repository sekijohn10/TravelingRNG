package edu.byui.joh18113.travelingrng

import java.lang.System.currentTimeMillis
import kotlin.random.Random

object SupRand {
    var seed1 = currentTimeMillis().shl(1)
    var seed2 = currentTimeMillis().ushr(32)
    var seed3 = currentTimeMillis().inv()
    var seed4 = currentTimeMillis().xor(362437)
    var seed5 = Random(currentTimeMillis().inv()).nextLong()

    fun getRand(a: Int, b: Int): Int {
        val t = seed5
        seed5 = seed4
        seed4 = seed3
        seed3 = seed2
        seed2 = seed1
        seed1 = t

        val n = b - a
        seed1 = seed1.shr(1).xor(currentTimeMillis()) + 362437
        return if (((seed1 % (n + 1L)) + a) >= a) {
            ((seed1 % (n + 1L)) + a).toInt()
        } else {
            getRand(a, b)
        }
    }
}
