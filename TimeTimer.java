// TimeTimer.java
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTimer {
    private JLabel timeLabel;

    public TimeTimer(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconds = 5;

            @Override
            public void run() {
                timeLabel.setText(seconds + " seconds!");
                new Timer().schedule(new TimerTask() {
                    int totalSeconds = seconds + 5;

                    @Override
                    public void run() {
                        timeLabel.setText("Lab1SO - Seconds: " + totalSeconds);
                    }
                }, 5000);
            }
        }, 5000);
    }
}
