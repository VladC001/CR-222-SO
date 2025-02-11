package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class IntervalTimer extends JFrame {
    private JTextArea textArea;
    private Timer intervalTimer;

    public IntervalTimer() {
        setTitle("Interval Timer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        intervalTimer = new Timer();
        intervalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> textArea.append("Interval Timer: Mesaj afișat!\n"));
            }
        }, 0, 2000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IntervalTimer app = new IntervalTimer();
            app.setVisible(true);
        });
    }
}
