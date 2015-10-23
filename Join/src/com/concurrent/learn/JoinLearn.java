package com.concurrent.learn;

class ThreadA extends Thread {
	public ThreadA(String name) {
		super(name);
	}
	
	public void run() {
		System.out.printf("%s start\n", this.getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s finish\n", this.getName());
	}
}
public class JoinLearn {
	public static void main(String[] args) {
		try {
			ThreadA t1 = new ThreadA("t1");
			t1.start();
			// let main thread wait for sub thread died
			t1.join();
			System.out.printf("%s finish\n", Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
