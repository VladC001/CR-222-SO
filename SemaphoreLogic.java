package lab1;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

public class SemaphoreLogic {
    private Timer timer;
    private SemaphoreUI ui; // Referință la interfață

    public SemaphoreLogic(SemaphoreUI ui) {
        this.ui = ui;
        timer = new Timer();
        startSemaphore();
    }

    public void startSemaphore() {
        // Schimbăm culoarea semaforului la intervale regulate
        timer.scheduleAtFixedRate(new SemaphoreTask("Roșu", Color.RED), 0, 12000);    // Roșu la început
        timer.scheduleAtFixedRate(new SemaphoreTask("Galben", Color.YELLOW), 5000, 12000); // Galben după 5 secunde
        timer.scheduleAtFixedRate(new SemaphoreTask("Verde", Color.GREEN), 7000, 12000);  // Verde după 7 secunde
    }

    class SemaphoreTask extends TimerTask {
        private String colorName;
        private Color color;

        public SemaphoreTask(String colorName, Color color) {
            this.colorName = colorName;
            this.color = color;
        }

        @Override
        public void run() {
            // Actualizăm culoarea semaforului în interfața grafică
            SwingUtilities.invokeLater(() -> ui.updateSemaphore(colorName, color));
        }
    }
}