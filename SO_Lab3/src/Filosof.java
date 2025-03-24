import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.*;

public class Filosof implements Runnable {
    private final int id;
    private final ReentrantLock[] furculite;
    private final int caloriiPeMasa;
    private int cicluriRestante;
    private int caloriiConsumate;
    private final JTextArea statusArea;

    // Constructorul filosofului
    public Filosof(int id, ReentrantLock[] furculite, int caloriiPeMasa, JTextArea statusArea) {
        this.id = id;
        this.furculite = furculite;
        this.caloriiPeMasa = caloriiPeMasa;
        this.cicluriRestante = 11; // Numărul de cicluri (gândire/mâncare)
        this.caloriiConsumate = 0;
        this.statusArea = statusArea;
    }

    // Metoda pentru gândire
    private void gandeste() {
        SwingUtilities.invokeLater(() -> statusArea.append("Filosoful " + id + " gândește.\n"));
        try {
            Thread.sleep(1000); // Gândirea durează 1 secundă
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Metoda pentru mâncat
    private void mananca() {
        int furculitaStanga = id;
        int furculitaDreapta = (id + 1) % furculite.length;

        try {
            if (id % 2 == 0) {  // Filosofii cu ID par
                if (furculite[furculitaStanga].tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        if (furculite[furculitaDreapta].tryLock(500, TimeUnit.MILLISECONDS)) {
                            try {
                                SwingUtilities.invokeLater(() -> statusArea.append("Filosoful " + id + " mănâncă.\n"));
                                Thread.sleep(1000); // Mâncatul durează 1 secundă

                                // Calculăm caloriile consumate
                                caloriiConsumate += caloriiPeMasa;
                                SwingUtilities.invokeLater(() -> statusArea.append("Filosoful " + id + " a consumat " + caloriiConsumate + " calorii.\n"));
                            } finally {
                                furculite[furculitaDreapta].unlock(); // Eliberăm furculița dreaptă
                            }
                        }
                    } finally {
                        furculite[furculitaStanga].unlock(); // Eliberăm furculița stângă
                    }
                }
            } else {  // Filosofii cu ID impar
                if (furculite[furculitaDreapta].tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        if (furculite[furculitaStanga].tryLock(500, TimeUnit.MILLISECONDS)) {
                            try {
                                SwingUtilities.invokeLater(() -> statusArea.append("Filosoful " + id + " mănâncă.\n"));
                                Thread.sleep(1000); // Mâncatul durează 1 secundă

                                // Calculăm caloriile consumate
                                caloriiConsumate += caloriiPeMasa;
                                SwingUtilities.invokeLater(() -> statusArea.append("Filosoful " + id + " a consumat " + caloriiConsumate + " calorii.\n"));
                            } finally {
                                furculite[furculitaStanga].unlock(); // Eliberăm furculița stângă
                            }
                        }
                    } finally {
                        furculite[furculitaDreapta].unlock(); // Eliberăm furculița dreaptă
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (cicluriRestante > 0) {
            gandeste();
            mananca();
            cicluriRestante--;
        }
        SwingUtilities.invokeLater(() -> statusArea.append("Filosoful " + id + " a terminat.\n"));
    }
}
