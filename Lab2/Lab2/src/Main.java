public class Main {
    public static void main(String[] args) {
        int numWriters = 5;
        int numReaders = 6;
        int numBooks = 6;

        Biblioteca biblioteca = new Biblioteca(numBooks);

        for (int i = 0; i < numWriters; i++) {
            new Scriitor(biblioteca, i, numWriters).start();
        }

        for (int i = 0; i < numReaders; i++) {
            new Cititor(biblioteca, "Cititor " + (i + 1)).start();
        }
    }
}