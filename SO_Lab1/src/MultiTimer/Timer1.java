package MultiTimer;

import java.util.Timer;
import java.util.TimerTask;

public class Timer1 {
    private Timer timer;
    private Main app;

    public Timer1(Main app) {
        this.app = app;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                app.logMessage("Timer 1: Se execută la fiecare 2 secunde");
            }
        }, 0, 2000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            app.logMessage("Timer 1 oprit");
        }
    }
}
