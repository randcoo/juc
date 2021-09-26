package juc.sync;

/**
 * synchronized用于同步XXX.class（类锁）
 * 1. 属于类锁
 */
public class SynchronizedClass {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("NonSyncClass" + "=".repeat(50));
		NonSyncClass nonSyncClass1 = new NonSyncClass();
		NonSyncClass nonSyncClass2 = new NonSyncClass();
		NonSyncClass nonSyncClass3 = new NonSyncClass();
		new Thread(() -> nonSyncClass1.count(), "nonSyncClass1-Thread").start();
		new Thread(() -> nonSyncClass2.count(), "nonSyncClass2-Thread").start();
		new Thread(() -> nonSyncClass3.count(), "nonSyncClass3-Thread").start();

		Thread.sleep(2_000);
		System.out.println("SyncClass" + "=".repeat(50));
		SyncClass syncClass1 = new SyncClass();
		SyncClass syncClass2 = new SyncClass();
		SyncClass syncClass3 = new SyncClass();
		new Thread(() -> syncClass1.count(), "syncClass1-Thread").start();
		new Thread(() -> syncClass2.count(), "syncClass2-Thread").start();
		new Thread(() -> syncClass3.count(), "syncClass3-Thread").start();
	}

	final static class SyncClass {

		public void count() {
			// 这里可以是任意一个class，因为class在JVM中只会存在一份
			synchronized (SyncClass.class) {
				for (int i = 0; i < 10; i++) {
					System.out.print(Thread.currentThread().getName() + " " + i + " ");
				}
				System.out.println();
			}
		}
	}

	final static class NonSyncClass {
		public void count() {
			for (int i = 0; i < 10; i++) {
				System.out.print(Thread.currentThread().getName() + " " + i + " ");
			}
			System.out.println();
		}
	}
}
