package MultiTimer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Timer2 {
    private Timer timer;
    private Main app;

    public Timer2(Main app) {
        this.app = app;
    }

    public void start() {
        timer = new Timer();
        Calendar calendar = Calendar.getInstance();

        // Setare timp exact (executare la ora curentă + 10 secunde)
        calendar.add(Calendar.SECOND, 10);
        Date executionTime = calendar.getTime();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                app.logMessage("Timer 2: Acțiune la timpul specificat!");
            }
        }, executionTime);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            app.logMessage("Timer 2 oprit");
        }
    }
}
