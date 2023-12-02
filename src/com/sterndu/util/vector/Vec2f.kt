@file:JvmName("Vec2f")
package com.sterndu.util.vector

import kotlin.math.pow
import kotlin.math.sqrt

data class Vec2f @JvmOverloads constructor(val x: Float = 0.0f, val y: Float = 0.0f) {

	constructor(vector: Vec2f) : this(vector.x, vector.y)

	fun distance(vector: Vec2f): Float {
		var xDist = x - vector.x
		var yDist = y - vector.y
		if (xDist < 0) xDist *= -1f
		if (yDist < 0) yDist *= -1f
		return sqrt(xDist.toDouble().pow(2.0) + yDist.toDouble().pow(2.0)).toFloat()
	}
}
