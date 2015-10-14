package com.concurrent.learn;

class MyThread extends Thread {
	public MyThread(String name) {
		super(name);
	}
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is running");
	}
}

public class StartAndRunLearn {
	public static void main(String[] args) {
		MyThread mt = new MyThread("mt");
		System.out.println(Thread.currentThread().getName()+" call mt.run()");
		// run in main thread
		mt.run(); 
        System.out.println(Thread.currentThread().getName()+" call mt.start()");
        // start a new thread, run in new thread
        mt.start();
	}
}
