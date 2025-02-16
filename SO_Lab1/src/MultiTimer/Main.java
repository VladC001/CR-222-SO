package MultiTimer;

import javax.swing.*;
import java.awt.*;

public class Main {
    private Timer1 timer1;
    private Timer2 timer2;
    private Timer3 timer3;
    private JButton startButton, stopButton;
    private JTextArea logArea;

    public Main() {
        JFrame frame = new JFrame("Multi Timer App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        frame.add(new JScrollPane(logArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start Timers");
        stopButton = new JButton("Stop Timers");

        startButton.addActionListener(e -> startTimers());
        stopButton.addActionListener(e -> stopTimers());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        timer1 = new Timer1(this);
        timer2 = new Timer2(this);
        timer3 = new Timer3(this);
    }

    public void logMessage(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }

    private void startTimers() {
        logMessage("Starting all timers...");
        timer1.start();
        timer2.start();
        timer3.start();
    }

    private void stopTimers() {
        logMessage("Stopping all timers...");
        timer1.stop();
        timer2.stop();
        timer3.stop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
