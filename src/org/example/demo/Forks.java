package org.example.demo;
import java.util.concurrent.locks.*;


class Forks {
    private int number;
    private State[] forks;
    private Lock lock = new ReentrantLock();
    private enum State {FREE, IN_USE};

    public Forks(int number) {
        this.number = number;
        this.forks = new State[number];
        for (int i = 0; i < number; i++) {
            forks[i] = State.FREE;
        }
        System.out.println("Forks are created!");
        printState();
    }

    public boolean takeForks(int position) {
        lock.lock();
        try {
            int left = (position == 0) ? number - 1 : position - 1;
            int right = position;
            if (forks[left] == State.FREE && forks[right] == State.FREE) {
                forks[left] = State.IN_USE;
                forks[right] = State.IN_USE;
                System.out.println("Philosopher #" + position + " has taken the forks.");
                printState();
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void leaveForks(int position) {
        lock.lock();
        try {
            int left = (position == 0) ? number - 1 : position - 1;
            forks[left] = State.FREE;
            forks[position] = State.FREE;
            System.out.println("Philosopher #" + position + " has left the forks.");
            printState();
        } finally {
            lock.unlock();
        }
    }

    private void printState() {
        System.out.print("Current state: ");
        for (int i = 0; i < number; i++) {
            System.out.print(i + ":" + forks[i] + "  ");
        }
        System.out.println();
    }
}