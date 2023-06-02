package RellenarVectorPrimos;

import java.util.Arrays;
import java.util.Random;

public class EjercicioPrimos {
	private static final int NUM_PRIMES = 256;
	private static final int MAX_NUM = 1000;

	public static void main(String[] args) {
		int[] primes = generateRandomPrimes();
		boolean[] results = new boolean[NUM_PRIMES / 2];

		int numThreads = Runtime.getRuntime().availableProcessors();
		PrimeChecker[] checkers = new PrimeChecker[numThreads];
		Thread[] threads = new Thread[numThreads];

		for (int i = 0; i < numThreads; i++) {
			int start = i * (NUM_PRIMES / 2) / numThreads;
			int end = (i + 1) * (NUM_PRIMES / 2) / numThreads;
			checkers[i] = new PrimeChecker(primes, results, start, end);
			threads[i] = new Thread(checkers[i]);
			threads[i].start();
		}

		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Array of " + NUM_PRIMES + " random primes between 0 and " + MAX_NUM + ":");
		System.out.println(Arrays.toString(primes));

		System.out.println("Results of prime sum checks:");
		System.out.println(Arrays.toString(results));
	}

	private static int[] generateRandomPrimes() {
		int[] primes = new int[NUM_PRIMES];
		int count = 0;
		Random random = new Random();

		for (int i = 0; i < MAX_NUM && count < NUM_PRIMES; i++) {
			boolean isPrime = true;

			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				primes[count++] = i;
			}
		}

		for (int i = 0; i < NUM_PRIMES; i++) {
			int j = random.nextInt(NUM_PRIMES);
			int temp = primes[i];
			primes[i] = primes[j];
			primes[j] = temp;
		}

		return primes;
	}
}
	class PrimeChecker implements Runnable {
		private int[] primes;
		private boolean[] results;
		private int start;
		private int end;

		public PrimeChecker(int[] primes, boolean[] results, int start, int end) {
			this.primes = primes;
			this.results = results;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			for (int i = start; i < end; i++) {
				int sum = primes[i * 2] + primes[i * 2 + 1];
				results[i] = isPrime(sum);
			}
		}

		private boolean isPrime(int n) {
			if (n <= 1) {
				return false;
			}
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0) {
					return false;
				}
			}
			return true;
		}
	}
