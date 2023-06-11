package com.sterndu.util;

import javax.swing.text.*;

public class NumberDocument extends PlainDocument {

	/**
	 *
	 */
	private static final long serialVersionUID = -2975700881256371299L;

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		str = str.replaceAll("[^\\d]", "");
		super.insertString(offs, str, a);
	}

}
