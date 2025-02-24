package org.lab;

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.util.TimerTask;

public class Time {

    // Metoda auxiliară pentru redarea sunetului
    public void playSound(String soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public TimerTask timer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                playSound("src/main/resources/timer_sound.wav");
                JOptionPane.showMessageDialog(null, "Your time is out");
            }
        };
        return task;
    }

    public TimerTask alarm(String hour, String minute, String second){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                playSound("src/main/resources/alarm_sound.wav");
                JOptionPane.showMessageDialog(null, "Your time is out, remind you after: " + hour + ":" + minute + ":" + second);
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
