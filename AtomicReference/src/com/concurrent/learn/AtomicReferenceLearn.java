package com.concurrent.learn;

import java.util.concurrent.atomic.AtomicReference;

class Person {
	volatile long id;
	public Person(long id) {
		this.id = id;
	}
	public String toString() {
		return "id: " + id;
	}
}

public class AtomicReferenceLearn {
	public static void main(String[] args) {
		Person p1 = new Person(101);
		Person p2 = new Person(102);
		
		AtomicReference<Person> ar = new AtomicReference<Person>(p1);
		ar.compareAndSet(p1, p2);
		
		Person p3 = ar.get();
		System.out.println("p3 is " + p3);
		System.out.println("p3.equals(p1)=" + p3.equals(p1));
	}
}
