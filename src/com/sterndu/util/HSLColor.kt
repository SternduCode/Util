@file:JvmName("HSLColor")
package com.sterndu.util

import java.awt.Color

/**
 * The HSLColor class provides methods to manipulate HSL (Hue, Saturation
 * Luminance) values to create a corresponding Color object using the RGB
 * ColorSpace.
 *
 * The HUE is the color, the Saturation is the purity of the color (with
 * respect to grey) and Luminance is the brightness of the color (with respect
 * to black and white)
 *
 * The Hue is specified as an angel between 0 - 360 degrees where red is 0,
 * green is 120 and blue is 240. In between you have the colors of the rainbow.
 * Saturation is specified as a percentage between 0 - 100 where 100 is fully
 * saturated and 0 approaches gray. Luminance is specified as a percentage
 * between 0 - 100 where 0 is black and 100 is white.
 *
 * In particular the HSL color space makes it easier change the Tone or Shade
 * of a color by adjusting the luminance value.
 */
class HSLColor {
	/**
	 * Get the RGB Color object represented by this HDLColor.
	 *
	 * @return the RGB Color object.
	 */
	val rGB: Color

	/**
	 * Get the HSL values.
	 *
	 * @return the HSL values.
	 */
	val hSL: FloatArray

	/**
	 * Get the Alpha value.
	 *
	 * @return the Alpha value.
	 */
	val alpha: Float

	/**
	 * Create a HSLColor object using an RGB Color object.
	 *
	 * @param rgb the RGB Color object
	 */
	constructor(rgb: Color) {
		rGB = rgb
		hSL = fromRGB(rgb)
		alpha = rgb.alpha / 255.0f
	}
	/**
	 * Create a HSLColor object using individual HSL values.
	 *
	 * @param h     the Hue value in degrees between 0 - 360
	 * @param s     the Saturation percentage between 0 - 100
	 * @param l     the Lumanance percentage between 0 - 100
	 * @param alpha the alpha value between 0 - 1
	 */
	/**
	 * Create a HSLColor object using individual HSL values and a default
	 * alpha value of 1.0.
	 *
	 * @param h is the Hue value in degrees between 0 - 360
	 * @param s is the Saturation percentage between 0 - 100
	 * @param l is the Lumanance percentage between 0 - 100
	 */
	@JvmOverloads
	constructor(h: Float, s: Float, l: Float, alpha: Float = 1.0f) {
		hSL = floatArrayOf(h, s, l)
		this.alpha = alpha
		rGB = toRGB(hSL, alpha)
	}
	/**
	 * Create a HSLColor object using an an array containing the
	 * individual HSL values.
	 *
	 * @param hsl  array containing HSL values
	 * @param alpha the alpha value between 0 - 1
	 */
	/**
	 * Create a HSLColor object using an an array containing the
	 * individual HSL values and with a default alpha value of 1.
	 *
	 * @param hsl  array containing HSL values
	 */
	@JvmOverloads
	constructor(hsl: FloatArray, alpha: Float = 1.0f) {
		hSL = hsl
		this.alpha = alpha
		rGB = toRGB(hsl, alpha)
	}

	/**
	 * Create a RGB Color object based on this HSLColor with a different
	 * Hue value. The degrees specified is an absolute value.
	 *
	 * @param degrees - the Hue value between 0 - 360
	 * @return the RGB Color object
	 */
	fun adjustHue(degrees: Float): Color {
		return toRGB(degrees, hSL[1], hSL[2], alpha)
	}

	/**
	 * Create a RGB Color object based on this HSLColor with a different
	 * Luminance value. The percent specified is an absolute value.
	 *
	 * @param percent - the Luminance value between 0 - 100
	 * @return the RGB Color object
	 */
	fun adjustLuminance(percent: Float): Color {
		return toRGB(hSL[0], hSL[1], percent, alpha)
	}

	/**
	 * Create a RGB Color object based on this HSLColor with a different
	 * Saturation value. The percent specified is an absolute value.
	 *
	 * @param percent - the Saturation value between 0 - 100
	 * @return the RGB Color object
	 */
	fun adjustSaturation(percent: Float): Color {
		return toRGB(hSL[0], percent, hSL[2], alpha)
	}

	/**
	 * Create a RGB Color object based on this HSLColor with a different
	 * Shade. Changing the shade will return a darker color. The percent
	 * specified is a relative value.
	 *
	 * @param percent - the value between 0 - 100
	 * @return the RGB Color object
	 */
	fun adjustShade(percent: Float): Color {
		val multiplier = (100.0f - percent) / 100.0f
		val l = Math.max(0.0f, hSL[2] * multiplier)
		return toRGB(hSL[0], hSL[1], l, alpha)
	}

	/**
	 * Create a RGB Color object based on this HSLColor with a different
	 * Tone. Changing the tone will return a lighter color. The percent
	 * specified is a relative value.
	 *
	 * @param percent - the value between 0 - 100
	 * @return the RGB Color object
	 */
	fun adjustTone(percent: Float): Color {
		val multiplier = (100.0f + percent) / 100.0f
		val l = Math.min(100.0f, hSL[2] * multiplier)
		return toRGB(hSL[0], hSL[1], l, alpha)
	}

