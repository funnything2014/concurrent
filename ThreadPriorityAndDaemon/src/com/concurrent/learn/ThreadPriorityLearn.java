package com.concurrent.learn;

class MyThread1 extends Thread {
	public MyThread1(String name) {
		super(name);
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + "(" + Thread.currentThread().getPriority() + "), loop" + i);
		}
	}
}
public class ThreadPriorityLearn {
	public static void main(String[] args) {
		// thread priority from 1~10
		System.out.println(Thread.currentThread().getName() + "(" + Thread.currentThread().getPriority() + ")");
		Thread t1 = new MyThread1("t1");
		Thread t2 = new MyThread1("t2");
		t1.setPriority(1);
		t2.setPriority(10);
		t1.start();
		t2.start();
	}
}
