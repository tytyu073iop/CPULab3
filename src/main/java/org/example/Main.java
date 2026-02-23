package org.example;


import java.util.concurrent.atomic.AtomicInteger;

import static org.example.ComputingThread.isPrime;

public class Main {
    static AtomicInteger coresAvaliable = new AtomicInteger(0);
    static void main() {
        // single Thread
        for (int i = 1_000_000; i <= 100_000_000; i *= 10) {
            System.out.println("Starting single threaded job");
            long start = System.currentTimeMillis();
            int count = 0;
            for (int j = 2; j <= i; j++) {
                if (isPrime(j)) {
                    count++;
                }
            }
            long end = System.currentTimeMillis();
            System.out.printf("there was: %d primes; ", count);
            System.out.printf("Single threaded was computing for: %.3f seconds\n", (end - start) / 1000.0);
        }


        // parallel
        for (int i = 2; i <= 4; i *= 2) {
            long start = System.currentTimeMillis();
            coresAvaliable.set(i);
            int counter = 0;
            ComputingThread treads[] = new ComputingThread[2000];

            for (int j = 1_000_000; j <= 100_000_000; j *= 10) {
                counter = 0;

                for (int w = 2; w <= j;) {
                    while (true) {
                        if (coresAvaliable.get() > 0) {
                            decrementCores();
                            treads[counter++] = new ComputingThread(w, w + 100_000 > j ? j + 1 : w + 100_000);
                            treads[counter - 1].start();
                            w += 100_000;
                            break;
                        }
                    }
                }

                while (coresAvaliable.get() != i) {}

                int sum = 0;
                for (int k = 0; k < counter; k++) {
                    sum += treads[k].getCounter();
                }

                long end = System.currentTimeMillis();
                System.out.printf("Time: %.3f\n", (end - start) / 1000.0);
                System.out.printf("Number of cores: %d\n", i);
                System.out.printf("Prime numbers: %d\n" ,sum);

            }
        }
    }

    static public void incrementCores() {
        coresAvaliable.incrementAndGet();
    }

    static public void decrementCores() {
        coresAvaliable.decrementAndGet();
    }
}
