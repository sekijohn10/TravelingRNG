package edu.byui.joh18113.travelingrng

import java.lang.System.currentTimeMillis
import kotlin.random.Random

object Rand {

    private val supplied: SupRand = SupRand
    private var custom: CustRand? = null
    private val ran : Random = Random(currentTimeMillis())


    fun getRand(a: Int, b: Int): Int {
        return when(Settings.usingRand) {
            1 -> ran.nextInt(a, b + 1)
            2 -> supplied.getRand(a, b)
            3 -> custom!!.getRand(a, b)
            else -> ran.nextInt(a, b + 1)
        }
    }




}