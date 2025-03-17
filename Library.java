import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<String> books = new ArrayList<>();
    private final int maxBooks;

    public Library(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public synchronized void writeBook(String book) {
        while (books.size() >= maxBooks) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        books.add(book);
        System.out.println("Book written: " + book);
        notifyAll();
    }



class Writer extends Thread {
    private final Library library;
    private final int writerId;
    private final int booksToWrite;

    public Writer(Library library, int writerId, int booksToWrite) {
        this.library = library;
        this.writerId = writerId;
        this.booksToWrite = booksToWrite;
    }

    @Override
    public void run() {
        for (int i = 1; i <= booksToWrite; i++) {
            String book = "Book " + i + " by Writer " + writerId;
            library.writeBook(book);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
