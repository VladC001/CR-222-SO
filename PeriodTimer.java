// PeriodTimer.java
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PeriodTimer {
    private JLabel periodLabel;

    public PeriodTimer(JLabel periodLabel) {
        this.periodLabel = periodLabel;
    }

    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int seconds = 0;

            @Override
            public void run() {
                seconds++;
                periodLabel.setText("Seconds: " + seconds);
                if (seconds % 4 == 0) {
                    periodLabel.setText("Markel - Seconds: " + seconds);
                }
            }
        }, 0, 1000);
    }
}
