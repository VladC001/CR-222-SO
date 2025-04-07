class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private final int cycles;
    private final PhilosopherGUI gui;

    public Philosopher(int id, Fork leftFork, Fork rightFork, int cycles, PhilosopherGUI gui) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.cycles = cycles;
        this.gui = gui;
    }

    private void think() throws InterruptedException {
        gui.updateStatus(id, "Thinking");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        gui.updateStatus(id, "Eating");
        Thread.sleep((int) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < cycles; i++) {
                think();

                if (id % 2 == 0) {
                    gui.updateStatus(id, "Trying to pick up left fork");
                    leftFork.pickUp();
                    gui.updateStatus(id, "Picked up left fork");

                    gui.updateStatus(id, "Trying to pick up right fork");
                    rightFork.pickUp();
                    gui.updateStatus(id, "Picked up right fork");
                } else {
                    gui.updateStatus(id, "Trying to pick up right fork");
                    rightFork.pickUp();
                    gui.updateStatus(id, "Picked up right fork");

                    gui.updateStatus(id, "Trying to pick up left fork");
                    leftFork.pickUp();
                    gui.updateStatus(id, "Picked up left fork");
                }

                eat();

                leftFork.putDown();
                gui.updateStatus(id, "Put down left fork");

                rightFork.putDown();
                gui.updateStatus(id, "Put down right fork");
            }
            gui.updateStatus(id, "Done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}