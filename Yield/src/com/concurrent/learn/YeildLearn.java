package com.concurrent.learn;

class ThreadA extends Thread {
	public ThreadA(String name) {
		super(name);
	}
	
	public synchronized void run() {
		for(int i=0; i<10; i++) {
			System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
			if (i%4 == 0)
				Thread.yield();
		}
	}
}

public class YeildLearn {
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		ThreadA t2 = new ThreadA("t2");
		t1.start();
		t2.start();
	}
}
