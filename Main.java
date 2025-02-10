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

public class TimerGUIApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Timer Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JLabel messageLabel = new JLabel("Waiting...");
        frame.add(messageLabel);
        frame.setVisible(true);

        Timer soundTimer = new Timer();
        Timer messageTimer = new Timer();

        soundTimer.scheduleAtFixedRate(new SoundPlayer(), 0, 2000);
        messageTimer.schedule(new Message(messageLabel, "Salut, Salut, Salut!!!"), 5000);

        System.out.println("Start");
    }
}
