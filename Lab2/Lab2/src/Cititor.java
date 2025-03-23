public class Cititor extends Thread {
    private final Biblioteca biblioteca;
    private final String name;

    public Cititor(Biblioteca biblioteca, String name) {
        this.biblioteca = biblioteca;
        this.name = name;
    }

    @Override
    public void run() {
        biblioteca.afiseazaCarti(name);
    }
}