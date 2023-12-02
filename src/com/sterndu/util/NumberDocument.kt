@file:JvmName("NumberDocument")
package com.sterndu.util

import javax.swing.text.*

class NumberDocument : PlainDocument() {
	@Throws(BadLocationException::class)
	override fun insertString(offs: Int, str: String, a: AttributeSet) {
		var str = str
		str = str.replace(Regex("\\D"), "")
		super.insertString(offs, str, a)
	}

	companion object {
		/**
		 *
		 */
		private const val serialVersionUID = -2975700881256371299L
	}
}
