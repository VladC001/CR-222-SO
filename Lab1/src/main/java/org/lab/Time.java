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
}
