import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int numarFilosofi = 11; // N = 11 filosofi
    private static final int caloriiPeMasa = 100; // Fiecare filosof consumă 100 calorii la fiecare masă
    private static final ReentrantLock[] furculite = new ReentrantLock[numarFilosofi];
    private static final JTextArea statusArea = new JTextArea();
    private static int filosofiiTerminati = 0;  // Variabilă globală pentru numărul filosofilor terminați
    private static JFrame frame;

    public static void main(String[] args) {
        // Creăm un tablou de furculițe protejate prin ReentrantLock
        for (int i = 0; i < numarFilosofi; i++) {
            furculite[i] = new ReentrantLock(true); // Lock echitabil (Fair ReentrantLock)
        }

        // Set up GUI
        setupGUI();

        // Creăm un ExecutorService pentru a administra firele de execuție
        ExecutorService executor = Executors.newFixedThreadPool(numarFilosofi);

        // Creăm și pornim filosofii
        for (int i = 0; i < numarFilosofi; i++) {
            executor.execute(new Filosof(i, furculite, caloriiPeMasa, statusArea));
        }

        // Oprirea ExecutorService după finalizarea tuturor filosofilor
        executor.shutdown();
    }

    private static void setupGUI() {
        // Creăm fereastra principală
        frame = new JFrame("Problema Filosofilor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Creăm zona de status
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(statusArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Afișăm fereastra
        frame.setVisible(true);
    }

    // Incrementăm contorul filosofilor terminați
    public static synchronized void incrementFilosofiTerminati() {
        filosofiiTerminati++;
        if (filosofiiTerminati == numarFilosofi) {
            SwingUtilities.invokeLater(() -> {
                frame.dispose(); // Închide fereastra când toți filosofii au terminat
                System.exit(0); // Oprirea aplicației
            });
        }
    }
}
