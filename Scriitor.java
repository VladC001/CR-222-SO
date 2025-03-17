import javax.swing.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Biblioteca {
    private final String[] carti;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Biblioteca(int nrCarti) {
        carti = new String[nrCarti];
        for (int i = 0; i < nrCarti; i++) {
            carti[i] = "Carte " + (i + 1) + " (goală)";
        }
    }

    public void scrieCarte(int index, String content, JTextArea textArea) {
        lock.writeLock().lock();
        try {
            carti[index] = content;
            textArea.append("Scriitor a scris: " + content + "\n");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String citesteCarte(int index, JTextArea textArea) {
        lock.readLock().lock();
        try {
            String content = carti[index];
            textArea.append("Cititor a citit: " + content + "\n");
            return content;
        } finally {
            lock.readLock().unlock();
        }
    }
}

class Scriitor extends Thread {
    private final Biblioteca biblioteca;
    private final int id;
    private final JTextArea textArea;

    public Scriitor(Biblioteca biblioteca, int id, JTextArea textArea) {
        this.biblioteca = biblioteca;
        this.id = id;
        this.textArea = textArea;
    }

    public void run() {
        for (int i = 0; i < 8; i++) {
            biblioteca.scrieCarte(i, "Carte " + (i + 1) + " scrisă de Scriitor " + id, textArea);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}