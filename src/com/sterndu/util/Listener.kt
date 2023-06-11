@file:JvmName("Listener")
package com.sterndu.util

import java.util.function.Consumer

class Listener<T>(private val m: Consumer<T>) {
	var data: T? = null
		private set

	fun call(nv: T) {
		data = nv
		m.accept(nv)
	}
}
