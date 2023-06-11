@file:JvmName("DataSplitter")
package com.sterndu.util

import java.util.function.Consumer
import java.util.function.Function

object DataSplitter {
	fun recive(recivemethod: Function<Consumer<ByteArray>?, Listener<ByteArray?>?>, init: String): ByteArray {
		val initsp = init.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
		var i = 0
		var b = ByteArray(0)
		i = try {
			initsp[1].toInt()
		} catch (e: NumberFormatException) {
			return init.toByteArray()
		}
		val list: MutableList<ByteArray> = ArrayList()
		val u = Wait()
		val c = Consumer<ByteArray> { t: ByteArray ->
			list.add(t)
			u.Recived()
		}
		for (j in 0 until i) {
			recivemethod.apply(c)
			u.waituntildataisrecived(50, null)
		}
		for (element in list) {
			val b2 = ByteArray(element.size + b.size)
			System.arraycopy(b, 0, b2, 0, b.size)
			System.arraycopy(element, 0, b2, b.size, element.size)
			b = b2
		}
		return b
	}

	fun send(sendmethod: Consumer<ByteArray?>, data: ByteArray) {
		val l = data.size
		val num = Math.ceil(l / 256.0).toInt()
		val b = Array(num) { ByteArray(256) }
		for (i in data.indices) {
			val s = Math.floor(i / 256.0).toInt()
			b[s][i - s * 256] = data[i]
		}
		val str = "Splitted-$num"
		sendmethod.accept(str.toByteArray())
		for (element in b) sendmethod.accept(element)
	}
}
