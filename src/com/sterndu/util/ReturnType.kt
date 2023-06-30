@file:JvmName("ReturnType")
package com.sterndu.util

class ReturnType<out E : Any>(private val `val`: E) {

	val isArray: Boolean
		get() = `val`.javaClass.isArray
	val isCollection: Boolean
		get() = `val` is Collection<*>

	fun isInstanceOf(clazz: Class<*>): Boolean {
		return clazz.isInstance(`val`)
	}

	val isList: Boolean
		get() = `val` is List<*>
	val isMultipleVals: Boolean
		get() = isCollection or isArray
	val isSet: Boolean
		get() = `val` is Set<*>
}
