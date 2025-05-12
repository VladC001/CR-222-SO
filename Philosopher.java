import java.util.concurrent.locks.ReentrantLock;

class Fork {
    private final ReentrantLock lock = new ReentrantLock();

    public void pickUp() {
        lock.lock();
    }

    public void putDown() {
        lock.unlock();
    }
}

public class Philosopher implements Runnable {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private final int cycles;

    public Philosopher(int id, Fork leftFork, Fork rightFork, int cycles) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.cycles = cycles;
    }

    private void think() throws InterruptedException {
        String message = "Philosopher " + id + " is thinking.";
        Main.updateTextArea(message); // Actualizăm GUI-ul
        Thread.sleep((int)(Math.random() * 100));
    }

    private void eat() throws InterruptedException {
        String message = "Philosopher " + id + " is eating.";
        Main.updateTextArea(message); // Actualizăm GUI-ul
        Thread.sleep((int)(Math.random() * 100));
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < cycles; i++) {
                think();

                // Evitare deadlock: filosofii iau furculițele într-o ordine controlată
                Fork first = id % 2 == 0 ? leftFork : rightFork;
                Fork second = id % 2 == 0 ? rightFork : leftFork;

                first.pickUp();
                second.pickUp();

                eat();

                second.putDown();
                first.putDown();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
