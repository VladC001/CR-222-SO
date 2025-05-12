import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static JTextArea textArea;

    public static void main(String[] args) {
        // Creăm fereastra GUI
        JFrame frame = new JFrame("Dining Philosophers");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adăugăm un JTextArea pentru a afișa statusurile filosofilor
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Setăm un buton pentru a închide aplicația
        JButton closeButton = new JButton("Închide");
        closeButton.addActionListener(e -> System.exit(0));
        frame.add(closeButton, BorderLayout.SOUTH);

        frame.setVisible(true);

        final int baseNumber = 5; // numărul de filosofi
        final int N = baseNumber + 10; // numărul de filosofi
        final int CYCLES = 5; // câte cicluri de gândire/mâncare face fiecare filosof

        Fork[] forks = new Fork[N];
        for (int i = 0; i < N; i++) {
            forks[i] = new Fork();
        }

        // Creăm un ExecutorService pentru a porni firele
        ExecutorService executor = Executors.newFixedThreadPool(N);

        // Creăm filosofii și le atribuim furculițele
        for (int i = 0; i < N; i++) {
            Philosopher philosopher = new Philosopher(i, forks[i], forks[(i + 1) % N], CYCLES);
            executor.execute(philosopher);
        }

        executor.shutdown();
    }

    // Metoda pentru a actualiza textul în JTextArea
    public static void updateTextArea(String message) {
        textArea.append(message + "\n");
    }
}