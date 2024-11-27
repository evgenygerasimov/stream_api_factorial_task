package org.example;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    private long n;
    public FactorialTask(long n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return 1;
        }
        long mid = n / 2;
        FactorialTask lowerHalfTask = new FactorialTask(mid);
        lowerHalfTask.fork();
        long upperHalfResult = multiply(mid + 1, n);
        long lowerHalfResult = lowerHalfTask.join();
        return Math.toIntExact(lowerHalfResult * upperHalfResult);
    }

    private long multiply(long start, long end) {
        long result = 1;
        for (long i = start; i <= end; i++) {
            result *= i;
        }
        return result;
    }
}
