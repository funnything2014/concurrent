package com.concurrent.learn;

public class SleepAndWaitLearn {
	private static Object obj = new Object();
	
	static class ThreadA extends Thread {
		public ThreadA(String name) {
			super(name);
		}
		public void run() {
			synchronized (obj) {
				for (int i = 0; i < 10; i++) {
					System.out.printf("%s: %d\n", this.getName(), i);
					if (i%4 == 0) {
						try {
							// sleep will not release lock
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		ThreadA t2 = new ThreadA("t2");
		t1.start();
		t2.start();
	}
}
