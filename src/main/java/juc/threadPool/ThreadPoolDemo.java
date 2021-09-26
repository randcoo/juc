package juc.threadPool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class ThreadPoolDemo {

	public static void main(String[] args) {
		// ExecutorService es = Executors.newFixedThreadPool(3);
		/**
		 * 自定义线程池
		 * 	 - 核心线程数： 2
		 * 	 - 最大线程数：10
		 * 	 - 线程存活时间：10s
		 * 	 - 任务队列长度：50
		 * 	 - 任务拒绝策略：丢弃
		 * ----------------------------
		 *  能够同时运行的最大务数：10
		 *  能够支持的最大任务数： 2 + 50 + （10 -2） ==> 60个任务，当提交第61个任务的时候会抛出任务被拒绝的异常
		 */
		ThreadPoolExecutor es = new ThreadPoolExecutor(2, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(50),
			new ThreadPoolExecutor.AbortPolicy());
		new Thread(() -> {
			while (true) {
				System.out.println();
				int queueSize = es.getQueue().size();
				System.out.println("当前排队线程数：" + queueSize);
				int poolSize = es.getPoolSize();
				System.out.println("总线程数： " + poolSize);
				int corePoolSize = es.getCorePoolSize();
				System.out.println("核心线程数：" + corePoolSize);
				int activeCount = es.getActiveCount();
				System.out.println("当前活动线程数：" + activeCount);

				long completedTaskCount = es.getCompletedTaskCount();
				System.out.println("执行完成线程数：" + completedTaskCount);

				long taskCount = es.getTaskCount();
				System.out.println("总任务数：" + taskCount);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("=================================================================");
			}
		}).start();
		for (int i = 1; i <= 60; i++) {
			es.submit(() -> {
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "========> hhhh");
			});
		}


	}
}
