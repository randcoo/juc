package juc.collaboration;

import java.util.concurrent.TimeUnit;

import juc.ThreadUtil;

/**
 * 线程协作：join
 * 在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束
 */
public class ThreadJoinDemo {

	public static void main(String[] args) {
		ThreadB b = new ThreadB();
		ThreadA a = new ThreadA(b);
		b.start();
		ThreadUtil.sleep(200);
		a.start();
	}

	static class ThreadA extends Thread {
		private ThreadB b;

		public ThreadA(ThreadB b) {
			this.b = b;
		}

		@Override
		public void run() {
			System.out.println("ThreadA start...");
			ThreadUtil.sleep(1_000);
			try {
				b.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ThreadUtil.sleep(1_000);
			System.out.println("ThreadA finish");
		}
	}

	static class ThreadB extends Thread {
		@Override
		public void run() {
			System.out.println("ThreadB start...");
			ThreadUtil.sleep(2_0000);
			System.out.println("ThreadB finish");
		}
	}
}
