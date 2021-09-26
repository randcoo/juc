package juc.example;

import java.util.concurrent.TimeUnit;

public class OddEvenTest {

	public static void main(String[] args) {
		Num num = new Num();
		Odd odd = new Odd(num);
		Even even = new Even(num);
		new Thread(odd).start();
		new Thread(even).start();

	}

	public static class Num {
		int i = 0;
	}

	static class Odd implements Runnable {
		private Num num;

		public Odd(Num num) {
			this.num = num;
		}

		@Override
		public void run() {
			synchronized (num) {
				while (num.i < 100) {

					if (num.i % 2 == 1) {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("偶数：" + num.i);
						num.i++;
						num.notifyAll();
					} else {
						try {
							num.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}
	}

	static class Even implements Runnable {
		private Num num;

		public Even(Num num) {
			this.num = num;
		}

		@Override
		public void run() {
			synchronized (num) {
				while (num.i <= 100) {

					if (num.i % 2 == 0) {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("奇数：" + num.i);
						num.i++;
						num.notifyAll();
					} else {
						try {
							num.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
