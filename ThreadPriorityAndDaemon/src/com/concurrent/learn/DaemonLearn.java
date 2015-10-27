package com.concurrent.learn;

class MyThread2 extends Thread {
	public MyThread2(String name) {
		super(name);
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + "(isDaemon=" + Thread.currentThread().isDaemon() + "), loop" + i);
		}
	}
}

class MyDaemon extends Thread {
	public MyDaemon(String name) {
		super(name);
	}
	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(Thread.currentThread().getName() + "(isDaemon=" + Thread.currentThread().isDaemon() + "), loop" + i);
		}
	}
}

public class DaemonLearn {
	public static void main(String[] args) {
		// main thread is user thread
		System.out.println(Thread.currentThread().getName() + "(isDaemon=" + Thread.currentThread().isDaemon() + ")");
		Thread t1 = new MyThread2("t1");
		Thread t2 = new MyDaemon("t2");
		// JVM will exit when there is only daemon thread
		t2.setDaemon(true);
		t1.start();
		t2.start();
	}
}
