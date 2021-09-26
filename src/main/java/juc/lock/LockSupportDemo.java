package juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

	public static void main(String[] args) throws Exception {

		TestThread myThread = new TestThread(Thread.currentThread());
		myThread.start();
		Thread.sleep(1000);
		System.out.println("before park");
		// 获取许可
		LockSupport.park("ParkAndUnparkDemo");
		System.out.println("after park");
	}

	static class TestThread extends Thread {

		private Object object;

		public TestThread(Object object) {
			this.object = object;
		}

		@Override
		public void run() {
			System.out.println("before unpark");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 获取blocker
			System.out.println("Blocker info " + LockSupport.getBlocker((Thread)object));
			// 释放许可
			LockSupport.unpark((Thread)object);
			// 休眠500ms，保证先执行park中的setBlocker(t, null);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 再次获取blocker
			System.out.println("Blocker info " + LockSupport.getBlocker((Thread)object));

			System.out.println("after unpark");

		}
	}
}

