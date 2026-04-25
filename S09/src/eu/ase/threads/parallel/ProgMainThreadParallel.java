package eu.ase.threads.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProgMainThreadParallel {
    private static final int NTHREADS = 4;
    public static void main(String[] args){
        int dimVect = 40_000_000;

        int[] v = new int [dimVect];
        Long sum = 0L;
        //Long sum = Long.valueOf(0);

        for(int i=0;i< dimVect;i++) {
            v[i] = i+1;
        }

        long startTime = 0;
        long stopTime = 0;
        int startIdx = 0;
        int stopIdx = 0;

        startTime = System.currentTimeMillis();

        for(int i = 0; i < dimVect; i++) {
            sum += v[i];
        }
        stopTime = System.currentTimeMillis();
        System.out.println("1. Seq time = " + (stopTime-startTime) + ", sum = " + sum);

        Thread[] vectThreads = new Thread[NTHREADS];
        MyMultithreadArray[] vectTasks = new MyMultithreadArray[NTHREADS];
        Long[] vectSum = new Long[NTHREADS];

        startTime = System.currentTimeMillis();

        for(int i=0; i < NTHREADS; i++) {
            startIdx = i * (dimVect/NTHREADS);
            stopIdx = (i+1) * (dimVect/NTHREADS) - 1;
            vectTasks[i] = new MyMultithreadArray(v, startIdx, stopIdx);
            vectThreads[i] = new Thread(vectTasks[i]);
        }

        for(int i = 0; i < NTHREADS; i++) {
            vectThreads[i].start();
        }

        for(int i = 0; i < NTHREADS; i++) {
            try {
                vectThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sum = 0L;
        for(int i = 0 ; i < NTHREADS ; i++) {
            sum += vectTasks[i].getSum();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("2. MultiThread Standard Time = " + (stopTime-startTime) + ", sum = " + sum);

        startTime = System.currentTimeMillis();
        ExecutorService execThreadPool = Executors.newFixedThreadPool(NTHREADS);
        MyMultithreadArray[] workerTasks = new MyMultithreadArray[NTHREADS];

        for(int i=0; i< NTHREADS; i++) {
            startIdx = i * (dimVect/NTHREADS);
            stopIdx = (i+1) * (dimVect/NTHREADS) - 1;

            vectSum[i] = 0L;
            workerTasks[i] = new MyMultithreadArray(v, startIdx, stopIdx);
            execThreadPool.execute(workerTasks[i]);
        }

        try {
            execThreadPool.shutdown();
            execThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        sum = 0L;
        for(int i=0 ; i< NTHREADS; i++) {
            vectSum[i] = workerTasks[i].getSum();
            sum += vectSum[i];
        }
        stopTime = System.currentTimeMillis();

        System.out.println("3. MultiThread Executor-Service Time = " + (stopTime-startTime) + ", sum = " + sum);

        startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Long>> futures = new ArrayList<>();

        for(int i=0 ;i < NTHREADS; i++) {
            startIdx = i * (dimVect/NTHREADS);
            stopIdx = (i+1) * (dimVect/NTHREADS) - 1;

            Callable<Long> worker = new MyCallableArray(v, startIdx, stopIdx);
            Future<Long> submit = executor.submit(worker);
            futures.add(submit);
        }

        sum = 0l;
        for(Future<Long> future: futures) {
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stopTime = System.currentTimeMillis();
        System.out.println("4. Array Multithreading - thread pool - Callable & Future Time = " + (stopTime-startTime) + ", sum= " + sum);

        startTime = System.currentTimeMillis();
        sum = SumForkJoin.sumArrays(v);
        stopTime = System.currentTimeMillis();
        System.out.println("5. Fork-Join Parallel array time = " + (stopTime-startTime) + ", sum = " + sum);

        sum = 0L;
        startTime = System.currentTimeMillis();
        MyMultithreadArray[] vectVirtualThreads = new MyMultithreadArray[NTHREADS];
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int i=0; i < NTHREADS; i++) {
                startIdx = i* (dimVect/NTHREADS);
                stopIdx = (i+1) * (dimVect/NTHREADS) - 1;
                vectVirtualThreads[i] = new MyMultithreadArray(v, startIdx, stopIdx);
                executorService.execute(vectVirtualThreads[i]);
            }
        }

        for(int i = 0 ; i< NTHREADS; i++) {
            sum += vectVirtualThreads[i].getSum();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("6. Virtual threads Time = " + (stopTime-startTime) + ", sum = " + sum);
    } //end of main

}
