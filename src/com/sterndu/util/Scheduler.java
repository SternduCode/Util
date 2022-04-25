package com.sterndu.util;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

public class Scheduler<K, E, O> {

	@FunctionalInterface
	public interface Task<E, O> {

		O run(E[] e);

	}

	private final List<Map.Entry<K, E[]>> e_li;
	private final Task<E,O> task;

	private final Map<K, O> res_map;

	private final ThreadGroup tg;

	private final Thread[] threads;

	private final AtomicBoolean ab;

	public Scheduler(int threads, Task<E, O> t) {
		this.threads = new Thread[threads];
		this.task = t;
		this.tg = new ThreadGroup("Task-Workers");
		ab = new AtomicBoolean(false);
		res_map = new HashMap<>();
		e_li = new LinkedList<>();
		Runnable r = () -> {
			Scheduler<K, E, O> s = this;
			Task<E, O> task = s.task;
			while (true) {
				Map.Entry<K, E[]> data = s.getTask();
				if (data != null) s.putResult(data.getKey(), task.run(data.getValue()));
				else break;
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		for (int i = 0; i < threads; i++) {
			this.threads[i] = new Thread(tg, r, "Task-Worker " + i + " stop=0");
			this.threads[i].start();
		}
	}

	private synchronized Entry<K, E[]> getTask() {
		do {
			if (e_li.size() > 0) {
				Map.Entry<K, E[]> entry = e_li.get(0);
				e_li.remove(0);
				return entry;
			} else if (ab.get()) return null;
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return null;
			}
		} while (true);
	}

	private void putResult(K key, O result) {
		synchronized (res_map) {
			res_map.put(key, result);
		}
	}

	public O getResult(Object key) {
		return res_map.get(key);
	}

	public Map<K, O> getResults() {

		while (tg.activeCount() > 0) try {
			Thread.sleep(2);
			// System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return res_map;
	}

	public void noAdditionalParams() {
		ab.set(true);
	}

	@SuppressWarnings("unchecked")
	public void pushParams(K obj, E... e) {
		if (!ab.get())
			synchronized (this.e_li) {
				this.e_li.add(Map.entry(obj, e));
			}
	}

}
