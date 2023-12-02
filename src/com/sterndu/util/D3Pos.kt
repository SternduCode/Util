@file:JvmName("D3Pos")
package com.sterndu.util

import java.util.*

class D3Pos(var x: Double, var y: Double, var z: Double) {

	fun distance(pos2: D3Pos): Double {
		return Math.sqrt(
			Math.pow(pos2.x - x, 2.0) + Math.pow(pos2.y - y, 2.0) + Math.pow(
				pos2.z - z, 2.0
			)
		)
	}

	override fun equals(obj: Any?): Boolean {
		if (this === obj) return true
		if (obj !is D3Pos) return false
		return java.lang.Double.doubleToLongBits(x) == java.lang.Double.doubleToLongBits(obj.x) &&
				java.lang.Double.doubleToLongBits(y) == java.lang.Double.doubleToLongBits(obj.y) &&
				java.lang.Double.doubleToLongBits(z) == java.lang.Double.doubleToLongBits(obj.z)
	}

	override fun hashCode(): Int {
		return Objects.hash(x, y, z)
	}

	override fun toString(): String {
		return ("D3Pos [x=" + x + ", y=" + y + ", z=" + z + ", hashCode()=" + hashCode() + ", getClass()="
				+ this.javaClass + "]")
	}
}
