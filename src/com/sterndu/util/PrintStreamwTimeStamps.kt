@file:JvmName("PrintStreamWithTimeStamps")
package com.sterndu.util

import java.io.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PrintStreamWithTimeStamps : PrintStream {
	constructor(file: File) : super(file)
	constructor(file: File, csn: String) : super(file, csn)
	constructor(out: OutputStream) : super(out)
	constructor(out: OutputStream, autoFlush: Boolean) : super(out, autoFlush)
	constructor(out: OutputStream, autoFlush: Boolean, encoding: String) : super(out, autoFlush, encoding)
	constructor(fileName: String) : super(fileName)
	constructor(fileName: String, csn: String) : super(fileName, csn)

	private val prefix: String
		get() {
			val ldt = LocalDateTime.now()
			return "[" + ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSSS")) + "] "
		}

	override fun print(b: Boolean) {
		super.print(prefix + b)
	}

	override fun print(c: Char) {
		super.print(prefix + c)
	}

	override fun print(obj: Double) {
		super.print(prefix + obj)
	}

	override fun print(f: Float) {
		super.print(prefix + f)
	}

	override fun print(i: Int) {
		super.print(prefix + i)
	}

	override fun print(l: Long) {
		super.print(prefix + l)
	}

	override fun print(obj: Any) {
		super.print(prefix + obj)
	}

	override fun print(s: String) {
		super.print(prefix + s)
	}
}
