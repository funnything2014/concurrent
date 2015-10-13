package com.concurrent.learn;

class MyThread extends Thread {
	private int ticket = 10;
	public void run() {
		for (int i = 0; i < 20; i++) {
			if(this.ticket > 0) {
				System.out.println(this.getName() + " sell£ºticket" + this.ticket--);
			}
		}
	}
}

public class ThreadLearn {
	public static void main(String[] args) {
		// start 3 threads, each thread sell 10 tickets.
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();
		t1.start();
		t2.start();
		t3.start();
	}
}
