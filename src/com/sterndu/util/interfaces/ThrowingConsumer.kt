package com.sterndu.util.interfaces

import java.util.*

fun interface ThrowingConsumer<S> {
	@Throws(Exception::class)
	fun accept(`var`: S)

}
