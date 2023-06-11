package com.sterndu.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrintStreamwTimeStamps extends PrintStream {

	public PrintStreamwTimeStamps(File file) throws FileNotFoundException {
		super(file);
	}

	public PrintStreamwTimeStamps(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(file, csn);
	}

	public PrintStreamwTimeStamps(OutputStream out) {
		super(out);
	}

	public PrintStreamwTimeStamps(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public PrintStreamwTimeStamps(OutputStream out, boolean autoFlush, String encoding)
			throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
	}

	public PrintStreamwTimeStamps(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public PrintStreamwTimeStamps(String fileName, String csn)
			throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
	}

	private String getPrefix() {
		LocalDateTime ldt = LocalDateTime.now();
		return "[" + ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSSS")) + "] ";
	}

	@Override
	public void print(boolean b) {
		super.print(getPrefix()+b);
	}

	@Override
	public void print(char c) {
		super.print(getPrefix() + c);
	}

	@Override
	public void print(double obj) {
		super.print(getPrefix() + obj);
	}

	@Override
	public void print(float f) {
		super.print(getPrefix() + f);
	}

	@Override
	public void print(int i) {
		super.print(getPrefix() + i);
	}

	@Override
	public void print(long l) {
		super.print(getPrefix() + l);
	}

	@Override
	public void print(Object obj) {
		super.print(getPrefix() + obj);
	}

	@Override
	public void print(String s) {
		super.print(getPrefix() + s);
	}

}
