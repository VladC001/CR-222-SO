import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Biblioteca {
    private final List<String> carti = new ArrayList<>();
    private final List<String> cartiScrise = new ArrayList<>();
    private final Object lock = new Object();
    private final CountDownLatch latch;

    public Biblioteca(int nrScriitori, int nrCarti) {
        latch = new CountDownLatch(nrScriitori);
        for (int i = 1; i <= nrCarti; i++) {
            carti.add("Cartea " + i);
        }
    }

    public void scrieCarte(int indexCarte, int idScriitor) {
        synchronized (lock) {
            if (indexCarte < carti.size()) {
                String carte = carti.get(indexCarte);
                cartiScrise.add(carte);
                System.out.println("Scriitorul " + idScriitor + " a scris: " + carte);
            }
        }
    }

    public List<String> getCartiScrise() {
        return new ArrayList<>(cartiScrise);
    }

    public void asteaptaScriitori() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scriitorTerminat() {
        latch.countDown();
    }
}
