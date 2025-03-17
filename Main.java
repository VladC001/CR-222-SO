import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    private static JTextArea textArea; // Zona pentru output

    public static void main(String[] args) {
        int nrScriitori = 18;
        int nrCititori = 22;
        int nrCarti = 18 * 14;

        // Creăm interfața grafică
        createGUI();

        // Redirecționăm ieșirea către JTextArea
        redirectOutputToTextArea();

        Biblioteca biblioteca = new Biblioteca(nrScriitori, nrCarti);

        // Creăm și pornim scriitorii
        for (int i = 1; i <= nrScriitori; i++) {
            new Scriitor(biblioteca, i).start();
        }

        // Creăm și pornim cititorii
        for (int i = 1; i <= nrCititori; i++) {
            new Cititor(biblioteca, i).start();
        }
    }

    private static void createGUI() {
        JFrame frame = new JFrame("Simulare Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Metodă pentru a redirecționa ieșirea în JTextArea
    private static void redirectOutputToTextArea() {
        // Cream un PrintStream care redirecționează la textArea
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));  // Adăugăm fiecare caracter la JTextArea
            }
        });

        // Setăm acest PrintStream ca ieșire standard
        System.setOut(printStream);
        System.setErr(printStream);  // Redirecționăm și erorile
    }
}
