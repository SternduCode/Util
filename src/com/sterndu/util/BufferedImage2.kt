@file:JvmName("BufferedImage2")
package com.sterndu.util

import java.awt.image.BufferedImage

class BufferedImage2 {
	var bi: BufferedImage
	var first = false

	constructor(bi: BufferedImage) {
		this.bi = bi
	}

	constructor(bi: BufferedImage, first: Boolean) {
		this.bi = bi
		this.first = first
	}
}
