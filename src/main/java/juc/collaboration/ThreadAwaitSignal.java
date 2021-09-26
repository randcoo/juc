package juc.collaboration;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import juc.ThreadUtil;

/**
 * 使用await和signal/signalAll来进行线程间通信
 *
 * java.util.concurrent 类库中提供了 Condition 类来实现线程之间的协调，可以在 Condition 上调用 await() 方法使线程等待，
 * 其它线程调用 signal() 或 signalAll() 方法唤醒等待的线程。相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。
 */
public class ThreadAwaitSignal {

	public static void main(String[] args) {
		AwaitSignalExample example = new AwaitSignalExample();
		new Thread(example::after).start();
		ThreadUtil.sleep(2_000);
		new Thread(example::before).start();
	}

	static class AwaitSignalExample {

		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		public void before() {
			System.out.println(Thread.currentThread().getName() + " enter before method in " + new Date());
			lock.lock();
			try {
				ThreadUtil.sleep(10_000);
				condition.signal();
			} finally {
				lock.unlock();
				System.out.println(Thread.currentThread().getName() + " exit before method in " + new Date());
			}

		}

		public void after() {
			System.out.println(Thread.currentThread().getName() + " enter after method in " + new Date());
			lock.lock();
			try {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
				System.out.println(Thread.currentThread().getName() + " exit after method in " + new Date());
			}
		}
	}
}
