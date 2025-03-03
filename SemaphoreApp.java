package lab1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class SemaphoreApp {
    private static Timer timer = new Timer();
    private static JButton semaphoreButton; // Butonul care va reprezenta semaforul

    public static void main(String[] args) {
        // Creăm fereastra
        JFrame frame = new JFrame("Semafor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        // Creăm un buton pentru semafor
        semaphoreButton = new JButton("Semafor");
        semaphoreButton.setFont(new Font("Arial", Font.PLAIN, 20));
        semaphoreButton.setPreferredSize(new Dimension(100, 100));

        // Setăm un layout pentru fereastră
        frame.setLayout(new BorderLayout());
        frame.add(semaphoreButton, BorderLayout.CENTER);

        // Vizualizăm fereastra
        frame.setVisible(true);

        // Pornim semaforul
        startSemaphore();
    }

    public static void startSemaphore() {
        // Schimbăm culoarea semaforului la intervale regulate
        TimerTask red = new SemaphoreTask("Roșu", Color.RED);
        TimerTask yellow = new SemaphoreTask("Galben", Color.YELLOW);
        TimerTask green = new SemaphoreTask("Verde", Color.GREEN);

        timer.scheduleAtFixedRate(red, 0, 12000); // Roșu la început și se repetă la fiecare 12 secunde
        timer.scheduleAtFixedRate(yellow, 5000, 12000); // Galben după 5 secunde
        timer.scheduleAtFixedRate(green, 7000, 12000); // Verde după 7 secunde
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