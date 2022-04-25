package com.sterndu.util.interfaces;

@FunctionalInterface
public interface QuadConsumer<S1, S2, S3, S4> {
	void accept(S1 s1, S2 s2, S3 s3, S4 s4);
}
