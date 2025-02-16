import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        // Creează fereastra principală
        JFrame frame = new JFrame("Super Workout");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(20, 15);
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea));

        JButton stopButton = new JButton("Oprește timerul");
        frame.add(stopButton);

        JButton startAnimationButton = new JButton("Start animation");
        frame.add(startAnimationButton);

        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        Timer timer3 = new Timer();


        Timer1 timerTask1 = new Timer1(timer1, textArea);
        timer1.scheduleAtFixedRate(timerTask1, 0, 1000);


        stopButton.addActionListener(e -> {
            timerTask1.cancel();
            timer1.cancel();
            textArea.append("\nTimerul a fost oprit.");
        });


        startAnimationButton.addActionListener(e -> {
            new Timer2(timer2);
        });

        // Pornim Timer3 pentru a aminti să bem apă
        Timer3 timerTask3 = new Timer3(timer3, frame);
        timer3.scheduleAtFixedRate(timerTask3, 0, 5000);

        frame.setVisible(true);
    }
}
