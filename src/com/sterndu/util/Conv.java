package com.sterndu.util;

import java.io.*;
import java.util.*;

public class Conv extends OutputStream {
	public static class InputStream extends java.io.InputStream {
		private final List<Byte> data = new ArrayList<>();
		private int pos = 0;

		private InputStream() {

		}

		@Override
		public int available() throws IOException {
			return data.size();
		}

		@Override
		public int read() throws IOException {
			return ++pos > data.size() ? -1 : data.get(pos - 1);
		}

		@Override
		public synchronized void reset() throws IOException {
			pos=0;
		}

		@Override
		public long transferTo(OutputStream out) throws IOException {
			data.forEach(d -> {
				try {
					out.write(d);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			return data.size();
		}

	}

	private InputStream is;
	private OutputStream os;

	public Conv() {
		is = new InputStream();
	}

	public Conv(OutputStream os) {
		setOs(os);
	}

	public InputStream getIs() {
		return is;
	}

	public OutputStream getOs() {
		return os;
	}

	public void reset() {
		is.data.clear();
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	@Override
	public void write(int arg0) throws IOException {
		is.data.add((byte) arg0);
		os.write(arg0);
	}
}
