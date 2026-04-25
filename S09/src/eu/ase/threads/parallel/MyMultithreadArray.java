package eu.ase.threads.parallel;

public class MyMultithreadArray implements Runnable {
    private int[] v;
    private int startIdx;
    private int stopIdx;
    private Long sum;

    public MyMultithreadArray(int[] v, int startIdx, int stopIdx) {
        this.v = v;
        this.startIdx = startIdx;
        this.stopIdx = stopIdx;
    }

    @Override
    public void run() {
        long s = 0;
        for(int idx = this.startIdx; idx <= this.stopIdx; idx++) {
            s += this.v[idx];
        }
        this.sum = s;
    }

    public long getSum() {
        return this.sum;
    }


}

