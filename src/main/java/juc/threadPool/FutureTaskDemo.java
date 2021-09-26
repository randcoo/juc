package juc.threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskDemo {

	public static void main(String[] args) throws InterruptedException {
		FutureTask<String> task = new FutureTask<>(() -> {
			TimeUnit.SECONDS.sleep(10);
			return "my future task";
		});
		ExecutorService es = Executors.newSingleThreadExecutor(r -> new Thread(r,"SingleThreadExecutor"));
		es.submit(task);
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					String s = task.get();
					System.out.println(Thread.currentThread().getName() + " get result " + s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}, "Get Thread " + i).start();
		}
		System.out.println("task = " + task);
		TimeUnit.SECONDS.sleep(50);
	}

}
