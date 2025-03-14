import java.util.ArrayList;
import java.util.List;

public class Cititor extends Thread {
    private final Biblioteca biblioteca;
    private final int idCititor;
    private List<String> cartiCitite = new ArrayList<>();

    public Cititor(Biblioteca biblioteca, int idCititor) {
        this.biblioteca = biblioteca;
        this.idCititor = idCititor;
    }

    @Override
    public void run() {
        biblioteca.asteaptaScriitori(); // Așteptăm ca toate cărțile să fie scrise

        List<String> cartiDisponibile = biblioteca.getCartiScrise();
        int startIndex = (idCititor - 1) * 14;
        int endIndex = Math.min(startIndex + 14, cartiDisponibile.size());

        // Adăugăm cărțile disponibile pentru cititor
        if (startIndex < cartiDisponibile.size()) {
            cartiCitite.addAll(cartiDisponibile.subList(startIndex, endIndex));
        }

        // Dacă nu sunt suficiente cărți, completăm din cele existente (recitim)
        int indexRecitire = 0;
        while (cartiCitite.size() < 14) {
            cartiCitite.add(cartiDisponibile.get(indexRecitire % cartiDisponibile.size()));
            indexRecitire++;
        }

        System.out.println("Cititorul " + idCititor + " a citit următoarele cărți: " + cartiCitite);
    }
}
