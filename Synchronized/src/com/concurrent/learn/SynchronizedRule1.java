package com.concurrent.learn;

class MyThread extends Thread {
	public MyThread(String name) {
		super(name);
	}
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " loop " + i);
			}
		}
	}
}

class MyRunnable implements Runnable {
	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " loop " + i);
			}
		}
	}
}

public class SynchronizedRule1 {
	public static void main(String[] args) {
//		t1, t2 share same runnable object
//		Runnable run = new MyRunnable();
//		Thread t1 = new Thread(run, "t1");
//		Thread t2 = new Thread(run, "t2");
//		t1.start();
//		t2.start();
		
//		t3, t4 run separately
		MyThread t3 = new MyThread("t3");
		MyThread t4 = new MyThread("t4");
		t3.start();
		t4.start();
	}
}
