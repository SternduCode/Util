package com.sterndu.util;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;

public class Util {
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

	public static boolean equals(Object a, Object b) {
		if (a instanceof List) return equals((List<?>)a, (List<?>)b);
		else if (a instanceof Map) return equals((Map<?, ?>) a, (Map<?, ?>) b);
		else return a.equals(b) & b.equals(a);
	}

	public static <E, O> List<E> getAllObjectsfromIterablewithParam(Iterable<E> list, Function<E, O> f, O o) {
		List<E> li = new ArrayList<>();
		for (E e : list)
			if (f.apply(e).equals(o))
				li.add(e);
		return li.size()>0?li:null;
	}

	public static <E, O> E getObjectfromIterablewithParam(Iterable<E> list, Function<E, O> f, O o) {
		for (E e : list)
			if (f.apply(e).equals(o))
				return e;
		return null;
	}

	public static InputStream getStringStream(String str) {
		try {
			return new ByteArrayInputStream(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

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

	public static String numberToBinaryString(Number n) {
		return numberToBinaryString(n, 0);
	}

	public static String numberToBinaryString(Number n, int minlength) {
		char[] num = Long.toBinaryString(n.longValue()).toCharArray();
		char[] chars = new char[Math.max(minlength, num.length)];
		Arrays.fill(chars, '0');
		for (int i = 0; i < num.length; i++)
			chars[chars.length - i - 1] = num[num.length - i - 1];
		return new String(chars);
	}

	public static boolean readXBytes(byte[] b,InputStream is,int amount) {
		if (b.length < amount) return false;
		if (amount == 0) return true;
		long time = System.currentTimeMillis();
		int written = 0;
		while (System.currentTimeMillis() - time < 2000 && written < amount) try {
			final int w = is.read(b, written, amount - written);
			written += w;
			if (w > 0) time = System.currentTimeMillis();
			else written-=w;
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return written >= amount;
	}

	public static Double Sigmoid(Double x) {
		return 1 / (1 + Math.exp(-x));
	}

}
