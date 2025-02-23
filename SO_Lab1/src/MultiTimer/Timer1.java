package MultiTimer;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Timer1 {
    private Timer timer;
    private Main app;
    private JButton startButton;
    private JButton stopButton;

    private Color[] colors = {Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA};
    private int colorIndex = 0;

    public Timer1(Main app, JButton startButton, JButton stopButton) {
        this.app = app;
        this.startButton = startButton;
        this.stopButton = stopButton;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                app.changeButtonColor(startButton, colors[colorIndex]);
                app.changeButtonColor(stopButton, colors[colorIndex]);

                colorIndex = (colorIndex + 1) % colors.length;
            }
        }, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
