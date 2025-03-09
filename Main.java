import javax.swing.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cititor extends Thread {
    private final Biblioteca biblioteca;
    private final int id;
    private final JTextArea textArea;

    public Cititor(Biblioteca biblioteca, int id, JTextArea textArea) {
        this.biblioteca = biblioteca;
        this.id = id;
        this.textArea = textArea;
    }

    public void run() {
        for (int i = 0; i < 8; i++) {
            biblioteca.citesteCarte(i, textArea);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cititori și Scriitori");
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Biblioteca biblioteca = new Biblioteca(8);
        Scriitor[] scriitori = new Scriitor[6];
        Cititor[] cititori = new Cititor[10];

        for (int i = 0; i < 6; i++) {
            scriitori[i] = new Scriitor(biblioteca, i + 1, textArea);
            scriitori[i].start();
        }

        for (Scriitor scriitor : scriitori) {
            try {
                scriitor.join(); // Așteptăm să termine scriitorii
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            cititori[i] = new Cititor(biblioteca, i + 1, textArea);
            cititori[i].start();
        }
    }
}
