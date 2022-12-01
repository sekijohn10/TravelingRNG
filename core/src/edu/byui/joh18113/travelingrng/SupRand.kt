package edu.byui.joh18113.travelingrng

import java.lang.System.currentTimeMillis
import kotlin.random.Random

object SupRand {
    var seed1: Int = currentTimeMillis().toInt()
    var seed2: Int = currentTimeMillis().ushr(32).toInt()
    var seed3: Int = currentTimeMillis().toInt().inv()
    var seed4: Int = currentTimeMillis().toInt().xor(362437)
    var seed5: Int = Random(currentTimeMillis().inv()).nextInt()

    fun getRand(a: Int, b: Int): Int {
        val t = seed5
        seed5 = seed4
        seed4 = seed3
        seed3 = seed2
        seed2 = seed1
        seed1 = t

        val n = b - a
        seed1 = seed1.shl(1).xor(currentTimeMillis().toInt()) + 362437
        return if (((seed1 % (n + 1L)) + a) >= a) {
            (seed1 % (n + 1)) + a
        } else {
            getRand(a, b)
        }
    }
}
