package org.lab;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Bibliotecary {
    private final ReentrantLock libraryLock = new ReentrantLock();


    public void enterLibrary() throws InterruptedException {
        if (!libraryLock.tryLock()) {
            Thread.currentThread().interrupt();
            throw new InterruptedException("Biblioteca este ocupată. Thread-ul curent a fost întrerupt.");
        }
    }

    public void exitLibrary() {
        if (libraryLock.isHeldByCurrentThread()) {
            libraryLock.unlock();
        }
    }

    public void readBook() throws InterruptedException {
        Random rand = new Random();
        Thread.sleep(500 + rand.nextInt(3000 - 500 + 1));
    }

    public void writeBook() throws InterruptedException {
        Random rand = new Random();
        Thread.sleep(2000 + rand.nextInt(3000));
    }
}
