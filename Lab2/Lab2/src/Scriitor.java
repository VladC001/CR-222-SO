public class Scriitor extends Thread {
    private final Biblioteca biblioteca;
    private final int id;
    private final int writersCount;

    public Scriitor(Biblioteca biblioteca, int id, int writersCount) {
        this.biblioteca = biblioteca;
        this.id = id;
        this.writersCount = writersCount;
    }

    @Override
    public void run() {
        for (int i = id; i < biblioteca.getTotalBooks(); i += writersCount) {
            biblioteca.adaugaCarte("Cartea " + (i + 1) + " - Scrisă de Scriitor " + id);
        }
    }
}