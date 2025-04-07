import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Library {
    static int books;
    Writer[] writers;
    Reader[] readers;
    static ArrayList<String> library = new ArrayList<>();
    static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);
    static final Lock writeLock = rwl.writeLock();
    static final Lock readLock = rwl.readLock();
    TextArea outputArea;

    public Library(int writers, int readers, int books, TextArea outputArea) {
        this.writers = new Writer[writers];
        this.readers = new Reader[readers];
        Library.books = books;
        this.outputArea = outputArea;
        for (int i = 0; i < writers; i++) {
            this.writers[i] = new Writer("Writer " + (i+1), outputArea);
        }
        for (int i = 0; i < readers; i++) {
            this.readers[i] = new Reader("Reader " + (i+1), outputArea);
        }
    }

    public void start() {
        for (Writer writer : this.writers) {
            writer.start();
        }
        for (Reader reader : this.readers) {
            reader.start();
        }
    }
}

class Writer extends Thread {
    String name;
    ArrayList<String> bookList = new ArrayList<>(Arrays.asList(
            "Book1", "Book2", "Book3", "Book4", "Book5",
            "Book6", "Book7", "Book8", "Book9", "Book10",
            "Book11", "Book12", "Book13", "Book14", "Book15"));
    public final Lock writeLock = Library.writeLock;
    ArrayList<String> library = Library.library;
    ArrayList<String> writtenBooks = new ArrayList<>();
    TextArea outputArea;

    public Writer(String name, TextArea outputArea) {
        this.name = name;
        this.outputArea = outputArea;
    }

    @Override
    public void run() {
        for (String book : bookList) {
            try {
                writeLock.lock();
                if (!library.contains(book)) {
                    Thread.sleep(200); // Writing simulation
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
                        Thread.sleep(100); // Reading simulation
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

        // TextArea for displaying output
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        frame.add(outputArea, BorderLayout.CENTER);

        // Button to start the simulation
        Button startButton = new Button("Start Simulation");
        frame.add(startButton, BorderLayout.SOUTH);

        // Action listener to start the simulation when clicked
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final int writers = 18;
                final int readers = 24;
                final int books = 15;
                Library library = new Library(writers, readers, books, outputArea);
                library.start();
            }
        });

        // Set up the frame
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
