class PhilosopherManager {
    private final int numPhilosophers;
    private final int cycles;
    private final Fork[] forks;
    private final Philosopher[] philosophers;
    private final PhilosopherGUI gui;

    public PhilosopherManager(int numPhilosophers, int cycles, PhilosopherGUI gui) {
        this.numPhilosophers = numPhilosophers;
        this.cycles = cycles;
        this.gui = gui;
        this.forks = new Fork[numPhilosophers];
        this.philosophers = new Philosopher[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < numPhilosophers; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % numPhilosophers];
            philosophers[i] = new Philosopher(i, leftFork, rightFork, cycles, gui);
        }
    }

    public void startDining() {
        for (Philosopher p : philosophers) {
            p.start();
        }
    }
}