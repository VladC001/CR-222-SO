package org.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class UI {
    private final JFrame frame;
    private Timer Alarm = new Timer("Alarm");
    private boolean AlarmStarted = false;

    public UI() {
        frame = new JFrame("Time Management");
        display();
    }

    public void display() {
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Alarm", createAlarmTab());
        tabbedPane.addTab("Timer", createTimerTab());
        tabbedPane.addTab("Pomodoro", createPomodoroTab());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createAlarmTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Set Alarm Time", SwingConstants.CENTER);
        panel.add(label);

        JPanel timeSelection = createTimeSelectionPanel();
        panel.add(timeSelection);

        JButton buttonAlarm = new JButton("Set Alarm");
        buttonAlarm.setPreferredSize(new Dimension(100, 30));

        panel.add(buttonAlarm);

        buttonAlarm.addActionListener(e -> {
            String hour = getSelectedValue(timeSelection, 0);
            String minute = getSelectedValue(timeSelection, 1);
            String second = getSelectedValue(timeSelection, 2);

            if (hour != null && minute != null && second != null) {
                int delay = Integer.parseInt(hour) * 3600000 + Integer.parseInt(minute) * 60000 + Integer.parseInt(second) * 1000;
                Time time = new Time();
                Alarm.scheduleAtFixedRate(time.alarm(hour, minute, second), delay, delay);
                JOptionPane.showMessageDialog(frame, "Alarm set for " + hour + ":" + minute + ":" + second);
            }
        });
        return panel;
    }

    private JPanel createTimerTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Set Timer Duration", SwingConstants.CENTER);
        panel.add(label);

        JPanel timeSelection = createTimeSelectionPanel();
        panel.add(timeSelection);

        JButton buttonTimer = new JButton("Start Timer");
        panel.add(buttonTimer);

        buttonTimer.addActionListener(e -> {
            String hour = getSelectedValue(timeSelection, 0);
            String minute = getSelectedValue(timeSelection, 1);
            String second = getSelectedValue(timeSelection, 2);

            if (hour != null && minute != null && second != null) {
                int delay = Integer.parseInt(hour) * 3600000 + Integer.parseInt(minute) * 60000 + Integer.parseInt(second) * 1000;
                Time time = new Time();
                new Timer().schedule(time.timer(), delay);
                JOptionPane.showMessageDialog(frame, "Timer set for " + hour + ":" + minute + ":" + second);
            }
        });
        return panel;
    }

    private JPanel createPomodoroTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Pomodoro Timer", SwingConstants.CENTER);
        panel.add(label);

        JButton buttonStartPomodoro = new JButton("Start Pomodoro");
        panel.add(buttonStartPomodoro);

        buttonStartPomodoro.addActionListener(e -> {
            Time time = new Time();
            int workMinutes = 25;
            int shortBreakMinutes = 5;
            int longBreakMinutes = 15;
            int sessions = 4;

            Timer pomodoroTimer = new Timer();
            pomodoroTimer.scheduleAtFixedRate(
                    time.pomodoro(workMinutes, shortBreakMinutes, longBreakMinutes, sessions),
                    0,
                    workMinutes * 60000 + shortBreakMinutes * 60000
            );

            JOptionPane.showMessageDialog(frame, "Pomodoro started: Work for " + workMinutes + " min, then break!");
        });
        return panel;
    }

    private JPanel createTimeSelectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        String[] hour = generateTimeArray(24);
        String[] minute = generateTimeArray(60);
        String[] second = generateTimeArray(60);

        JList<String> listHour = new JList<>(hour);
        JList<String> listMinute = new JList<>(minute);
        JList<String> listSecond = new JList<>(second);

        panel.add(new JScrollPane(listHour));
        panel.add(new JScrollPane(listMinute));
        panel.add(new JScrollPane(listSecond));
        return panel;
    }

    private String getSelectedValue(JPanel panel, int index) {
        JScrollPane scrollPane = (JScrollPane) panel.getComponent(index);
        JList<?> list = (JList<?>) scrollPane.getViewport().getView();
        return (String) list.getSelectedValue();
    }

    private String[] generateTimeArray(int size) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = String.format("%02d", i);
        }
        return array;
    }
}
