@file:JvmName("MathUtil")
package com.sterndu.util

import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.roundToInt

object MathUtil {
	fun avg(o: Int, r: Int): Int {
		var doi = o.toDouble()
		doi += r.toDouble()
		doi /= 2.0
		return doi.roundToInt().toInt()
	}

	fun factorial(x: Long): BigInteger {
		var x = x
		var y = BigInteger.ONE
		x = abs(x)
		while (x > 1) {
			y = y.multiply(BigInteger.valueOf(x))
			x--
		}
		return y
	}
}
