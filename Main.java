import javax.swing.*;
import java.awt.*;
import java.util.Timer;

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

        Timer timer1 = new Timer();
  //For You     //Timer timer2 = new Timer();
  //For You       //Timer timer3 = new Timer();


        Timer1 timerTask1 = new Timer1(timer1, textArea);
        timer1.scheduleAtFixedRate(timerTask1, 0, 1000);
//Timer1 timerTask2 = new Timer1(timer2, textArea2);
//Timer1 timerTask3 = new Timer1(timer3, textArea3);

        stopButton.addActionListener(e -> {
            timerTask1.cancel();
            timer1.cancel();
            textArea.append("\nTimerul a fost oprit.");
        });

        // Afișează fereastra
        frame.setVisible(true);
    }
}
