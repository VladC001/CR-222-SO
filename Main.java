// Main.java
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

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
        for (int i = 0; i < booksToRead; i++) {
            String book = library.readBook();
            library.updateTextArea("Reader " + readerId + " read: " + book + "\n");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Simulation");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton startButton = new JButton("Start Simulation");

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            int numWriters = 23;
            int booksPerWriter = 8;
            int numReaders = 12;
            int booksPerReader = booksPerWriter;

            int maxBooks = numWriters * booksPerWriter;
            Library library = new Library(maxBooks, textArea);

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

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 60000);
        });

        frame.setVisible(true);
    }
}
