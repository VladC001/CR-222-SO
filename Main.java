class Reader extends Thread {
    private final Library library;
    private final int readerId;
    private final int booksToRead;

    public Reader(Library library, int readerId, int booksToRead) {
        this.library = library;
        this.readerId = readerId;
        this.booksToRead = booksToRead;
    }

    @Override
    public void run() {
        for (int i = 1; i <= booksToRead; i++) {
            String book = Library.readBook();
            System.out.println("Book read: " + book + " by Reader " + readerId);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int numWriters = 21;
        int booksPerWriter = 10;
        int numReaders = 21;
        int booksPerReader = booksPerWriter;

        int maxBooks = numWriters * booksPerWriter;

        Library library = new Library(maxBooks);
        Thread[] writers = new Thread[numWriters];
        Thread[] readers = new Thread[numReaders];

        for (int i = 0; i < numWriters; i++) {
            writers[i] = new Writer(library, i + 1, booksPerWriter);
            writers[i].start();
        }

        for (int i = 0; i < numReaders; i++) {
            readers[i] = new Reader(library, i + 1, booksPerReader);
            readers[i].start();
        }

        for (Thread writer : writers) {
            try {
                writer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (Thread reader : readers) {
            try {
                reader.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Toți scriitorii și cititorii au terminat.");
    }
}