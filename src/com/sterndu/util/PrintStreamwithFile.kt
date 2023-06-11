package com.sterndu.util;

import java.io.*;

public class PrintStreamwithFile extends PrintStream {

	private BufferedWriter bw;
	private final File f;

	public PrintStreamwithFile(File file, File f) throws FileNotFoundException {
		super(file);
		this.f = f;
		init();
	}

	public PrintStreamwithFile(File file, String csn, File f)
			throws FileNotFoundException, UnsupportedEncodingException {
		super(file, csn);
		this.f = f;
		init();
	}

	public PrintStreamwithFile(OutputStream out, boolean autoFlush, File f) {
		super(out, autoFlush);
		this.f = f;
		init();
	}

	public PrintStreamwithFile(OutputStream out, boolean autoFlush, String encoding, File f)
			throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
		this.f = f;
		init();
	}

	public PrintStreamwithFile(OutputStream out, File f) {
		super(out);
		this.f = f;
		init();
	}

	public PrintStreamwithFile(String str, File f) throws FileNotFoundException {
		super(str);
		this.f = f;
		init();
	}

	public PrintStreamwithFile(String fileName, String csn, File f) throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
		this.f = f;
		init();
	}

	private void init() {
		try {
			bw = new BufferedWriter(new FileWriter(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		super.close();
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void flush() {
		super.flush();
		try {
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void print(Object obj) {
		super.print(obj);
		try {
			bw.write("" + obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void print(String s) {
		super.print(s);
		try {
			bw.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void println(Object x) {
		super.println(x);
		try {
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void println(String x) {
		super.println(x);
		try {
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
