package com.sterndu.util.interfaces;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<S> {
	void accept(S var) throws Exception;

	default ThrowingConsumer<S> andThen(Consumer<S> var) {
		Objects.requireNonNull(var);
		return var2 -> {
			this.accept(var2);
			var.accept(var2);
		};
	}

	default ThrowingConsumer<S> andThen(ThrowingConsumer<S> var) {
		Objects.requireNonNull(var);
		return var2 -> {
			this.accept(var2);
			var.accept(var2);
		};
	}
}
