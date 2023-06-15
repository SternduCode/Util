@file:JvmName("Base256")
package com.sterndu.util

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.InetAddress
import java.nio.ByteBuffer
import java.util.*

// -18 Length = 238 ß
private const val ORACLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789=+-*/_#~!?([{}])'\\\"%&^<>|@ѬѭѪѫѨѩѦѧЫыФфБДЖЗИЛЦЧШЩЪЭЮЯбвгджзилнтцbArrшщъэюяђљњѯѱѳΑαΒβΓγΔδΕεΖζΗηΘθΙιΚκΛλΜμΝνΞξΟοΠπΡρΣσςΤτΥυΦφΧχΨψΩω𐌀𐌁𐌂𐌃𐌄𐌅𐌆𐌇𐌉𐌊𐌋𐌌𐌍𐌏𐌐𐌒𐌓𐌔𐌕𐌖𐌗𐌈𐌎𐌑𐌘𐌙𐌚𐌛𐌜𐌝𐌞𐌠𐌡𐌢𐌣öäüÖÄÜ§$€°,;.:ß¥·↕ï←♀▬Ì▀ú↑Ç‼Â¯¶bûÎ"

fun decode(string: String): ByteArray {
	val bytes = ByteArray(string.length)
	for (i in string.indices) bytes[i] = ORACLE.indexOf(string[i]).toByte()
	return bytes
}

fun encode(bytes: ByteArray): String {
	var out = ""
	for (b in bytes) out += ORACLE[0xff and b.toInt()]
	return out
}

fun main() {
	println(ORACLE.toCharArray().size)
	println(ORACLE.toByteArray(Charsets.UTF_8).size)
	for (c in ORACLE.toCharArray()) println(c)
	val str = "FFS this is a log lorem ipsum dolore string...".toByteArray()
	val ip = "12a02:0908:1a16:bd80:8928:31a3:5dc4:a4f72192.168.000.218361013".toByteArray()
	val baos = ByteArrayOutputStream()
	try {
		baos.write(1)
		baos.write(InetAddress.getByName("2a02:0908:1a16:bd80:8928:31a3:5dc4:a4f7").address)
		baos.write(2)
		baos.write(InetAddress.getByName("192.168.000.218").address)
		baos.write(3)
		println(61013.toUShort())
		baos.write(ByteBuffer.allocate(2).putShort(61013.toShort()).array())
	} catch (e: IOException) {
		e.printStackTrace()
	}
	val ip2 = baos.toByteArray()
	var out = String(Base64.getEncoder().encode(ip2))
	println(out)
	printIp(Base64.getDecoder().decode(out))
	out = encode(ip2)
	println(out)
	printIp(decode(out))
	val byteArray = ByteArray(256)
	for (i in byteArray.indices) byteArray[i] = i.toByte()
	out = encode(byteArray)
	println(out.length)
	println(out)
	val byteArray2 = decode(out)
	println(byteArray2.contentToString())

	var lastVal = byteArray2[0].toUByte()
	for (i in 1 .. 255) {
		if ((byteArray2[i] - 1).toUByte() == lastVal) {
			lastVal = byteArray2[i].toUByte()
		} else {
			println(encode(byteArrayOf(byteArray2[i])))
		}
	}
}

fun printIp(ipData: ByteArray) {
	var i = 0
	while (i < ipData.size) {
		when (ipData[i]) {
			1.toByte() -> {
				var j = 1
				while (j < 16) {
					val x =
						(java.lang.Byte.toUnsignedInt(ipData[i + j]) shl 8) + java.lang.Byte.toUnsignedInt(ipData[i + j + 1])
					print(Integer.toHexString(x))
					if (j < 15) print(':')
					j += 2
				}
				println()
				i += 17
			}

			2.toByte() -> {
				var j = 1
				while (j <= 4) {
					print(java.lang.Byte.toUnsignedInt(ipData[i + j]))
					if (j < 4) print('.')
					j++
				}
				println()
				i += 5
			}

			3.toByte() -> {
				val x = (java.lang.Byte.toUnsignedInt(ipData[++i]) shl 8) + java.lang.Byte.toUnsignedInt(ipData[++i])
				println(x)
				i++
			}

			else -> println(ipData[i++])
		}
	}
}
