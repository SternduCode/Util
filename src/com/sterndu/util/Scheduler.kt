@file:JvmName("Scheduler")
package com.sterndu.util

import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

@Deprecated("Use MultiCore", ReplaceWith("OneTaskMultipleData","com.sterndu.multicore.*"), DeprecationLevel.WARNING)
class Scheduler<K, E, O>(threads: Int, private val task: Task<E, O>) {
	fun interface Task<in E, O> {
		fun run(e: Array<out E>?): O
	}

	private val e_li: MutableList<Pair<K, Array<out E>>> = LinkedList()
	private val res_map: MutableMap<K, O> = HashMap()
	private val tg: ThreadGroup = ThreadGroup("Task-Workers")
	private val threads: Array<Thread?> = arrayOfNulls(threads)
	private val ab: AtomicBoolean = AtomicBoolean(false)

	init {
		val r = Runnable {
			val s = this
			val task = s.task
			while (true) {
				val data = s.getTask()
				if (data != null) s.putResult(data.first, task.run(data.second)) else break
				try {
					Thread.sleep(2)
				} catch (e: InterruptedException) {
					e.printStackTrace()
				}
			}
		}
		for (i in 0 until threads) {
			this.threads[i] = Thread(tg, r, "Task-Worker $i stop=0")
			this.threads[i]!!.start()
		}
	}

	@Synchronized
	private fun getTask(): Pair<K, Array<out E>>? {
		do {
			if (e_li.isNotEmpty()) {
				val entry = e_li[0]
				e_li.removeAt(0)
				return entry
			} else if (ab.get()) return null
			try {
				Thread.sleep(2)
			} catch (e: InterruptedException) {
				e.printStackTrace()
				return null
			}
		} while (true)
	}

	private fun putResult(key: K, result: O) {
		synchronized(res_map) { res_map.put(key, result) }
	}

	fun getResult(key: Any?): O? {
		return res_map[key]
	}

	val results: Map<K, O>
		get() {
			while (tg.activeCount() > 0) try {
				Thread.sleep(2)
			} catch (e: InterruptedException) {
				e.printStackTrace()
			}
			return res_map
		}

	fun noAdditionalParams() {
		ab.set(true)
	}

	fun pushParams(obj: K, vararg e: E) {
		if (!ab.get()) synchronized(e_li) { e_li.add(obj to e) }
	}
}
