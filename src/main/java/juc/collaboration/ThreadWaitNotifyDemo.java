package juc.collaboration;

import juc.ThreadUtil;

/**
 * 使用wait和notify/notifyAll进行线程间通信
 *
 * 调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
 * 它们都属于 Object 的一部分，而不属于 Thread。 只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
 * 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
 */
public class ThreadWaitNotifyDemo {

	public static void main(String[] args) {
		WaitNotifyExample example = new WaitNotifyExample();
		new Thread(() -> example.after()).start();
		ThreadUtil.sleep(1_0000);
		new Thread(() -> example.before()).start();

	}

	static class WaitNotifyExample{

		public void before(){
			synchronized (this){
				System.out.println("before");
				notify();
			}
		}

		public void after(){
			synchronized (this){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("after");
			}
		}
	}

}
