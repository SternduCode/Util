@file:JvmName("Conv")
package com.sterndu.util

import java.io.*
import java.util.function.Consumer

class Conv : OutputStream {
	class InputStream : java.io.InputStream() {
		val data: MutableList<Byte> = ArrayList()
		private var pos = 0
		@Throws(IOException::class)
		override fun available(): Int {
			return data.size
		}

		@Throws(IOException::class)
		override fun read(): Int {
			return if (++pos > data.size) -1 else data[pos - 1].toInt()
		}

		@Synchronized
		@Throws(IOException::class)
		override fun reset() {
			pos = 0
		}

		@Throws(IOException::class)
		override fun transferTo(out: OutputStream): Long {
			data.forEach(Consumer { d: Byte ->
				try {
					out.write(d.toInt())
				} catch (e: IOException) {
					e.printStackTrace()
				}
			})
			return data.size.toLong()
		}
	}

	var `is`: InputStream? = null
		private set
	var os: OutputStream? = null

	constructor() {
		`is` = InputStream()
	}

	constructor(os: OutputStream?) {
		this.os = os
	}

	fun reset() {
		`is`!!.data.clear()
	}

	@Throws(IOException::class)
	override fun write(arg0: Int) {
		`is`!!.data.add(arg0.toByte())
		os!!.write(arg0)
	}
}
