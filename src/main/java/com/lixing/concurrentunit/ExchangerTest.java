package com.lixing.concurrentunit;

import java.util.concurrent.Exchanger;

/**
 * Created by Toephy on 2016/6/3 14:37
 */
public class ExchangerTest {

    static class ExchangerRunnable implements Runnable {

        int threadId;
        Exchanger<Object> exchanger;
        Object object;

        public ExchangerRunnable(int threadId, Exchanger<Object> exchanger, Object object) {
            this.threadId = threadId;
            this.exchanger = exchanger;
            this.object = object;
        }

        @Override
        public void run() {
            try {
                System.out.println(threadId + " before: object = " + object);
                if (threadId == 2)
                    Thread.sleep(5000);
                Object exchangeObject = exchanger.exchange(object);
                System.out.println(threadId + " after: object = " + object);
                System.out.println(threadId + " exchangeObject = " + exchangeObject);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Exchanger<Object> exchanger = new Exchanger<Object>();
        Thread t1 = new Thread(new ExchangerRunnable(1, exchanger, "A"));
        Thread t2 = new Thread(new ExchangerRunnable(2, exchanger, "B"));
        t1.start();
        t2.start();
    }
}
