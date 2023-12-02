@file:JvmName("MathUtil")
package com.sterndu.util

import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.roundToInt

object MathUtil {
	fun avg(o: Int, r: Int): Int {
		return intArrayOf(o, r).average().roundToInt()
	}

	fun factorial(initialX: Long): BigInteger {
		var x = abs(initialX)
		var y = BigInteger.ONE
		while (x > 1) {
			y = y.multiply(BigInteger.valueOf(x))
			x--
		}
		return y
	}
}
