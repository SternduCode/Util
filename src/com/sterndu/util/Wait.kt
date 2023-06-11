package com.sterndu.util;

public class Wait {

	private boolean rec = false;

	public void Recived() {
		rec = true;
	}

	public void waituntildataisrecived(long testinterval, Long maxcycles) {
		if (maxcycles == null)
			maxcycles = 30000l;
		rec = false;
		int i = 0;
		while (!rec)
			try {
				Thread.sleep(testinterval);
				i++;
				if (i > maxcycles)
					return;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
