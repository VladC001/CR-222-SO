import javax.swing.*;
import java.awt.*;

public class Interfata extends JFrame {
    private JLabel intervalLabel, timeLabel, periodLabel;
    private JButton startIntervalButton, startTimeButton, startPeriodButton;
    private TimeTimer timeTimer;
    private IntervalTimer intervalTimer;
    private PeriodTimer periodTimer;

    public Interfata() {
        setTitle("Multi Timer Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        timeLabel = new JLabel("Time Timer: Not started", JLabel.CENTER);
        intervalLabel = new JLabel("Interval Timer: Not started", JLabel.CENTER);
        periodLabel = new JLabel("Period Timer: Not started", JLabel.CENTER);

        startTimeButton = new JButton("Start Time Timer");
        startIntervalButton = new JButton("Start Interval Timer");
        startPeriodButton = new JButton("Start Period Timer");

        add(timeLabel);
        add(startTimeButton);
        add(intervalLabel);
        add(startIntervalButton);
        add(periodLabel);
        add(startPeriodButton);

        timeTimer = new TimeTimer(timeLabel);
        intervalTimer = new IntervalTimer(intervalLabel);
        periodTimer = new PeriodTimer(periodLabel);

        startTimeButton.addActionListener(e -> timeTimer.start());
        startIntervalButton.addActionListener(e -> intervalTimer.start());
        startPeriodButton.addActionListener(e -> periodTimer.start());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfata::new);
    }
}
