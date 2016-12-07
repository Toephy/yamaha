package com.lixing.concurrentunit;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Toephy on 2016.10.12 11:09
 */
public class FutureTest {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);

            FutureTask<Integer> result = new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(1000);
                    int i = new Random().nextInt();
                    System.out.println("random = " + i);
                    return i;
                }
            });

            executorService.submit(result);

            //Future<Integer> result = executorService.submit(new Callable<Integer>() {
            //    @Override
            //    public Integer call() throws Exception {
            //        Thread.sleep(1000);
            //        int i = new Random().nextInt();
            //        System.out.println("random = " + i);
            //        return i;
            //    }
            //});

            System.out.println("to get...");
            Integer integer = null;
            try {
                integer = result.get(2000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("first get:" + integer);
            Thread.sleep(1000);
            Integer integer2 = result.get();
            System.out.println("second get:" + integer2);

            executorService.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
