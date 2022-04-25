package com.sterndu.util.interfaces;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<S1, S2, S3, O> {

	default <V> TriFunction<S1, S2, S3, V> andThen(Function<? super O, ? extends V> after) {
		Objects.requireNonNull(after);
		return (S1 t, S2 u, S3 c) -> after.apply(apply(t, u, c));
	}

	O apply(S1 s1, S2 s2, S3 s3);

}
