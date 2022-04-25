package com.sterndu.util.interfaces;

import java.util.Objects;
import java.util.function.Function;

public interface ThrowingBiFunction<T, T2, R, TR extends Throwable> {

	static ThrowingBiFunction<?, ?, ?, ?> identity() {
		return (a, b) -> a;
	}

	default <V> ThrowingBiFunction<T, T2, V, TR> andThen(Function<R, ? extends V> after) {
		Objects.requireNonNull(after);
		return (t, t2) -> after.apply(this.apply(t, t2));
	}

	default <V> ThrowingBiFunction<T, T2, V, ? extends TR> andThen(
			ThrowingFunction<? super R, ? extends V, ? extends TR> after) {
		Objects.requireNonNull(after);
		return (t, t2) -> after.apply(this.apply(t, t2));
	}

	R apply(T t, T2 t2) throws TR;

	default <V, V2> ThrowingBiFunction<V, V2, R, TR> compose(Function<? super V, ? extends T> before1,
			Function<? super V2, ? extends T2> before2) {
		Objects.requireNonNull(before1);
		Objects.requireNonNull(before2);
		return (v, v2) -> this.apply(before1.apply(v), before2.apply(v2));
	}

	default <V, V2> ThrowingBiFunction<V, V2, R, ? extends TR> compose(
			ThrowingFunction<? super V, ? extends T, ? extends TR> before1,
			ThrowingFunction<? super V2, ? extends T2, ? extends TR> before2) {
		Objects.requireNonNull(before1);
		Objects.requireNonNull(before2);
		return (v, v2) -> this.apply(before1.apply(v), before2.apply(v2));
	}

}
