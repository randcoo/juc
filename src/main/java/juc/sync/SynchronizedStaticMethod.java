package juc.sync;

import juc.ThreadUtil;

/**
 * 同步静态方法作用于整个类（属于类锁）
 *  1.synchronized修饰的同一个类的所有静态方法之间互斥
 *  2.synchronized修饰的同一个类的所有静态方法与普通静态方法，普通方法之间不互斥
 */
public class SynchronizedStaticMethod {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("SyncStaticMethod" + "=".repeat(50));
		SyncStaticMethod syncStaticMethod = new SyncStaticMethod();
		new Thread(() -> SyncStaticMethod.syncStaticMethod1(), "Thread-syncStaticMethod1").start();
		new Thread(() -> SyncStaticMethod.syncStaticMethod2(), "Thread-syncStaticMethod2").start();
		new Thread(() -> SyncStaticMethod.staticMethod3(), "Thread-staticMethod3").start();
		new Thread(() -> syncStaticMethod.normalMethod4(), "Thread-normalMethod4").start();
	}

	final static class SyncStaticMethod {

		public synchronized static void syncStaticMethod1() {
			for (int i = 0; i < 5; i++) {
				ThreadUtil.sleep(1_000);
				System.out.println(Thread.currentThread().getName() + " staticMethod1 " + i + " ");
			}
			System.out.println("=".repeat(66));
		}

		public synchronized static void syncStaticMethod2() {
			for (int i = 0; i < 5; i++) {
				ThreadUtil.sleep(1_000);
				System.out.println(Thread.currentThread().getName() + " staticMethod2 " + i + " ");
			}
			System.out.println("=".repeat(66));
		}

		public static void staticMethod3() {
			for (int i = 0; i < 5; i++) {
				ThreadUtil.sleep(1_000);
				System.out.println(Thread.currentThread().getName() + " staticMethod3 " + i + " ");
			}
			System.out.println("=".repeat(66));
		}

		public void normalMethod4() {
			for (int i = 0; i < 10; i++) {
				ThreadUtil.sleep(500);
				System.out.println(Thread.currentThread().getName() + " normalMethod4 " + i + " ");
			}
			System.out.println("=".repeat(66));
		}
	}
}