	val complementary: Color
		/**
		 * Create a RGB Color object that is the complementary color of this
		 * HSLColor. This is a convenience method. The complementary color is
		 * determined by adding 180 degrees to the Hue value.
		 * @return the RGB Color object
		 */
		get() {
			val hue = (hSL[0] + 180.0f) % 360.0f
			return toRGB(hue, hSL[1], hSL[2])
		}
	val hue: Float
		/**
		 * Get the Hue value.
		 *
		 * @return the Hue value.
		 */
		get() = hSL[0]
	val luminance: Float
		/**
		 * Get the Luminance value.
		 *
		 * @return the Luminance value.
		 */
		get() = hSL[2]
	val saturation: Float
		/**
		 * Get the Saturation value.
		 *
		 * @return the Saturation value.
		 */
		get() = hSL[1]

	override fun toString(): String {
		return "HSLColor[h=" + hSL[0] +
				",s=" + hSL[1] +
				",l=" + hSL[2] +
				",alpha=" + alpha + "]"
	}

	companion object {
		private fun HueToRGB(p: Float, q: Float, h: Float): Float {
			var h = h
			if (h < 0) h += 1f
			if (h > 1) h -= 1f
			if (6 * h < 1) return p + (q - p) * 6 * h
			if (2 * h < 1) return q
			return if (3 * h < 2) p + (q - p) * 6 * (2.0f / 3.0f - h) else p
		}

		/**
		 * Convert a RGB Color to it corresponding HSL values.
		 *
		 * @return an array containing the 3 HSL values.
		 */
		fun fromRGB(color: Color): FloatArray {
			//  Get RGB values in the range 0 - 1
			val rgb = color.getRGBColorComponents(null)
			val r = rgb[0]
			val g = rgb[1]
			val b = rgb[2]

			//	Minimum and Maximum RGB values are used in the HSL calculations
			val min = Math.min(r, Math.min(g, b))
			val max = Math.max(r, Math.max(g, b))

			//  Calculate the Hue
			var h = 0f
			if (max == min) h = 0f else if (max == r) h =
				(60 * (g - b) / (max - min) + 360) % 360 else if (max == g) h =
				60 * (b - r) / (max - min) + 120 else if (max == b) h = 60 * (r - g) / (max - min) + 240

			//  Calculate the Luminance
			val l = (max + min) / 2

			//  Calculate the Saturation
			var s = 0f
			s = if (max == min) 0f else if (l <= .5f) (max - min) / (max + min) else (max - min) / (2 - max - min)
			return floatArrayOf(h, s * 100, l * 100)
		}
		/**
		 * Convert HSL values to a RGB Color.
		 *
		 * @param h Hue is specified as degrees in the range 0 - 360.
		 * @param s Saturation is specified as a percentage in the range 1 - 100.
		 * @param l Lumanance is specified as a percentage in the range 1 - 100.
		 * @param alpha  the alpha value between 0 - 1
		 *
		 * @returns the RGB Color object
		 */
		/**
		 * Convert HSL values to a RGB Color with a default alpha value of 1.
		 *
		 * @param h Hue is specified as degrees in the range 0 - 360.
		 * @param s Saturation is specified as a percentage in the range 1 - 100.
		 * @param l Lumanance is specified as a percentage in the range 1 - 100.
		 *
		 * @returns the RGB Color object
		 */
		@JvmOverloads
		fun toRGB(h: Float, s: Float, l: Float, alpha: Float = 1.0f): Color {
			var h = h
			var s = s
			var l = l
			if (s < 0.0f || s > 100.0f) {
				val message = "Color parameter outside of expected range - Saturation"
				throw IllegalArgumentException(message)
			}
			if (l < 0.0f || l > 100.0f) {
				val message = "Color parameter outside of expected range - Luminance"
				throw IllegalArgumentException(message)
			}
			if (alpha < 0.0f || alpha > 1.0f) {
				val message = "Color parameter outside of expected range - Alpha"
				throw IllegalArgumentException(message)
			}

			//  Formula needs all values between 0 - 1.
			h = h % 360.0f
			h /= 360f
			s /= 100f
			l /= 100f
			var q = 0f
			q = if (l < 0.5) l * (1 + s) else l + s - s * l
			val p = 2 * l - q
			var r = Math.max(0f, HueToRGB(p, q, h + 1.0f / 3.0f))
			var g = Math.max(0f, HueToRGB(p, q, h))
			var b = Math.max(0f, HueToRGB(p, q, h - 1.0f / 3.0f))
			r = Math.min(r, 1.0f)
			g = Math.min(g, 1.0f)
			b = Math.min(b, 1.0f)
			return Color(r, g, b, alpha)
		}
		/**
		 * Convert HSL values to a RGB Color.
		 * H (Hue) is specified as degrees in the range 0 - 360.
		 * S (Saturation) is specified as a percentage in the range 1 - 100.
		 * L (Lumanance) is specified as a percentage in the range 1 - 100.
		 *
		 * @param hsl    an array containing the 3 HSL values
		 * @param alpha  the alpha value between 0 - 1
		 *
		 * @returns the RGB Color object
		 */
		/**
		 * Convert HSL values to a RGB Color with a default alpha value of 1.
		 * H (Hue) is specified as degrees in the range 0 - 360.
		 * S (Saturation) is specified as a percentage in the range 1 - 100.
		 * L (Lumanance) is specified as a percentage in the range 1 - 100.
		 *
		 * @param hsl an array containing the 3 HSL values
		 *
		 * @returns the RGB Color object
		 */
		@JvmOverloads
		fun toRGB(hsl: FloatArray, alpha: Float = 1.0f): Color {
			return toRGB(hsl[0], hsl[1], hsl[2], alpha)
		}
	}
}
