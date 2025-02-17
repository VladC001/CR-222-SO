// IntervalTimer.java
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class IntervalTimer {
    private JLabel intervalLabel;

    public IntervalTimer(JLabel intervalLabel) {
        this.intervalLabel = intervalLabel;
    }

    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;
            int seconds = 0;

            @Override
            public void run() {
                count++;
                seconds += 1;
                intervalLabel.setText("Times: " + count + ", Seconds: " + seconds);
                if (count % 5 == 0) {
                    intervalLabel.setText("Lab1_4 - Seconds: " + seconds);
                }
                if (count == 5) timer.cancel();
            }
        }, 0, 1000);
    }
}
