package com.lixing.concurrentunit;

import java.util.concurrent.Semaphore;

/**
 * Created by Toephy on 2016/6/3 15:32
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        try {
            Semaphore semaphore = new Semaphore(0);
            semaphore.acquire();
            System.out.println("Semaphore acquired.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
