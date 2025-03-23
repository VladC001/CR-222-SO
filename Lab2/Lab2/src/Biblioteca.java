import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Biblioteca {
    private final List<String> books = new ArrayList<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final int totalBooks;

    public Biblioteca(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public void adaugaCarte(String book) {
        rwLock.writeLock().lock();
        try {
            books.add(book);
            System.out.println("Scris: " + book);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void afiseazaCarti(String readerName) {
        rwLock.readLock().lock();
        try {
            books.forEach(book -> System.out.println(readerName + " citește: " + book));
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public int getTotalBooks() {
        return totalBooks;
    }
}

