package com.sterndu.util;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {

	/**
	 * Equals.
	 *
	 * @param a the a
	 * @param b the b
	 * @return true, if successful
	 */
	public static boolean equals(List<?> a, List<?> b) {
		for (int i=0;i<a.size();i++) {
			Object o=a.get(i),o2=b.get(i);
			if (!equals(o, o2)) {
				System.out.println(o + " " + o2);
				return false;
			}
		}
		for (int i = 0; i < b.size(); i++) {
			Object o = b.get(i), o2 = a.get(i);
			if (!equals(o, o2)) {
				System.out.println(o + " " + o2);
				return false;
			}
		}
		return true;
	}

	/**
	 * Equals.
	 *
	 * @param a the a
	 * @param b the b
	 * @return true, if successful
	 */
	public static boolean equals(Map<?,?> a, Map<?,?> b) {
		Iterator<?> i = a.entrySet().iterator();
		while (i.hasNext()) {
			Entry<?, ?> e = (Entry<?, ?>) i.next();
			try {
				if (!equals(e.getValue(), b.get(e.getKey()))) {
					System.out.println(e.getValue() + " " + b.get(e.getKey()));
					return false;
				}
			} catch (NullPointerException e1) {
				System.err.println(e.getValue() + " " + e.getKey() + " " + b + " " + a);
			}
		}
		i = b.entrySet().iterator();
		while (i.hasNext()) {
			Entry<?, ?> e = (Entry<?, ?>) i.next();
			try {
				if (!equals(e.getValue(), a.get(e.getKey()))) {
					System.out.println(e.getValue() + " " + a.get(e.getKey()));
					return false;
				}
			} catch (NullPointerException e1) {
				System.err.println(e.getValue() + " " + e.getKey() + " " + a + " " + b);
			}
		}
		return true;
	}

	/**
	 * Equals.
	 *
	 * @param a the a
	 * @param b the b
	 * @return true, if successful
	 */
	public static boolean equals(Object a, Object b) {
		if (a instanceof List) return equals((List<?>)a, (List<?>)b);
		if (a instanceof Map) return equals((Map<?, ?>) a, (Map<?, ?>) b);
		return a.equals(b) & b.equals(a);
	}

	/**
	 * Gets the all objectsfrom iterablewith param.
	 *
	 * @param <E> the element type
	 * @param <O> the generic type
	 * @param list the list
	 * @param f the f
	 * @param o the o
	 * @return the all objectsfrom iterablewith param
	 */
	public static <E, O> List<E> getAllObjectsfromIterablewithParam(Iterable<E> list, Function<E, O> f, O o) {
		List<E> li = new ArrayList<>();
		for (E e : list)
			if (f.apply(e).equals(o))
				li.add(e);
		return li.size()>0?li:null;
	}

	/**
	 * Gets the objectfrom iterablewith param.
	 *
	 * @param <E> the element type
	 * @param <O> the generic type
	 * @param list the list
	 * @param f the f
	 * @param o the o
	 * @return the objectfrom iterablewith param
	 */
	public static <E, O> E getObjectfromIterablewithParam(Iterable<E> list, Function<E, O> f, O o) {
		for (E e : list)
			if (f.apply(e).equals(o))
				return e;
		return null;
	}

	/**
	 * Gets the string stream.
	 *
	 * @param str the str
	 * @return the string stream
	 */
	public static InputStream getStringStream(String str) {
		try {
			return new ByteArrayInputStream(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Mapto string.
	 *
	 * @param <E> the element type
	 * @param <T> the generic type
	 * @param map the map
	 * @param seperator the seperator
	 * @return the string
	 */
	public static <E, T> String MaptoString(Map<E, T> map, char seperator) {
		Set<E> set = map.keySet();
		StringBuilder sb = new StringBuilder();

		for (E e : set) {
			boolean c = map.get(e).toString().contains("\"");
			sb.append(e.toString());
			if (!c) sb.append("=\"");
			else sb.append("='");
			sb.append(map.get(e).toString());
			if (!c) sb.append("\"" + seperator);
			else sb.append("'" + seperator);
		}

		if (sb.length() > 0)
			sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * Number to binary string.
	 *
	 * @param n the n
	 * @return the string
	 */
	public static String numberToBinaryString(Number n) {
		return numberToBinaryString(n, 0);
	}

	/**
	 * Number to binary string.
	 *
	 * @param n the n
	 * @param minlength the minlength
	 * @return the string
	 */
	public static String numberToBinaryString(Number n, int minlength) {
		char[] num = Long.toBinaryString(n.longValue()).toCharArray();
		char[] chars = new char[Math.max(minlength, num.length)];
		Arrays.fill(chars, '0');
		for (int i = 0; i < num.length; i++)
			chars[chars.length - i - 1] = num[num.length - i - 1];
		return new String(chars);
	}

	/**
	 * Read X bytes.
	 *
	 * @param b the b
	 * @param is the is
	 * @param amount the amount
	 * @return true, if successful
	 */
	public static boolean readXBytes(byte[] b,InputStream is,int amount) {
		return readXBytes(b, is, amount, 1000l); }

	/**
	 * Read X bytes.
	 *
	 * @param b       the b
	 * @param is      the is
	 * @param amount  the amount
	 * @param timeout the timeout
	 *
	 * @return true, if successful
	 */
	public static boolean readXBytes(byte[] b, InputStream is, int amount, long timeout) {
		if (b.length < amount) return false;
		if (amount == 0) return true;
		long time = System.currentTimeMillis();
		int written = 0;
		while (System.currentTimeMillis() - time < timeout && written < amount) try {
			if (is.available() > 0) {
				final int w = is.read(b, written, Math.min(amount - written, is.available()));
				written += w;
				if (w > 0) time = System.currentTimeMillis();
				else written-=w;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return written >= amount;
	}

	/**
	 * Sigmoid.
	 *
	 * @param x the x
	 * @return the double
	 */
	public static Double Sigmoid(Double x) {
		return 1 / (1 + Math.exp(-x));
	}

}
