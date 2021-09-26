package juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadCreate {

	public static void main(String[] args) {
		new MyExtendsThread().start();
		new Thread(new MyRunnableThread()).start();
		MyCallableThread callableThread = new MyCallableThread();
		FutureTask task = new FutureTask<Void>(callableThread);
		new Thread(task).start();
	}

	static class MyExtendsThread extends Thread {
		@Override
		public void run() {
			System.out.println("通过继承Thread类创建线程");
		}
	}

	static class MyRunnableThread implements Runnable {
		@Override
		public void run() {
			System.out.println("通过实现Runnable接口创建线程");
		}
	}

	static class MyCallableThread implements Callable<Void> {
		@Override
		public Void call() throws Exception {
			System.out.println("通过实现Callable接口创建线程");
			return null;
		}
	}
}
