package juc.thread;

public class ThreadPropertiesAndMethod {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println("Hello Thread");
			System.out.println("=".repeat(60));
		});
		thread.start();

		System.out.println("thread.getName() = " + thread.getName());
		System.out.println("thread.getState() = " + thread.getState());
		System.out.println("thread.getId() = " + thread.getId());
		System.out.println("thread.getThreadGroup() = " + thread.getThreadGroup());
		System.out.println("thread.getPriority() = " + thread.getPriority());
		System.out.println("thread.getContextClassLoader() = " + thread.getContextClassLoader());
		System.out.println("thread.getUncaughtExceptionHandler() = " + thread.getUncaughtExceptionHandler());
		System.out.println("=".repeat(60));
	}
}
