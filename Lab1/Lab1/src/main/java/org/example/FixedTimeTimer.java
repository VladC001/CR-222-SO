package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FixedTimeTimer extends JFrame {
    private JTextArea textArea;
    private Timer fixedTimeTimer;

    public FixedTimeTimer() {
        setTitle("Fixed Time Timer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        fixedTimeTimer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 01);
        calendar.set(Calendar.MINUTE, 12);
        calendar.set(Calendar.SECOND, 0);
        Date scheduledTime = calendar.getTime();
        fixedTimeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> textArea.append("Fixed Time Timer: Eveniment la ora programată!\n"));
            }
        }, scheduledTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FixedTimeTimer app = new FixedTimeTimer();
            app.setVisible(true);
        });
    }
}

