package com.sterndu.util;

import java.math.BigInteger;

public class MathUtil {

	public static int avg(int o, int r) {
		double doi = o;
		doi+=r;
		doi/= 2;
		return (int) Math.round(doi);
	}

	public static BigInteger factorial(long x) {
		BigInteger y = BigInteger.ONE;
		x = Math.abs(x);
		for (; x > 1; x--) {
			y = y.multiply(BigInteger.valueOf(x));
		}
		return y;
	}

}
