package com.sterndu.util;

import java.util.*;
import java.util.function.*;

public class DataSplitter {

	public static byte[] recive(Function<Consumer<byte[]>, Listener<byte[]>> recivemethod, String init){
		String[] initsp = init.split("-");
		int i = 0;
		byte[] b = new byte[0];
		try {
			i = Integer.parseInt(initsp[1]);
		} catch (NumberFormatException e) {
			return init.getBytes();
		}
		List<byte[]> list = new ArrayList<>();
		Wait u = new Wait();
		Consumer<byte[]> c = t -> {
			list.add(t);
			u.Recived();
		};
		for (int j = 0; j < i; j++) {
			recivemethod.apply(c);
			u.waituntildataisrecived(50, null);
		}
		for (byte[] element : list) {
			byte[] b2 = new byte[element.length + b.length];
			System.arraycopy(b, 0, b2, 0, b.length);
			System.arraycopy(element, 0, b2, b.length, element.length);
			b = b2;
		}
		return b;
	}

	public static void send(Consumer<byte[]> sendmethod, byte[] data){
		int l = data.length;
		int num = (int) Math.ceil(l/256d);
		byte[][] b = new byte[num][256];
		for (int i = 0; i < data.length; i++) {
			int s = (int) Math.floor(i/256d);
			b[s][i-s*256] = data[i];
		}
		String str = "Splitted-" + num;
		sendmethod.accept(str.getBytes());
		for (byte[] element : b)
			sendmethod.accept(element);
	}

}
