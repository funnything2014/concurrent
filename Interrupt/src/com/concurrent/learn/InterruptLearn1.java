package com.concurrent.learn;

class MyThread1 extends Thread {
	public MyThread1(String name) {
		super(name);
	}
	
//	public void run() {
//		try {
//			int i = 0;
//			while(!isInterrupted()) {
//				Thread.sleep(2000);
//				i++;
//				System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
//			}
//		} catch (InterruptedException ie) {
//			System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
//		}
//	}
	
	public void run() {
		int i = 0;
		while(!isInterrupted()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ie) {
				System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
			}
			i++;
			System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
		}
	}
}

public class InterruptLearn1 {
	public static void main(String[] args) {
		try {
			Thread t1 = new MyThread1("t1");
			System.out.println(t1.getName() + " (" + t1.getState() + ") is new.");
			
			t1.start();
			System.out.println(t1.getName() + " (" + t1.getState() + ") is started.");

			Thread.sleep(3000);
			// set interrupt flag true, but when thread state is timed-waiting, it reset interrupt flag to false, and throw an InterruptedException
			t1.interrupt();
			System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted.");
			
			Thread.sleep(3000);
			System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted now.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
