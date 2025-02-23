package MultiTimer;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Timer3 {
    private Main main;
    private Timer timer;
    private long timeLeft; // Timp rămas în milisecunde
    private JLabel timerLabel; // Label pentru afișarea timpului

    public Timer3(Main main, JButton startButton, JLabel timerLabel) {
        this.main = main;
        this.timer = new Timer();
        this.timerLabel = timerLabel;
        startButton.setBackground(null);
    }

    public void setTimer(int hours, int minutes, int seconds) {
        timeLeft = (hours * 3600 + minutes * 60 + seconds) * 1000L;
        updateLabel();
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeLeft <= 0) {
                    timer.cancel();
                    main.showTimer3Popup();
                } else {
                    timeLeft -= 10; // Scade 10 milisecunde
                    updateLabel();
                }
            }
        }, 0, 10); // Actualizare la fiecare 10ms
    }

    public void stop() {
        timer.cancel();
    }

    private void updateLabel() {
        long hours = timeLeft / (1000 * 3600);
        long minutes = (timeLeft % (1000 * 3600)) / (1000 * 60);
        long seconds = (timeLeft % (1000 * 60)) / 1000;
        long milliseconds = timeLeft % 1000;

        timerLabel.setText(String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds));
    }
}
