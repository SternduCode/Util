@file:JvmName("Util")
package com.sterndu.util

import java.io.*
import java.util.*
import java.util.function.Function

/**
 * Equals.
 *
 * @param a the a
 * @param b the b
 * @return true, if successful
 */
fun equals(a: List<*>, b: List<*>?): Boolean {
	for (i in a.indices) {
		val o = a[i]!!
		val o2 = b!![i]!!
		if (!equals(o, o2)) {
			println("$o $o2")
			return false
		}
	}
	for (i in b!!.indices) {
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
fun equals(a: Map<*, *>, b: Map<*, *>?): Boolean {
	var i: Iterator<*> = a.entries.iterator()
	while (i.hasNext()) {
		val (key, value) = i.next() as Map.Entry<*, *>
		try {
			if (!equals(value!!, b!![key])) {
				println(value.toString() + " " + b[key])
				return false
			}
		} catch (e1: NullPointerException) {
			System.err.println(value.toString() + " " + key + " " + b + " " + a)
		}
	}
	i = b!!.entries.iterator()
	while (i.hasNext()) {
		val (key, value) = i.next()
		try {
			if (!equals(value!!, a[key])) {
				println(value.toString() + " " + a[key])
				return false
			}
		} catch (e1: NullPointerException) {
			System.err.println(value.toString() + " " + key + " " + a + " " + b)
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
fun equals(a: Any, b: Any?): Boolean {
	if (a is List<*>) return equals(a, b as List<*>?)
	return if (a is Map<*, *>) equals(a, b as Map<*, *>?) else (a == b) and (b == a)
}

/**
 * Gets the all objectsfrom iterablewith param.
 *
 * @param <E> the element type
 * @param <O> the generic type
 * @param list the list
 * @param f the f
 * @param o the o
 * @return the all objectsfrom iterablewith param
</O></E> */
fun <E, O> getAllObjectsfromIterablewithParam(list: Iterable<E>, f: Function<E, O>, o: O): List<E> {
	val li: MutableList<E> = ArrayList()
	for (e in list) if (f.apply(e) == o) li.add(e)
	return li
}

/**
 * Gets the objectfrom iterablewith param.
 *
 * @param <E> the element type
 * @param <O> the generic type
 * @param list the list
 * @param f the f
 * @param o the o
 * @return the objectfrom iterablewith param
</O></E> */
fun <E, O> getObjectfromIterablewithParam(list: Iterable<E>, f: Function<E, O>, o: O): E? {
	for (e in list) if (f.apply(e) == o) return e
	return null
}

/**
 * Gets the string stream.
 *
 * @param str the str
 * @return the string stream
 */
fun getStringStream(str: String): InputStream? {
	return try {
		ByteArrayInputStream(str.toByteArray(charset("UTF-8")))
	} catch (e: UnsupportedEncodingException) {
		e.printStackTrace()
		null
	}
}

/**
 * Mapto string.
 *
 * @param <E> the element type
 * @param <T> the generic type
 * @param map the map
 * @param seperator the seperator
 * @return the string
</T></E> */
fun <E, T> MaptoString(map: Map<E, T>, seperator: Char): String {
	val set = map.keys
	val sb = StringBuilder()
	for (e in set) {
		val c = map[e].toString().contains("\"")
		sb.append(e.toString())
		if (!c) sb.append("=\"") else sb.append("='")
		sb.append(map[e].toString())
		if (!c) sb.append("\"" + seperator) else sb.append("'$seperator")
	}
	if (sb.length > 0) sb.setLength(sb.length - 1)
	return sb.toString()
}
/**
 * Number to binary string.
 *
 * @param n the n
 * @param minlength the minlength
 * @return the string
 */
/**
 * Number to binary string.
 *
 * @param n the n
 * @return the string
 */
@JvmOverloads
fun numberToBinaryString(n: Number, minlength: Int = 0): String {
	val num = java.lang.Long.toBinaryString(n.toLong()).toCharArray()
	val chars = CharArray(Math.max(minlength, num.size))
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
/**
 * Read X bytes.
 *
 * @param b the b
 * @param is the is
 * @param amount the amount
 * @return true, if successful
 */
@JvmOverloads
fun readXBytes(b: ByteArray, `is`: InputStream, amount: Int, timeout: Long = 1000L): Boolean {
	if (b.size < amount) return false
	if (amount == 0) return true
	var time = System.currentTimeMillis()
	var written = 0
	while (System.currentTimeMillis() - time < timeout && written < amount) try {
		if (`is`.available() > 0) {
			val w = `is`.read(b, written, Math.min(amount - written, `is`.available()))
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
fun Sigmoid(x: Double?): Double {
	return 1 / (1 + Math.exp(-x!!))
}
