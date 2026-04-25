package eu.ase.threads.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class SumForkJoin  extends RecursiveTask<Long> {
    int[] array;
    int low;
    int high;
    static final int SEQUENTIAL_THRESHOLD = 5000;

    public SumForkJoin(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Long compute() {
        if(high - low <= SEQUENTIAL_THRESHOLD) {
            long sum = 0;
            for(int i= low ; i < high; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = low + (high - low) / 2;
            SumForkJoin left = new SumForkJoin(array, low, mid);
            SumForkJoin right = new SumForkJoin(array, mid, high);
            left.fork();
            Long rightAns = right.compute();
            Long leftAns = left.join();
            return rightAns + leftAns;
        }
    }

    public static long sumArrays(int[] array) {
        return ForkJoinPool.commonPool().invoke(new SumForkJoin(array, 0, array.length));
    }
}
