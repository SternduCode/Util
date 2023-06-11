@file:JvmName("NumberDocument")
package com.sterndu.util

import javax.swing.text.AttributeSet
import javax.swing.text.BadLocationException
import javax.swing.text.PlainDocument

class NumberDocument : PlainDocument() {
	@Throws(BadLocationException::class)
	override fun insertString(offs: Int, str: String, a: AttributeSet) {
		var str = str
		str = str.replace("[^\\d]".toRegex(), "")
		super.insertString(offs, str, a)
	}

	companion object {
		/**
		 *
		 */
		private const val serialVersionUID = -2975700881256371299L
	}
}
