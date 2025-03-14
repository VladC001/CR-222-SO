public class Main {
    public static void main(String[] args) {
        int nrScriitori = 18;
        int nrCititori = 22;
        int nrCarti = 18 * 14;

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
}
