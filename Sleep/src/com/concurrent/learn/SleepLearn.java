package com.concurrent.learn;

class ThreadA extends Thread {
	public ThreadA(String name) {
		super(name);
	}
	
	public synchronized void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: %d\n", this.getName(), i);
			if (i%4 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
public class SleepLearn {
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		t1.start();
	}
}
