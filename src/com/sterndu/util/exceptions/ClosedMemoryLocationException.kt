package com.sterndu.util.exceptions;

public class ClosedMemoryLocationException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 4810813237605305525L;

	public ClosedMemoryLocationException() {}

	public ClosedMemoryLocationException(String message) {
		super(message);
	}

	public ClosedMemoryLocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClosedMemoryLocationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClosedMemoryLocationException(Throwable cause) {
		super(cause);
	}



}
