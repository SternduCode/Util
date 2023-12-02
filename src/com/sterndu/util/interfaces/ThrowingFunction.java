package com.sterndu.util.interfaces;

public interface ThrowingFunction<T, R, TR extends Throwable> {

	R apply(T t) throws TR;

}
