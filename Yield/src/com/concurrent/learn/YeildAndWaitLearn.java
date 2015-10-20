package com.concurrent.learn;

public class YeildAndWaitLearn {
	private static Object obj = new Object();

	static class ThreadA extends Thread {
		public ThreadA(String name) {
			super(name);
		}
		
		public void run() {
			synchronized (obj) {
				for(int i=0; i <10; i++) {
					System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i); 
					if (i%4 == 0)
						// yield will not release the lock
						Thread.yield();
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
