package MultiTimer;

import java.util.Timer;
import java.util.TimerTask;

public class Timer3 {
    private Timer timer;
    private Main app;

    public Timer3(Main app) {
        this.app = app;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                app.logMessage("Timer 3: Se repetă la fiecare 6 secunde");
            }
        }, 0, 6000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            app.logMessage("Timer 3 oprit");
        }
    }
}
