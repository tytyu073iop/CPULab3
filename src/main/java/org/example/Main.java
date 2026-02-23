package org.example;


import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger coresAvaliable = new AtomicInteger(0);
    static void main() {
        // posl

        // parallel
        for (int i = 2; i <= 4; i *= 2) {
            coresAvaliable.set(i);

            for (int j = 1_000_000; j <= 100_000_000; j *= 10) {
                for (int w = 3; w <= j; w += 2) {
                    while (true) {
                        if (coresAvaliable.get() > 0) {

                        }
                    }
                }
            }
        }
    }
}
