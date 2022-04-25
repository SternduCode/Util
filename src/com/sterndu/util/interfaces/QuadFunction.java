package com.sterndu.util.interfaces;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface QuadFunction<S1, S2, S3, S4, O> {

	default <V> QuadFunction<S1, S2, S3, S4, V> andThen(Function<? super O, ? extends V> after) {
		Objects.requireNonNull(after);
		return (S1 t, S2 u, S3 c, S4 v) -> after.apply(apply(t, u, c, v));
	}

	O apply(S1 s1, S2 s2, S3 s3, S4 s4);

}
