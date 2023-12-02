@file:JvmName("D2Pos")
package com.sterndu.util

import java.util.*

class D2Pos(var x: Double, var y: Double) {

	fun distance(pos2: D2Pos): Double {
		return Math.sqrt(Math.pow(pos2.x - x, 2.0) + Math.pow(pos2.y - y, 2.0))
	}

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj !is D2Pos) return false
		return (java.lang.Double.doubleToLongBits(x) == java.lang.Double.doubleToLongBits(obj.x)  &&
				java.lang.Double.doubleToLongBits(y) == java.lang.Double.doubleToLongBits(obj.y))
	}

	override fun hashCode(): Int {
		return Objects.hash(x, y)
	}

	override fun toString(): String {
		return "(x=$x|y=$y)"
	}
}
