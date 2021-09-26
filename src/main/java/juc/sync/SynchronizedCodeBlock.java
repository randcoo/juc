package juc.sync;

/**
 * synchronized用于同步代码块（对象锁）
 *   针对一个类的同一个实例，存在并发访问该实例方法的情况下，会同步进行
 */
public class SynchronizedCodeBlock {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("=".repeat(60));
		// A B 交替执行
		CodeBlock codeBlock = new CodeBlock();
		new Thread(() -> codeBlock.count(), "Thread-A").start();
		new Thread(() -> codeBlock.count(), "Thread-B").start();

		Thread.sleep(2000);

		System.out.println("=".repeat(60));
		// A B 顺序执行，只会有一个线程执行完了之后才会有下一个线程执行
		SyncCodeBlock syncCodeBlock = new SyncCodeBlock();
		new Thread(() -> syncCodeBlock.count(), "Sync-Thread-A").start();
		new Thread(() -> syncCodeBlock.count(), "Sync-Thread-B").start();
	}

	static class CodeBlock {
		public void count() {
			for (int i = 0; i < 10; i++) {
				System.out.print(Thread.currentThread().getName() + " " + i + " ");
			}
			System.out.println();
		}
	}

	static class SyncCodeBlock {
		private Object object = new Object();

		public void count() {
			synchronized (object) {
				for (int i = 0; i < 10; i++) {
					System.out.print(Thread.currentThread().getName() + " " + i + " ");
				}
				System.out.println();
			}
		}
	}
}
