import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.List;
class Library {
    static int books;
    Writer[] writers;
    Reader[] readers;
    static ArrayList<String> library = new ArrayList<>();
    static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true); // Fairness ON
    static final Lock writeLock = rwl.writeLock();
    static final Lock readLock = rwl.readLock();
    TextArea outputArea;

    public Library(int writersCount, int readersCount, int books, TextArea outputArea) {
        this.writers = new Writer[writersCount];
        this.readers = new Reader[readersCount];
        Library.books = books;
        this.outputArea = outputArea;

        // Listă totală de cărți
        List<String> allBooks = new ArrayList<>(Arrays.asList(
                "Book1", "Book2", "Book3", "Book4", "Book5",
                "Book6", "Book7", "Book8", "Book9", "Book10",
                "Book11", "Book12", "Book13", "Book14", "Book15"
        ));

        // Împărțim cărțile între scriitori
        Collections.shuffle(allBooks); // Shuffle ca să nu le scrie unul după altul
        int booksPerWriter = (int)Math.ceil((double)allBooks.size() / writersCount);
        for (int i = 0; i < writersCount; i++) {
            int fromIndex = i * booksPerWriter;
            int toIndex = Math.min(fromIndex + booksPerWriter, allBooks.size());
            if (fromIndex >= allBooks.size()) break;
            List<String> writerBooks = allBooks.subList(fromIndex, toIndex);
            this.writers[i] = new Writer("Writer " + (i + 1), outputArea, new ArrayList<>(writerBooks));
        }


        for (int i = 0; i < readersCount; i++) {
            this.readers[i] = new Reader("Reader " + (i + 1), outputArea);
        }
    }

    public void start() {
        for (Writer writer : this.writers) {
            if (writer != null) {
                writer.start();
            }
        }
        for (Reader reader : this.readers) {
            reader.start();
        }
    }

}

class Writer extends Thread {
    String name;
    ArrayList<String> bookList;
    public final Lock writeLock = Library.writeLock;
    ArrayList<String> library = Library.library;
    ArrayList<String> writtenBooks = new ArrayList<>();
    TextArea outputArea;

    public Writer(String name, TextArea outputArea, ArrayList<String> bookList) {
        this.name = name;
        this.outputArea = outputArea;
        this.bookList = bookList;
    }

    @Override
    public void run() {
        for (String book : bookList) {
            try {
                writeLock.lock();
                if (!library.contains(book)) {
                    Thread.sleep(100 + new Random().nextInt(300)); // Simulare scriere mai realistă
                    library.add(book);
                    writtenBooks.add(book);
                    outputArea.append(name + " wrote " + book + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
        outputArea.append(name + " finished writing:\n" + writtenBooks + "\n");
    }
}

class Reader extends Thread {
    public final Lock readLock = Library.readLock;
    ArrayList<String> readBooks = new ArrayList<>();
    ArrayList<String> library = Library.library;
    String name;
    TextArea outputArea;

    public Reader(String name, TextArea outputArea) {
        this.name = name;
        this.outputArea = outputArea;
    }

    @Override
    public void run() {
        while (readBooks.size() < Library.books) {
            try {
                readLock.lock();
                for (String book : library) {
                    if (!readBooks.contains(book)) {
                        Thread.sleep(100); // Simulare citire
                        readBooks.add(book);
                        outputArea.append(name + " read " + book + "\n");
                    }
                    if (readBooks.size() == Library.books) break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
        outputArea.append(name + " finished reading:\n" + readBooks + "\n");
    }
}

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame("Library Simulation");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Zonă pentru afișarea rezultatelor
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        frame.add(outputArea, BorderLayout.CENTER);

        // Butonul de pornire
        Button startButton = new Button("Start Simulation");
        frame.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final int writers = 18;   // poți modifica
                final int readers = 24;   // poți modifica
                final int books = 15;    // total cărți
                Library library = new Library(writers, readers, books, outputArea);
                library.start();
            }
        });

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
