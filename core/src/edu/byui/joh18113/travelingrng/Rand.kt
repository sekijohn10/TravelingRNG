package edu.byui.joh18113.travelingrng

import java.lang.System.currentTimeMillis
import kotlin.random.Random

object Rand {

    private val supplied: SupRand = SupRand
    private var custom: CustRand? = null
    private val ran : Random = Random(currentTimeMillis())


    fun getRand(a: Int, b: Int, choice: Int? = null): Int {
        if (choice != null) {
            return if (choice == 1) {
                SupRand.getRand(a, b)
            } else if (choice == 2 && custom != null) {
                custom!!.getRand(a, b)
            } else {
                ran.nextInt(a, b + 1)
            }
        }
        return ran.nextInt(a, b + 1)
    }

    fun getRand(a: Long, b: Long, choice: Int? = null): Long {
        if (choice != null) {
            return if (choice == 1) {
                SupRand.getRand(a, b)
            } else if (choice == 2 && custom != null) {
                custom!!.getRand(a, b)
            } else {
                ran.nextLong(a, b + 1)
            }
        }
        return ran.nextLong(a, b + 1)

    }


}