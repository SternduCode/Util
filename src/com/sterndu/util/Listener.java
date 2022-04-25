package com.sterndu.util;

import java.util.function.Consumer;

public class Listener<T> {
	
	private Consumer<T> m;
	private T data = null;
	
	public Listener(Consumer<T> c) {
		m = c;
	}
	
	public void call(T nv) {
		data = nv;
		m.accept(nv);
	}
	
	public T getData() {
		return data;
	}

}
