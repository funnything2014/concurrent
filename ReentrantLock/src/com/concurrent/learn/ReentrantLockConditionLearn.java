package com.concurrent.learn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Depot2 {
	private int capacity;
	private int size;
	private Lock lock;
	private Condition fullCondition;
	private Condition emptyCondition;
	
	public Depot2(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.lock = new ReentrantLock();
		this.fullCondition = lock.newCondition();
		this.emptyCondition = lock.newCondition();
	}
	
	public void produce(int val) {
		lock.lock();
		try {
			int left = val;
			while (left > 0) {
				while (size >= capacity) {
					fullCondition.await();
				}
				int inc = (size+left) > capacity ? (capacity-size) : left;
				size += inc;
				left -= inc;
				System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, inc, size);
				emptyCondition.signal();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void consume(int val) {
		lock.lock();
		try {
			int left = val;
			while (left > 0) {
				while (size <= 0) {
					emptyCondition.await();
				}
				int dec = (size<left) ? size :left;
				size -= dec;
				left -= dec;
				System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
				fullCondition.signal();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public String toString() {
		return "capacity:" + capacity + ", actual size:" + size;
	}
}

class Producer2 {
	private Depot2 depot;
	
	public Producer2(Depot2 depot) {
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

class Customer2 {
	private Depot2 depot;
	
	public Customer2(Depot2 depot) {
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

public class ReentrantLockConditionLearn {
	public static void main(String[] args) {
		Depot2 depot = new Depot2(100);
		Producer2 producer = new Producer2(depot);
		Customer2 customer = new Customer2(depot);
		
		producer.produce(60);
		producer.produce(120);
		customer.consume(90);
		customer.consume(150);
		producer.produce(110);
	}
}
