import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Timer3 extends TimerTask {
    private JFrame frame;
    private boolean defaultTitle = true;

    public Timer3(Timer timer3, JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        if (defaultTitle) {
            frame.setTitle("Drink Watah!!!!!");
        } else {
            frame.setTitle("Super Workout");
        }
        defaultTitle = !defaultTitle;
    }
}
