package com.lixing.concurrentunit;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Toephy on 2016/6/3 12:17
 */
public class CyclicBarrierTest {

    static class Runner implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private int runnerId;

        public Runner(int runnerId, CyclicBarrier cyclicBarrier) {
            this.runnerId = runnerId;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(runnerId + " thread run step 1");
                if (runnerId == 1) {
                    Thread.sleep(2000);
                }
                if (cyclicBarrier != null) {
                    cyclicBarrier.await();
                }
                System.out.println(runnerId + " thread run step 2.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("cyclicBarrier run.");
            }
        });
        Thread t1 = new Thread(new Runner(1, cyclicBarrier));
        Thread t2 = new Thread(new Runner(2, cyclicBarrier));
        t1.start();
        t2.start();
    }
}
