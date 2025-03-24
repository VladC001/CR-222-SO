// Library.java
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class Library {
    private final List<String> books = new ArrayList<>();
    private final int maxBooks;
    private final JTextArea textArea;

    public Library(int maxBooks, JTextArea textArea) {
        this.maxBooks = maxBooks;
        this.textArea = textArea;
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
        updateTextArea("Book written: " + book + "\n");
        notifyAll();
    }

    public synchronized String readBook() {
        while (books.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "No books available";
            }
        }
        String book = books.remove(0);
        updateTextArea("Book read: " + book + "\n");
        notifyAll();
        return book;
    }

    public void updateTextArea(String message) {
        textArea.append(message);
    }
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
