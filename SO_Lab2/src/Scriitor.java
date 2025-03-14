public class Scriitor extends Thread {
    private final Biblioteca biblioteca;
    private final int idScriitor;

    public Scriitor(Biblioteca biblioteca, int idScriitor) {
        this.biblioteca = biblioteca;
        this.idScriitor = idScriitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 14; i++) {
            int indexCarte = (idScriitor - 1) * 14 + i;
            biblioteca.scrieCarte(indexCarte, idScriitor);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        biblioteca.scriitorTerminat();
    }
}
