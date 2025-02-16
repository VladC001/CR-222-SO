import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Timer2 extends TimerTask {
    private JFrame animationFrame;
    private JLabel gifLabel;

    public Timer2(Timer timer2) {

        animationFrame = new JFrame("Animation");
        animationFrame.setSize(300, 300);
        animationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        animationFrame.setLayout(new BorderLayout());


        gifLabel = new JLabel(new ImageIcon("animation.gif"));
        animationFrame.add(gifLabel, BorderLayout.CENTER);

        animationFrame.setVisible(true);


        timer2.schedule(this, 5000);
    }

    @Override
    public void run() {
        animationFrame.dispose();
    }
}
