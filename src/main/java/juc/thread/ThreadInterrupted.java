package juc.thread;

public class ThreadInterrupted {

	public static void main(String[] args) {
		Thread thread = new Thread(new ThreadA(),"ThreadA");
		thread.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ThreadA isInterrupted： " + thread.isInterrupted());
		thread.interrupt();
		System.out.println("ThreadA isInterrupted： " + thread.isInterrupted());


	}

	static class ThreadA implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(5_000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "被中断了");
			}
		}
	}
}
