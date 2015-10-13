package com.concurrent.learn;

class MyRunnable implements Runnable {
	private volatile int ticket = 10;
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			synchronized (MyRunnable.class) {
				if(this.ticket > 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " sell£ºticket" + this.ticket--);
				}
			}
		}
	}
}

public class RunnableLearn {
	public static void main(String[] args) {
		// start 3 threads, share one runnable object, threads sell 10 tickets totally.
		MyRunnable mr = new MyRunnable();
		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr);
		Thread t3 = new Thread(mr);
		t1.start();
		t2.start();
		t3.start();
	}
}
