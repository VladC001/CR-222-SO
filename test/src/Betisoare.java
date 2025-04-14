import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class Betisoare {
    private final ReentrantLock[] betisoare;

    public Betisoare(int n) {
        betisoare = new ReentrantLock[n];
        for (int i = 0; i < n; i++) {
            betisoare[i] = new ReentrantLock();
        }
    }

    public boolean incearcaRidicare(int id) throws InterruptedException {
        int stanga = id;
        int dreapta = (id + 1) % betisoare.length;

        boolean betisorStanga = betisoare[stanga].tryLock(2000, TimeUnit.MILLISECONDS);
        if (!betisorStanga) return false;

        boolean betisorDreapta = betisoare[dreapta].tryLock(2000, TimeUnit.MILLISECONDS);
        if (!betisorDreapta) {
            betisoare[stanga].unlock(); // elibereaza stanga daca nu a prins dreapta
            return false;
        }

        return true; // a prins ambele
    }

    public void puneBetisoare(int id) {
        int stanga = id;
        int dreapta = (id + 1) % betisoare.length;

        betisoare[stanga].unlock();
        betisoare[dreapta].unlock();
    }
}
