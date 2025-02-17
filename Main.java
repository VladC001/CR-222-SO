import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

class SoundPlayer extends TimerTask {
    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Sound played");
    }
}

class Message extends TimerTask {
    private JLabel label;
    private String msg;

    public Message(JLabel label, String msg) {
        this.label = label;
        this.msg = msg;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> label.setText(msg));
        System.out.println(msg);
    }
}
class BackgroundColorChanger extends TimerTask {
    private JFrame frame;

    public BackgroundColorChanger(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            // Randomly change the background color
            Color randomColor = new Color((int) (Math.random() * 0x1000000));
            frame.getContentPane().setBackground(randomColor);
        });
        System.out.println("Background color changed");
    }
}
class TimerGUIApp {
    private Timer soundTimer;
    private Timer messageTimer;
    private Timer backgroundColorTimer;

    public TimerGUIApp() {
        soundTimer = new Timer();
        messageTimer = new Timer();
        backgroundColorTimer = new Timer();
    }

    public void startTimers(JFrame frame, JLabel messageLabel) {
        soundTimer.scheduleAtFixedRate(new SoundPlayer(), 0, 2000);
        messageTimer.schedule(new Message(messageLabel, "Salut, Salut, Salut!!!"), 5000);
        backgroundColorTimer.scheduleAtFixedRate(new BackgroundColorChanger(frame), 0, 3000);  // Change background every 3 seconds
        System.out.println("Timers started");
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Set up the GUI (frame and label)
            JFrame frame = new JFrame("Timer Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLayout(new FlowLayout());

            JLabel messageLabel = new JLabel("Waiting...");
            frame.add(messageLabel);
            frame.setVisible(true);

            // Initialize and start the timers
            TimerGUIApp timerGUIApp = new TimerGUIApp();
            timerGUIApp.startTimers(frame, messageLabel);

            System.out.println("Start");
        });
    }
}
