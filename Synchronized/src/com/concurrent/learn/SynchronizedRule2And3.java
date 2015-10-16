package com.concurrent.learn;

class Count {
	public void synMethod() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " synMethod loop " + i);
			}
		}
	}
//	public void nonSynMethod() {
//		for (int i = 0; i < 5; i++) {
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
//		}
//	}
	public void nonSynMethod() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
			}
		}
	}
}
public class SynchronizedRule2And3 {
	public static void main(String[] args) {
		final Count count = new Count();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				count.synMethod();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				count.nonSynMethod();
			}
		}, "t2");
		t1.start();
		t2.start();
	}
}
