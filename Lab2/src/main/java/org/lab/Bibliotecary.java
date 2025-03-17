package org.lab;

import java.util.Random;

public class Bibliotecary {
    public void readBook() throws InterruptedException {
        Random rand = new Random();
        Thread.sleep(500 + rand.nextInt(3000 - 500 + 1));
    }


    public synchronized void writeBook() throws InterruptedException {
        Random rand = new Random();
        Thread.sleep(2000 + rand.nextInt(3000));
    }
}
