

public class Main {
    public static void main(String[] args) {
        int N = 19 + 10;
        int cycles = 5;
        PhilosopherGUI gui = new PhilosopherGUI(N);
        PhilosopherManager manager = new PhilosopherManager(N, cycles, gui);
        manager.startDining();
    }
}