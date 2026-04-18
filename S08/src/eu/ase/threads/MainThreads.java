package eu.ase.threads;

import java.sql.Time;
import java.util.concurrent.*;

public class MainThreads {
    public static void main(String[] args) {
        HelloThread tJ5 = new HelloThread("Th01 Java 1.1... *");
        // tJ5.run();
        tJ5.start();
        try {
            tJ5.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        HelloRunnable tRunnable = new HelloRunnable();
        Thread tw_tJ5Plus = new Thread(tRunnable, "Th02 Java 1.1 Plus...  ");
        tw_tJ5Plus.start();

        Runnable taskJ7 = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("Hello J7 " + name);
            }
        };

        Thread twj7 = new Thread(taskJ7, "Th03 Java 7.. *");
        twj7.start();

        Runnable taskJ8 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Hello J7 " + name);
        };

        Thread twJ8 = new Thread(taskJ8, "Th04 Java 8...");
        twJ8.start();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(taskJ8);
        executorService.submit(taskJ8);

        try {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("task interrupted");
        } finally {
            if(!executorService.isTerminated()) {
                System.out.println("cancel non-finished tasks");
            }
            executorService.shutdownNow();
            System.out.println("shutdown finished");
        }

        ExecutorService executorService4FC = Executors.newFixedThreadPool(1);
        Callable<Integer> taskCallable = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 105;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task callable interruped!", e);
            }
        };

        Future<Integer> future = executorService4FC.submit(taskCallable);
        try {
            Integer result = future.get();
            System.out.println("result = " + result);
        } catch(InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        try {
            executorService4FC.shutdown();
            executorService4FC.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        } finally {
            if(!executorService4FC.isTerminated()) {
                System.out.println("cancel non-finished tasks");
            }
            executorService4FC.shutdownNow();
            System.out.println("shutdown finished");
        }

        Runnable taskJ19 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Hello J19" + name);
        };

        Thread twJ19 = Thread.ofVirtual().name("VirtualThread").unstarted(taskJ19);
        twJ19.start();

        System.out.println("CPU cores: " + VirtualThreadPlayground.numberOfCores());
        VirtualThreadPlayground.concurrentMorningRoutineUsingExecutorsWithName();
    } //end of main method
}
