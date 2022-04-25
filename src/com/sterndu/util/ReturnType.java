package com.sterndu.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ReturnType<E> {

	private final E retval;

	public ReturnType(E val) {
		retval = val;
	}

	public E getVal() { return retval; }

	public boolean isArray() { return retval.getClass().isArray(); }

	public boolean isCollection() { return Collection.class.isInstance(retval); }

	public boolean isInstanceOf(Class<?> clazz) { return clazz.isInstance(retval); }

	public boolean isList() { return List.class.isInstance(retval); }

	public boolean isMultipleVals() { return isCollection() | isArray(); }

	public boolean isSet() {
		return Set.class.isInstance(retval);
	}

}
