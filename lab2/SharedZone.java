import java.util.LinkedList;
import java.util.Queue;

public class SharedZone {
    private final Queue<String> books = new LinkedList<>();
    private final int maxBooks = 14;

    public synchronized void writeBook(String book) {
        while (books.size() == maxBooks) {
            try {
                wait();  // Așteaptă dacă sunt prea multe cărți
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        books.add(book);
        System.out.println("A scris o carte: " + book);
        notifyAll();  // Anunță cititorii că o carte a fost adăugată
    }

    public synchronized String readBooks() {
        while (books.size() < 3) {
            try {
                wait();  // Așteaptă dacă nu sunt suficiente cărți pentru citire
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        StringBuilder readBooks = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            readBooks.append(books.poll()).append("\n");
        }
        System.out.println("A citit cărțile: \n" + readBooks);
        notifyAll();  // Anunță scriitorii că au citit cărțile
        return readBooks.toString();
    }
}