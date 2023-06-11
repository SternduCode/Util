@file:JvmName("Wait")
package com.sterndu.util

class Wait {
	private var rec = false
	fun Recived() {
		rec = true
	}

	fun waituntildataisrecived(testinterval: Long, maxcycles: Long?) {
		var maxcycles = maxcycles
		if (maxcycles == null) maxcycles = 30000L
		rec = false
		var i = 0
		while (!rec) try {
			Thread.sleep(testinterval)
			i++
			if (i > maxcycles) return
		} catch (e: InterruptedException) {
			e.printStackTrace()
		}
	}
}
