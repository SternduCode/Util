package com.sterndu.util.interfaces;

public interface ThrowingBiFunction<T, T2, R, TR extends Throwable> {

	R apply(T t, T2 t2) throws TR;

}
