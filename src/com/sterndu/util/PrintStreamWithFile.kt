@file:JvmName("PrintStreamWithFile")
package com.sterndu.util

import java.io.*

class PrintStreamWithFile : PrintStream {
	private var bw: BufferedWriter? = null
	private val f: File

	constructor(file: File, f: File) : super(file) {
		this.f = f
		init()
	}

	constructor(file: File, csn: String, f: File) : super(file, csn) {
		this.f = f
		init()
	}

	constructor(out: OutputStream, autoFlush: Boolean, f: File) : super(out, autoFlush) {
		this.f = f
		init()
	}

	constructor(out: OutputStream, autoFlush: Boolean, encoding: String, f: File) : super(out, autoFlush, encoding) {
		this.f = f
		init()
	}

	constructor(out: OutputStream, f: File) : super(out) {
		this.f = f
		init()
	}

	constructor(str: String, f: File) : super(str) {
		this.f = f
		init()
	}

	constructor(fileName: String, csn: String, f: File) : super(fileName, csn) {
		this.f = f
		init()
	}

	private fun init() {
		try {
			bw = BufferedWriter(FileWriter(f))
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	override fun close() {
		super.close()
		try {
			bw!!.close()
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	override fun flush() {
		super.flush()
		try {
			bw!!.flush()
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	override fun print(obj: Any?) {
		super.print(obj)
		try {
			bw!!.write("" + obj)
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	override fun print(s: String?) {
		super.print(s)
		try {
			bw!!.write(s.toString())
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	override fun println(x: Any?) {
		super.println(x)
		try {
			bw!!.newLine()
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	override fun println(x: String?) {
		super.println(x)
		try {
			bw!!.newLine()
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}
}
