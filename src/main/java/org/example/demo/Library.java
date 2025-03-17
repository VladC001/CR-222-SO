package org.example.demo;
//
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Library {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read(String readerName) {
        lock.readLock().lock();
        try {
//            System.out.println(readerName + " citește o carte.");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(String writerName) {
        lock.writeLock().lock();
        try {
//            System.out.println(writerName + " scrie o carte.");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
