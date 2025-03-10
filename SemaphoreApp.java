import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SemaphoreApp {
    private static Timer timer = new Timer();
    private static Timer timer3 = new Timer();  // Al treilea timer pentru task periodic
    private static JButton semaphoreButton; // Butonul care va reprezenta semaforul

    public static void main(String[] args) {
        // Creăm fereastra semaforului
        JFrame frame = new JFrame("Semafor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        semaphoreButton = new JButton("Semafor");
        semaphoreButton.setFont(new Font("Arial", Font.PLAIN, 20));
        semaphoreButton.setPreferredSize(new Dimension(100, 100));

        frame.setLayout(new BorderLayout());
        frame.add(semaphoreButton, BorderLayout.CENTER);
        frame.setVisible(true);

        // Pornim semaforul
        startSemaphore();

        // Adăugăm celelalte task-uri
        AdditionalTimers.scheduleAtSpecificTime();
        AdditionalTimers.scheduleWithFixedDelay();
        AdditionalTimers.schedulePeriodicTask();
    }

    public static void startSemaphore() {
        // Schimbăm culoarea semaforului la intervale regulate
        TimerTask red = new SemaphoreTask("Roșu", Color.RED);
        TimerTask yellow = new SemaphoreTask("Galben", Color.YELLOW);
        TimerTask green = new SemaphoreTask("Verde", Color.GREEN);

        // Timere pentru semafor
        timer.scheduleAtFixedRate(red, 0, 12000);  // Roșu la început și se repetă la fiecare 12 secunde
        timer.scheduleAtFixedRate(yellow, 5000, 12000); // Galben după 5 secunde
        timer.scheduleAtFixedRate(green, 7000, 12000);  // Verde după 7 secunde

        // Al treilea timer pentru un task periodic
        timer3.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task periodic semafor executat la fiecare 10 secunde.");
            }
        }, 0, 10000);  // La fiecare 10 secunde
    }

    // Metodă pentru a opri toate timerele
    public static void stopAllTimers() {
        System.out.println("Oprire timere...");
        timer.cancel();
        timer.purge();
        timer3.cancel();
        timer3.purge();
    }

    static class SemaphoreTask extends TimerTask {
        private String colorName;
        private Color color;

        public SemaphoreTask(String colorName, Color color) {
            this.colorName = colorName;
            this.color = color;
        }

        @Override
        public void run() {
            // Actualizăm culoarea semaforului în interfața grafică
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    semaphoreButton.setBackground(color);
                    semaphoreButton.setText(colorName);
                }
            });
        }
    }
}
