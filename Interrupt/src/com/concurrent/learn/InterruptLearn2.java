package com.concurrent.learn;

class MyThread2 extends Thread {
	private volatile boolean flag = true;
	public void stopTask() {
		flag = false;
	}
	public MyThread2(String name) {
		super(name);
	}
	
	public void run() {
		synchronized (this) {
			try {
				int i = 0;
				while(flag) {
					Thread.sleep(2000);
					i++;
					System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
				}
			} catch (InterruptedException ie) {
				System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
			}
		}
	}
}

public class InterruptLearn2 {
	public static void main(String[] args) {
		try {
			MyThread2 t1 = new MyThread2("t1");
			System.out.println(t1.getName() + " (" + t1.getState() + ") is new.");
			
			t1.start();
			System.out.println(t1.getName() + " (" + t1.getState() + ") is started.");

			Thread.sleep(3000);
			// use additional state flag
			t1.stopTask();
			System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted.");
			
			Thread.sleep(3000);
			System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted now.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
