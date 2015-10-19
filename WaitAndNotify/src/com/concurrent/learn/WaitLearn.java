package com.concurrent.learn;

class ThreadA extends Thread {
	public ThreadA(String name) {
		super(name);
	}
	public void run() {
//		synchronized (this) {
//			System.out.println(Thread.currentThread().getName() + " call notify()");
//			notify();
//		}
		
		System.out.println(Thread.currentThread().getName() + " run");
		while (true) {
			;
		}
	}
}
public class WaitLearn {
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		
		synchronized (t1) {
            System.out.println(Thread.currentThread().getName()+" start t1");
            t1.start();

//            System.out.println(Thread.currentThread().getName()+" wait()");
//            try {
//            	// let current thread(thread running on cpu) wait
//				t1.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
            
            System.out.println(Thread.currentThread().getName() + " call wait ");
            try {
            	// current thread wait 3000ms
				t1.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

            System.out.println(Thread.currentThread().getName()+" continue");
		}
	}
}
