import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class BookApp {
    private static final int NUM_WRITERS = 7;  // Numărul de scriitori
    private static final int NUM_READERS = 6;  // Numărul de cititori
    private static final SharedZone sharedZone = new SharedZone();

    private static JTextPane outputArea; // Schimbat din JTextArea în JTextPane
    private static StyledDocument doc;

    public static void main(String[] args) {
        // Crearea interfeței grafice
        JFrame frame = new JFrame("Writer-Reader Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Crearea unui JTextPane pentru a afișa mesaje stilizate
        outputArea = new JTextPane();
        outputArea.setEditable(false);
        doc = outputArea.getStyledDocument();  // Obținerea documentului stilizat
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
    public static void logMessage(String message, String color) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Crearea unui stil pentru textul colorat
                Style style = doc.addStyle("Style", null);
                if ("green".equals(color)) {
                    StyleConstants.setForeground(style, Color.GREEN);
                } else if ("blue".equals(color)) {
                    StyleConstants.setForeground(style, Color.BLUE);
                } else {
                    StyleConstants.setForeground(style, Color.BLACK);
                }

                // Adăugarea textului la document
                doc.insertString(doc.getLength(), message + "\n", style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }
}