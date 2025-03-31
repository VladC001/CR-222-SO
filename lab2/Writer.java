public class Writer implements Runnable {
    private final SharedZone sharedZone;

    public Writer(SharedZone sharedZone) {
        this.sharedZone = sharedZone;
    }

    @Override
    public void run() {
        for (int i = 0; i < 14; i++) {  // Scrie 14 cărți
            sharedZone.writeBook("Carte " + (i + 1));
        }
    }
}