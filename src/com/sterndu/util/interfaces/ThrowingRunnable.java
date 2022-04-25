package com.sterndu.util.interfaces;

@FunctionalInterface
public interface ThrowingRunnable {
	void run() throws Exception;
}
