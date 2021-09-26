package juc.sync;

/**
 * synchronized修饰非静态方法（对象锁）
 * 1. 作用同一个类的实例，如果一个类的不同实例在多线程的环境下访问，则不受影响，相互独立
 */
public class SynchronizedMethod {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("NonSyncMethod" + "=".repeat(50));
		NonSyncMethod nonSyncMethod = new NonSyncMethod();
		new Thread(() -> nonSyncMethod.count(), "Thread-A").start();
		new Thread(() -> nonSyncMethod.count(), "Thread-B").start();
		new Thread(() -> nonSyncMethod.count(), "Thread-C").start();

		Thread.sleep(2_000);
		System.out.println("SyncMethod" + "=".repeat(50));
		SyncMethod syncMethod = new SyncMethod();
		new Thread(() -> syncMethod.count(), "SyncThread-A").start();
		new Thread(() -> syncMethod.count(), "SyncThread-B").start();
		new Thread(() -> syncMethod.count(), "SyncThread-C").start();
	}

	final static class NonSyncMethod {
		public void count() {
			for (int i = 0; i < 10; i++) {
				System.out.print(Thread.currentThread().getName() + " " + i + " ");
			}
			System.out.println();
		}
	}

	static class SyncMethod {
		public synchronized void count() {
			for (int i = 0; i < 10; i++) {
				System.out.print(Thread.currentThread().getName() + " " + i + " ");
			}
			System.out.println();
		}
	}
}
