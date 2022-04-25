package com.sterndu.util.interfaces;

import java.util.Objects;
import java.util.function.Function;

public interface ThrowingFunction<T, R, TR extends Throwable> {

	static ThrowingFunction<?, ?, ?> identity() {
		return a -> a;
	}

	default <V> ThrowingFunction<T, V, TR> andThen(Function<R, ? extends V> after) {
		Objects.requireNonNull(after);
		return t -> after.apply(this.apply(t));
	}

	default <V> ThrowingFunction<T, V, ? extends TR> andThen(
			ThrowingFunction<? super R, ? extends V, ? extends TR> after) {
		Objects.requireNonNull(after);
		return t -> after.apply(this.apply(t));
	}

	R apply(T t) throws TR;

	default <V> ThrowingFunction<V, R, TR> compose(Function<? super V, ? extends T> before) {
		Objects.requireNonNull(before);
		return v -> this.apply(before.apply(v));
	}

	default <V> ThrowingFunction<V, R, ? extends TR> compose(
			ThrowingFunction<? super V, ? extends T, ? extends TR> before) {
		Objects.requireNonNull(before);
		return v -> this.apply(before.apply(v));
	}

}
