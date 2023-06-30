@file:JvmName("Vec2f")
package com.sterndu.util.vector

import kotlin.math.pow
import kotlin.math.sqrt

data class Vec2f @JvmOverloads constructor(val x: Float = 0.0f, val y: Float = 0.0f) {

	constructor(vector: Vec2f) : this(vector.x, vector.y)

	fun distance(vector: Vec2f): Float {
		var xdist = x - vector.x
		var ydist = y - vector.y
		if (xdist < 0) xdist *= -1f
		if (ydist < 0) ydist *= -1f
		return sqrt(xdist.toDouble().pow(2.0) + ydist.toDouble().pow(2.0)).toFloat()
	}
}
