package com.concurrent.learn;

// lock type : instance lock and class lock
class Something {
	public synchronized void isSyncA() {
		try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // ÐÝÃß100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncA");
            }
        }catch (InterruptedException ie) {
        }
	}
	public synchronized void isSyncB() {
		try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // ÐÝÃß100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncB");
            }
        }catch (InterruptedException ie) {
        }
	}
	public static synchronized void cSyncA() {
		try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // ÐÝÃß100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncA");
            }
        }catch (InterruptedException ie) {
        }
	}
	public static synchronized void cSyncB() {
		try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // ÐÝÃß100ms
                System.out.println(Thread.currentThread().getName()+" : cSyncB");
            }
        }catch (InterruptedException ie) {
        }
	}
}

public class LockType {
	static Something x = new Something();
	static Something y = new Something();
	
	private static void lockTest1() {
		Thread t11 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.isSyncA();
			}
		}, "t11");
		Thread t12 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.isSyncB();
			}
		}, "t12");
		t11.start();
		t12.start();
	}
	
	private static void lockTest2() {
		Thread t21 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.isSyncA();
			}
		}, "t21");
		Thread t22 = new Thread(new Runnable() {
			@Override
			public void run() {
				y.isSyncA();
			}
		}, "t22");
		t21.start();
		t22.start();
	}
	
	private static void lockTest3() {
		Thread t31 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.cSyncA();
			}
		}, "t31");
		Thread t32 = new Thread(new Runnable() {
			@Override
			public void run() {
				y.cSyncB();
			}
		}, "t32");
		t31.start();
		t32.start();
	}
	
	private static void lockTest4() {
		Thread t41 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.isSyncA();
			}
		}, "t41");
		Thread t42 = new Thread(new Runnable() {
			@Override
			public void run() {
				Something.cSyncA();
			}
		}, "t42");
		t41.start();
		t42.start();
	}
	
	public static void main(String[] args) {
//		lockTest1();
//		lockTest2();
//		lockTest3();
		lockTest4();
	}
}
