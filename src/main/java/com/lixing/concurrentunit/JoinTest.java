package com.lixing.concurrentunit;

/**
 * Created by Toephy on 2016.11.17 16:57
 */
public class JoinTest {


    public static void main(String[] args) {
        Thread locker = new Thread(new JobLocker());
        Thread t1 = new Thread(new JobTask(locker, -1));
        Thread t2 = new Thread(new JobTask(locker));
        locker.start();
        t1.start();
        t2.start();
    }

    static class JobTask implements Runnable {

        Thread t;
        int time = 0;

        public JobTask(Thread t) {
            this.t = t;
        }

        public JobTask(Thread t, int time) {
            this.t = t;
            this.time = time;
        }

        @Override
        public void run() {
            try {
                System.out.println("begin run " + this.hashCode());
                t.join(time);
                System.out.println("end run " + this.hashCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class JobLocker implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (i++ < 10) {
                try {
                    System.out.println("JobLocker");
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
