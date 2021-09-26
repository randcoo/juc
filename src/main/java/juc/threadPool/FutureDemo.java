package juc.threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureDemo {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask future = new FutureTask(() -> {
			TimeUnit.SECONDS.sleep(10);
			System.out.println("hhhhh");
			return "from runnable";
		});
		Thread thread = new Thread(future);
		thread.start();
		System.out.println(future.get());
	}
}
