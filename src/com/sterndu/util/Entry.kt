@file:JvmName("Entry")
package com.sterndu.util

data class Entry<E, V>(val key: E, val value: V) {
	fun key(): E {
		return key
	}

	fun value(): V {
		return value
	}
}
