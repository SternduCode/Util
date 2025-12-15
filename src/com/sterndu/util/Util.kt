@file:JvmName("Util")
package com.sterndu.util

import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.math.exp

/**
 * Equals.
 *
 * @param a the a
 * @param b the b
 * @return true, if successful
 */
fun equals(a: List<*>, b: List<*>): Boolean {
	for (i in a.indices) {
		val o = a[i]
		val o2 = b[i]
		if (o?.let { equals(it, o2) } == false) {
			println("$o $o2")
			return false
		}
	}
	for (i in b.indices) {
		val o = b[i]!!
		val o2 = a[i]!!
		if (!equals(o, o2)) {
			println("$o $o2")
			return false
		}
	}
	return true
}

/**
 * Equals.
 *
 * @param a the a
 * @param b the b
 * @return true, if successful
 */
fun equals(a: Map<*, *>, b: Map<*, *>): Boolean {
	var i: Iterator<*> = a.entries.iterator()
	while (i.hasNext()) {
		val (key, value) = i.next() as Map.Entry<*, *>
		try {
			if (!equals(value!!, b[key])) {
				println(value.toString() + " " + b[key])
				return false
			}
		} catch (e1: NullPointerException) {
			System.err.println("$value $key $b $a")
		}
	}
	i = b.entries.iterator()
	while (i.hasNext()) {
		val (key, value) = i.next()
		try {
			if (!equals(value!!, a[key])) {
				println(value.toString() + " " + a[key])
				return false
			}
		} catch (e1: NullPointerException) {
			System.err.println("$value $key $a $b")
		}
	}
	return true
}

/**
 * Equals.
 *
 * @param a the a
 * @param b the b
 * @return true, if successful
 */
fun equals(a: Any?, b: Any?): Boolean {
	if (a is List<*>) return equals(a, b as? List<*>)
	return if (a is Map<*, *>) equals(a, b as? Map<*, *>) else (a == b) and (b == a)
}

/**
 * Gets the string stream.
 *
 * @param str the str
 * @return the string stream
 */
fun getStringStream(str: String): InputStream = ByteArrayInputStream(str.toByteArray(Charsets.UTF_8))

/**
 * Map to string.
 *
 * @param <E> the element type
 * @param <T> the generic type
 * @param map the map
 * @param separator the separator
 * @return the string
</T></E> */
fun <E, T> MapToString(map: Map<E, T>, separator: Char): String {
	val set = map.keys
	val sb = StringBuilder()
	for (e in set) {
		val c = map[e].toString().contains("\"")
		sb.append(e.toString())
		if (!c) sb.append("=\"") else sb.append("='")
		sb.append(map[e].toString())
		if (!c) sb.append("\"" + separator) else sb.append("'$separator")
	}
	if (sb.isNotEmpty()) sb.setLength(sb.length - 1)
	return sb.toString()
}
/**
 * Number to binary string.
 *
 * @param n the number
 * @param minLength the minLength
 * @return the string
 */
@JvmOverloads
fun numberToBinaryString(n: Number, minLength: Int = 0): String {
	val num = java.lang.Long.toBinaryString(n.toLong()).toCharArray()
	val chars = CharArray(minLength.coerceAtLeast(num.size))
	Arrays.fill(chars, '0')
	for (i in num.indices) chars[chars.size - i - 1] = num[num.size - i - 1]
	return String(chars)
}

/**
 * Read X bytes.
 *
 * @param b       the b
 * @param is      the is
 * @param amount  the amount
 * @param timeout the timeout
 *
 * @return true, if successful
 */
@JvmOverloads
@Throws(IOException::class)
fun readXBytes(b: ByteArray, `is`: InputStream, amount: Int, timeout: Long = 1000L): Boolean {
	if (b.size < amount) return false
	if (amount == 0) return true
	var time = System.currentTimeMillis()
	var written = 0
	while (System.currentTimeMillis() - time < timeout && written < amount) try {
		if (`is`.available() > 0) {
			val w = `is`.read(b, written, (amount - written).coerceAtMost(`is`.available()))
			written += w
			if (w > 0) time = System.currentTimeMillis() else written -= w
		}
	} catch (e: IOException) {
		e.printStackTrace()
	}
	return written >= amount
}

/**
 * Sigmoid.
 *
 * @param x the x
 * @return the double
 */
fun Sigmoid(x: Double): Double {
	return 1 / (1 + exp(-x))
}
