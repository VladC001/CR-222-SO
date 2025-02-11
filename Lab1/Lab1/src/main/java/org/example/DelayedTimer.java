package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class DelayedTimer extends JFrame {
    private JTextArea textArea;
    private Timer delayedTimer;

    public DelayedTimer() {
        setTitle("Delayed Timer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        delayedTimer = new Timer();
        delayedTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> textArea.append("Delayed Timer: Acțiune întârziată executată!\n"));
            }
        }, 5000, 3000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DelayedTimer app = new DelayedTimer();
            app.setVisible(true);
        });
    }
}