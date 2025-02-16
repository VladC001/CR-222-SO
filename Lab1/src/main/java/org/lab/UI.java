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
    public UI(){
        frame = new JFrame();
        display();
    }
    public void display() {
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel label = new JLabel("Beings of time");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(frame.getWidth()/3+25,0,200,40);
        frame.add(label);

        JButton buttonAlarm = new JButton("Alarm");
        buttonAlarm.setFont(new Font("Arial", Font.BOLD, 18));
        buttonAlarm.setBounds(frame.getWidth()/3+75,200,100,40);
        frame.add(buttonAlarm);

        JButton buttonTimer = new JButton("Timer");
        buttonTimer.setFont(new Font("Arial", Font.BOLD, 18));
        buttonTimer.setBounds(frame.getWidth()/3-75,200,100,40);
        frame.add(buttonTimer);

        JButton buttonStopAll = new JButton("Stop");
        buttonStopAll.setFont(new Font("Arial", Font.BOLD, 18));
        buttonStopAll.setBounds(frame.getWidth()/3,260,100,40);
        frame.add(buttonStopAll);

        String[] hour = new String[24];
        for (int i = 0; i < 24; i++) {
            hour[i] = String.format("%02d", (i));
        }
        JList<String> listHour = new JList<>(hour);
        JScrollPane pane = new JScrollPane(listHour);
        pane.setBounds(frame.getWidth()/3-100,50,75,100);

        String[] minute = new String[60];
        for (int i = 0; i < minute.length; i++) {
            minute[i] = String.format("%02d", (i));
        }
        JList<String> listMinute = new JList<>(minute);
        JScrollPane pane1 = new JScrollPane(listMinute);
        pane1.setBounds(frame.getWidth()/3,50,75,100);

        String[] second = new String[60];
        for (int i = 0; i < second.length; i++) {
            second[i] = String.format("%02d", (i));
        }
        JList<String> listSecond = new JList<>(second);
        JScrollPane pane2 = new JScrollPane(listSecond);
        pane2.setBounds(frame.getWidth()/3+100,50,75,100);

        frame.add(pane);
        frame.add(pane1);
        frame.add(pane2);

        buttonAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(listHour.getSelectedValue() == null || listMinute.getSelectedValue() == null || listSecond.getSelectedValue() == null))
                {
                    if (!(listHour.getSelectedValue().equals(hour[0]) && listMinute.getSelectedValue().equals(minute[0]) && listSecond.getSelectedValue().equals(second[0])))
                    {
                        if (!AlarmStarted)
                        {
                            JOptionPane.showMessageDialog(frame, "Alarm sent after: " +
                                    listHour.getSelectedValue() + " hour "
                                    + listMinute.getSelectedValue() + " minute "
                                    + listSecond.getSelectedValue() + " second "
                            );
                            Time time = new Time();
                            int delay = Integer.parseInt(listHour.getSelectedValue()) * 3600000 + Integer.parseInt(listMinute.getSelectedValue()) * 60000 + Integer.parseInt(listSecond.getSelectedValue()) * 1000;
                            Alarm.scheduleAtFixedRate(time.alarm(listHour.getSelectedValue(), listMinute.getSelectedValue(), listSecond.getSelectedValue()), delay, delay);
                            AlarmStarted = true;
                        }
                        else JOptionPane.showMessageDialog(frame, "Alarm is set.");
                    }
                    else JOptionPane.showMessageDialog(frame, "Alarm denied");
                }
                else JOptionPane.showMessageDialog(frame, "Set time");
            }
        });

        buttonTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(listHour.getSelectedValue() == null || listMinute.getSelectedValue() == null || listSecond.getSelectedValue() == null))
                {
                    if (!(listHour.getSelectedValue().equals(hour[0]) && listMinute.getSelectedValue().equals(minute[0]) && listSecond.getSelectedValue().equals(second[0])))
                    {
                        JOptionPane.showMessageDialog(frame, "Timer set notify after: " +
                                listHour.getSelectedValue() + " hour "
                                + listMinute.getSelectedValue() + " minute "
                                + listSecond.getSelectedValue() + " second "
                        );
                        Time time = new Time();
                        int delay = Integer.parseInt(listHour.getSelectedValue()) * 3600000 + Integer.parseInt(listMinute.getSelectedValue()) * 60000 + Integer.parseInt(listSecond.getSelectedValue()) * 1000;
                        new Timer().schedule(time.timer(), delay);
                    }
                    else JOptionPane.showMessageDialog(frame, "Timer denied");
                }
                else JOptionPane.showMessageDialog(frame, "Set time");
            }
        });
        buttonStopAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alarm.cancel();
                JOptionPane.showMessageDialog(frame, "Alarm cancelled");
            }
        });
    }
}
