package org.example;

public class ComputingThread extends Thread {

    int min;
    int max;
    int counter = 0;

    public int getCounter() {
        return counter;
    }

    ComputingThread(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = min; i < max; i++) {
            if (isPrime(i)) {
                counter++;
            }
        }
        Main.incrementCores();
    }

    public static boolean isPrime(int x) {
        assert x > 1;
        int top = (int)Math.sqrt(x);
        for (int i = 2; i <= top; i++)
            if ( x % i == 0 )
                return false;
        return true;
    }
}
