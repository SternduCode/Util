package com.sterndu.util.interfaces

import java.util.*
import java.util.function.Consumer

fun interface ThrowingConsumer<S> {
	@Throws(Exception::class)
	fun accept(`var`: S)
	fun andThen(`var`: Consumer<S>): ThrowingConsumer<S>? {
		Objects.requireNonNull(`var`)
		return ThrowingConsumer { var2: S ->
			accept(var2)
			`var`.accept(var2)
		}
	}

	fun andThen(`var`: ThrowingConsumer<S>): ThrowingConsumer<S>? {
		Objects.requireNonNull(`var`)
		return ThrowingConsumer { var2: S ->
			accept(var2)
			`var`.accept(var2)
		}
	}
}
