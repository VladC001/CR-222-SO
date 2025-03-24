import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookApp {
    private static final int NUM_WRITERS = 7;  // Numărul de scriitori
    private static final int NUM_READERS = 6;  // Numărul de cititori
    private static final SharedZone sharedZone = new SharedZone();

    private static JTextArea outputArea;

    public static void main(String[] args) {
        // Crearea interfeței grafice
        JFrame frame = new JFrame("Writer-Reader Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Crearea unui text area pentru a afișa mesaje
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Crearea unui buton pentru a începe simularea
        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(e -> startSimulation());
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void startSimulation() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_WRITERS + NUM_READERS);

        // Crearea și lansarea scriitorilor
        for (int i = 0; i < NUM_WRITERS; i++) {
            executor.submit(new Writer(sharedZone));
        }

        // Crearea și lansarea cititorilor
        for (int i = 0; i < NUM_READERS; i++) {
            executor.submit(new Reader(sharedZone));
        }

        // Închide executorul după terminarea simulării
        executor.shutdown();
    }

    // Metodă de logare a mesajelor în zona de text a interfeței
    public static void logMessage(String message) {
        outputArea.append(message + "\n");
    }
}
