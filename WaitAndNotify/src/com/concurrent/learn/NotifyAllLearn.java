package com.concurrent.learn;

public class NotifyAllLearn {

    private static Object obj = new Object();
    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        ThreadA t3 = new ThreadA("t3");
        t1.start();
        t2.start();
        t3.start();

        try {
            System.out.println(Thread.currentThread().getName()+" sleep(3000)");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized(obj) {
            System.out.println(Thread.currentThread().getName()+" notifyAll()");
            // notify all waiting thread on obj
            obj.notifyAll();
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name){
            super(name);
        }

        public void run() {
            synchronized (obj) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    // add current thread to obj's waiting thread
                    obj.wait();

                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}