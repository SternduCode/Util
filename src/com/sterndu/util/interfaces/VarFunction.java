package com.sterndu.util.interfaces;

@FunctionalInterface
public interface VarFunction<R> {
	R apply(Object... o);
}
