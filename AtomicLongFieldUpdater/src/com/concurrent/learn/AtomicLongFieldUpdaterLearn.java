package com.concurrent.learn;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

class Person {
	volatile long id;
	public Person(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}

public class AtomicLongFieldUpdaterLearn {
	public static void main(String[] args) {
		Class<Person> cls = Person.class;
		AtomicLongFieldUpdater<Person> mAtoLong = AtomicLongFieldUpdater.newUpdater(cls, "id");
		Person person = new Person(12345678L);
		
		mAtoLong.compareAndSet(person, 12345678L, 1000);
		System.out.println("id=" + person.getId());
	}
}
