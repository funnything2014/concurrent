package com.concurrent.learn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Depot1 {
	private int size;
	private Lock lock;
	
	public Depot1() {
		this.size = 0;
		this.lock = new ReentrantLock();
	}
	
	public void produce(int val) {
		lock.lock();
		try {
			size += val;
			System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
		} finally {
			lock.unlock();
		}
	}
	
	public void consume(int val) {
		lock.lock();
		try {
			size -= val;
			System.out.printf("%s consume(%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
		} finally {
			lock.unlock();
		}
	}
}

class Producer1 {
	private Depot1 depot;
	
	public Producer1(Depot1 depot) {
		this.depot = depot;
	}
	
	public void produce(final int val) {
		new Thread() {
			public void run() {
				depot.produce(val);
			}
		}.start();
	}
}

class Customer1 {
	private Depot1 depot;
	
	public Customer1(Depot1 depot) {
		this.depot = depot;
	}
	
	public void consume(final int val) {
		new Thread() {
			public void run() {
				depot.consume(val);
			}
		}.start();
	}
}

public class ReentrantLockLearn {
	public static void main(String[] args) {
		// two major problems:
		// 1. depot's capacity can't be negative
		// 2. depot's capacity can't be unlimited
		Depot1 depot = new Depot1();
		Producer1 producer = new Producer1(depot);
		Customer1 customer = new Customer1(depot);
		
		producer.produce(60);
		producer.produce(120);
		customer.consume(90);
		customer.consume(150);
		producer.produce(110);
	}
}
