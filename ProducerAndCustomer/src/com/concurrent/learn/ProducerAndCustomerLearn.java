package com.concurrent.learn;

class Depot {
	// volume size
	private int capacity;
	// actual size
	private int size;
	
	public Depot(int capacity) {
		this.capacity = capacity;
		this.size = 0;
	}
	
	public synchronized void produce(int val) {
		try {
			int left = val;
			while (left > 0) {
				while (size >= capacity) {
					wait();
				}
				int inc = (size + left) > capacity ? (capacity - size) : left;
				size += inc;
				left -= inc;
				System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, inc, size);
				
				notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void consume(int val) {
		try {
			int left = val;
			while (left > 0) {
				while (size <= 0) {
					wait();
				}
				int dec = (size < left) ? size : left;
				size -= dec;
				left -= dec;
				System.out.printf("%s consume(%3d) --> left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
				
				notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "capacity:" + capacity + ", actual size: " + size;
	}
}

class Producer {
	private Depot depot;
	
	public Producer(Depot depot) {
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

class Customer {
	private Depot depot;
	
	public Customer(Depot depot) {
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

public class ProducerAndCustomerLearn {
	public static void main(String[] args) {
		// synchronized/notify: waiting in line to obtain object lock
		// wait: waiting in the object's waiting area
		Depot depot = new Depot(100);
		Producer producer = new Producer(depot);
		Customer customer = new Customer(depot);
		
		producer.produce(60);
		producer.produce(120);
		customer.consume(80);
		customer.consume(150);
		producer.produce(110);
	}
}
