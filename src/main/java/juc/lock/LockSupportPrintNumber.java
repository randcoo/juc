package juc.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportPrintNumber {

	static class N {
		private int num = 1;
	}

	static class OddThread extends Thread {

		private N n;

		private EvenThread evenThread;

		public OddThread(N n) {
			this.n = n;

		}

		public void setEvenThread(EvenThread evenThread) {
			this.evenThread = evenThread;
		}

		@Override
		public void run() {
			while (n.num < 100) {
				String s = "odd wait";
				System.out.println("s.toString() = " + s.toString());
				LockSupport.park(s);
				if (n.num % 2 == 1) {
					System.out.println(n.num);
					n.num++;
					LockSupport.unpark(evenThread);
				}
			}
		}
	}

	static class EvenThread extends Thread {
		private N n;
		private OddThread oddThread;

		public EvenThread(N n) {
			this.n = n;
		}

		public void setOddThread(OddThread oddThread) {
			this.oddThread = oddThread;
		}

		@Override
		public void run() {
			while (n.num < 100) {
				System.out.println("park" + n.num);
				LockSupport.park("even wait");
				// LockSupport.park("odd wait");
				if (n.num % 2 == 0) {
					System.out.println(n.num);
					n.num++;
					LockSupport.unpark(oddThread);
				}
			}
		}
	}

	public static void main(String[] args) {
		N n = new N();
		OddThread oddThread = new OddThread(n);


		EvenThread evenThread = new EvenThread(n);
		oddThread.setName("odd");
		oddThread.setEvenThread(evenThread);
		evenThread.setName("even");
		evenThread.setOddThread(oddThread);
		oddThread.start();
		evenThread.start();
		// LockSupport.unpark(oddThread);
	}

}
