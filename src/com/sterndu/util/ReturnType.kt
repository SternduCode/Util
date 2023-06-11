@file:JvmName("ReturnType")
package com.sterndu.util

class ReturnType<out E : Any>(private val `val`: E) {

	val isArray: Boolean
		get() = `val`.javaClass.isArray
	val isCollection: Boolean
		get() = MutableCollection::class.java.isInstance(`val`)

	fun isInstanceOf(clazz: Class<*>): Boolean {
		return clazz.isInstance(`val`)
	}

	val isList: Boolean
		get() = MutableList::class.java.isInstance(`val`)
	val isMultipleVals: Boolean
		get() = isCollection or isArray
	val isSet: Boolean
		get() = MutableSet::class.java.isInstance(`val`)
}
