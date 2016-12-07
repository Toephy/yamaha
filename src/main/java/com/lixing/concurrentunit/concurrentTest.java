package com.lixing.concurrentunit;

/**
 * Created by Toephy on 2016/6/3 10:09
 */
public class concurrentTest {

    private static final Object lock = new Object();

    public void method1() {
        synchronized (lock) {
            System.out.println("enter method1.");
            try {
                lock.wait(1000);
                System.out.println("execute method1.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method1 end.");
        }
    }

    public void method2() {
        synchronized (lock) {
            System.out.println("enter method2.");
            System.out.println("execute method2.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method2 end.");
            lock.notify();
        }
    }

    public void method3() {
        int i = 0;
        while (i++ < 10) {
            try {
                System.out.println("complete method3");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        concurrentTest instance = new concurrentTest();
        //Thread t1 = new Thread(new Run1(instance));
        //Thread t2 = new Thread(new Run2(instance));
        //t1.start();
        //t2.start();
        Thread t3 = new Thread(new Run3(instance));
        t3.start();


        synchronized (t3) {
            while (t3.isAlive()) {
                try {
                    t3.wait(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("main task end");
    }

    public static class Run1 implements Runnable {

        private concurrentTest c;
        public Run1(concurrentTest c) {
            this.c = c;
        }

        @Override
        public void run() {
            c.method1();
        }
    }

    public static class Run2 implements Runnable {

        private concurrentTest c;
        public Run2(concurrentTest c) {
            this.c = c;
        }

        @Override
        public void run() {
            c.method2();
        }
    }

    public static class Run3 implements Runnable {

        private concurrentTest c;
        public Run3(concurrentTest c) {
            this.c = c;
        }

        @Override
        public void run() {
            c.method3();
        }
    }
}
