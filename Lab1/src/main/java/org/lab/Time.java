package org.lab;

import javax.swing.*;
import java.util.TimerTask;

public class Time {
    public TimerTask timer(){
        TimerTask task = new TimerTask() {
          @Override
          public void run() {
              JOptionPane.showMessageDialog(null, "Your time is out");
          }
        };
        return task;
    }
    public TimerTask alarm(String hour, String minute, String second){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Your time is out, remind you after:" + hour + ":" + minute + ":" + second);
            }
        };
        return task;
    }
    public TimerTask pomodoro(int workMinutes, int shortBreakMinutes, int longBreakMinutes, int sessions) {
        return new TimerTask() {
            private int sessionCount = 0;
            private boolean isWorkSession = true;

            public void run() {
                if (sessionCount < sessions * 2) {
                    if (isWorkSession) {
                        JOptionPane.showMessageDialog(null, "Work Session Started! Focus for " + workMinutes + " minutes.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Break Time! Relax for " + shortBreakMinutes + " minutes.");
                    }
                    isWorkSession = !isWorkSession;
                    sessionCount++;
                } else {
                    JOptionPane.showMessageDialog(null, "Great job! Take a long break of " + longBreakMinutes + " minutes.");
                    this.cancel();
                }
            }
        };
    }


}
