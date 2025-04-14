public class Main {
    public static void main(String[] args) {
        final int N = 12;
        Betisoare betisoare = new Betisoare(N);
        DiningPhilosophersGUI gui = new DiningPhilosophersGUI();

        for (int i = 0; i < N; i++) {
            new Filosof(i, betisoare, gui).start();
        }
    }
}
