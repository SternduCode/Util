@file:JvmName("ColorUtil")
package com.sterndu.util

import java.awt.Color

object ColorUtil {
	@JvmOverloads
	fun calculateColor(
		bi: BufferedImage2, rgb: Int, r2: Int, g2: Int, b2: Int, a2: Int,
		interpretzerovalasfirstwrite: Boolean = false
	): Int {
		return if ((bi.first || interpretzerovalasfirstwrite) && rgb == 0) {
			Color(r2, g2, b2, a2).rgb
		} else {
			val o = Color(rgb)
			val r3 = o.red
			val g3 = o.green
			val b3 = o.blue
			val r4 = MathUtil.avg(r3, r2)
			val g4 = MathUtil.avg(g3, g2)
			val b4 = MathUtil.avg(b3, b2)
			Color(r4, g4, b4, a2).rgb
		}
	}

	fun convert(r: Int, g: Int, b: Int): Int {
		return 255 and 255 shl 24 or (r and 255 shl 16) or (g and 255 shl 8) or (b and 255 shl 0)
	}

	fun correctValue(dv: Double): Int {
		var v = Math.round(dv).toInt()
		if (v > 255) {
			v = 255
		}
		if (v < 0) {
			v = 0
		}
		return v
	}
}
