public class Filosof extends Thread {
    private int id;
    private Betisoare betisoare;
    private DiningPhilosophersGUI gui;

    public Filosof(int id, Betisoare betisoare, DiningPhilosophersGUI gui) {
        this.id = id;
        this.betisoare = betisoare;
        this.gui = gui;
    }

    private void gandeste() throws InterruptedException {
        DiningPhilosophersGUI.actualizeazaStare(id, "Gandeste");
        gui.log("Filosoful " + id + " gandeste...");
        gui.repaint();
        Thread.sleep(1000);
    }

    private void mananca() throws InterruptedException {
        DiningPhilosophersGUI.actualizeazaStare(id, "Mananca");
        gui.log("Filosoful " + id + " mananca!");
        gui.repaint();
        Thread.sleep(1000);
    }

    public void run() {
        while (true) {
            try {
                gandeste();
                DiningPhilosophersGUI.actualizeazaStare(id, "Asteapta");
                gui.log("Filosoful " + id + " asteapta betisoare...");
                gui.repaint();

                if (betisoare.incearcaRidicare(id)) {
                    mananca();
                    betisoare.puneBetisoare(id);
                } else {
                    gui.log("Filosoful " + id + " a asteptat prea mult si renunta temporar...");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
