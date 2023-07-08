package com.sterndu.util.interfaces

fun interface ThrowingRunnable {
	@Throws(Exception::class)
	fun run()
}
