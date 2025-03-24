public class Reader implements Runnable {
    private final SharedZone sharedZone;

    public Reader(SharedZone sharedZone) {
        this.sharedZone = sharedZone;
    }

    @Override
    public void run() {
        while (true) {
            sharedZone.readBooks();
        }
    }
}
