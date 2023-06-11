package com.sterndu.util;

public class Entry<E, V> {

	private final E	key;
	private final V	value;

	public Entry(E key, V value) {
		this.key	= key;
		this.value	= value;

	}

	public E key() { return key; }

	public V value() { return value; }

}
