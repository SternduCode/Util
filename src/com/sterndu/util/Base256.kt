package com.sterndu.util;

import java.io.*;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.*;

public class Base256 {
	// -18 Length = 238 ß
	public static final String ORACLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789=+-*/_#~!?([{}])'\\\"%&^<>|@ѬѭѪѫѨѩѦѧЫыФфБДЖЗИЛЦЧШЩЪЭЮЯбвгджзилнтцчшщъэюяђљњѯѱѳΑαΒβΓγΔδΕεΖζΗηΘθΙιΚκΛλΜμΝνΞξΟοΠπΡρΣσςΤτΥυΦφΧχΨψΩω𐌀𐌁𐌂𐌃𐌄𐌅𐌆𐌇𐌉𐌊𐌋𐌌𐌍𐌏𐌐𐌒𐌓𐌔𐌕𐌖𐌗𐌈𐌎𐌑𐌘𐌙𐌚𐌛𐌜𐌝𐌞𐌠𐌡𐌢𐌣öäüÖÄÜ§$€°,;.:ß¥·↕ï←♀▬Ì▀ú↑Ç‼Â¯¶bûÎ";

	public static byte[] decode(String string) {
		byte[] bytes = new byte[string.length()];
		for (int i = 0; i < string.length(); i++) bytes[i] = (byte) ORACLE.indexOf(string.charAt(i));
		return bytes;
	}

	public static String encode(byte[] bytes) {
		String out = "";
		for (byte b: bytes) out += ORACLE.charAt(0xff & b);
		return out;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println(ORACLE.toCharArray().length);
		for (char c : ORACLE.toCharArray()) System.out.println(c);
		byte[] str = "FFS this is a log lorem ipsum dolore string...".getBytes();
		byte[] ip = "12a02:0908:1a16:bd80:8928:31a3:5dc4:a4f72192.168.000.218361013".getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			baos.write(1);
			baos.write(InetAddress.getByName("2a02:0908:1a16:bd80:8928:31a3:5dc4:a4f7").getAddress());
			baos.write(2);
			baos.write(InetAddress.getByName("192.168.000.218").getAddress());
			baos.write(3);
			System.out.println(Short.toUnsignedInt((short) 61013));
			baos.write(ByteBuffer.allocate(2).putShort((short) 61013).array());
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] ip2 = baos.toByteArray();
		String out = new String(Base64.getEncoder().encode(ip2));
		System.out.println(out);
		printIp(Base64.getDecoder().decode(out));
		out = encode(ip2);
		System.out.println(out);
		printIp(decode(out));
		byte[] b_arr = new byte[256];
		for (int i = 0; i < 256; i++) b_arr[i] = (byte) i;
		out = encode(b_arr);
		System.out.println(out);
		System.out.println(Arrays.toString(decode(out)));
	}

	public static void printIp(byte[] ipdata) {
		for (int i=0;i<ipdata.length;i++)
			switch (ipdata[i]) {
				case 1:
					for (int j=1;j<16;j+=2) {
						int x = (Byte.toUnsignedInt(ipdata[i + j]) << 8) + Byte.toUnsignedInt(ipdata[i + j + 1]);
						System.out.print(Integer.toHexString(x));
						if (j<15) System.out.print(':');
					}
					System.out.println();
					i+=16;
					break;
				case 2:
					for (int j=1;j<=4;j++) {
						System.out.print(Byte.toUnsignedInt(ipdata[i + j]));
						if (j<4) System.out.print('.');
					}
					System.out.println();
					i+=4;
					break;
				case 3:
					int x = (Byte.toUnsignedInt(ipdata[++i]) << 8) + Byte.toUnsignedInt(ipdata[++i]);
					System.out.println(x);
					break;
				default:
					System.out.println(ipdata[i]);
					break;
			}
	}

}
