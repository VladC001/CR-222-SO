import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainTimer extends JFrame {
    private JLabel timerLabel = new JLabel("00:00");
    private JLabel alarmLabel = new JLabel("Alarm Set: --:--");
    private JLabel pomodoroLabel = new JLabel("Pomodoro: 00:00");

    private Timer generalTimer;

    public MainTimer() {
        setTitle("Task Scheduler");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        // Timer Panel
        JPanel timerPanel = new JPanel(new GridLayout(3, 2));
        timerPanel.setBorder(BorderFactory.createTitledBorder("Timer"));
        JTextField minuteField = new JTextField("00");
        JTextField secondField = new JTextField("00");
        JButton startTimerButton = new JButton("Start Timer");

        timerPanel.add(new JLabel("Minutes:"));
        timerPanel.add(minuteField);
        timerPanel.add(new JLabel("Seconds:"));
        timerPanel.add(secondField);
        timerPanel.add(startTimerButton);
        timerPanel.add(timerLabel);

        // Alarm Panel
        JPanel alarmPanel = new JPanel(new GridLayout(3, 2));
        alarmPanel.setBorder(BorderFactory.createTitledBorder("Alarm"));
        JTextField alarmHourField = new JTextField("00");
        JTextField alarmMinuteField = new JTextField("00");
        JButton startAlarmButton = new JButton("Set Alarm");

        alarmPanel.add(new JLabel("Hour:"));
        alarmPanel.add(alarmHourField);
        alarmPanel.add(new JLabel("Minute:"));
        alarmPanel.add(alarmMinuteField);
        alarmPanel.add(startAlarmButton);
        alarmPanel.add(alarmLabel);

        // Pomodoro Panel
        JPanel pomodoroPanel = new JPanel(new GridLayout(3, 2));
        pomodoroPanel.setBorder(BorderFactory.createTitledBorder("Pomodoro"));
        JTextField sessionField = new JTextField("25");
        JTextField breakField = new JTextField("5");
        JButton startPomodoroButton = new JButton("Start Pomodoro");

        pomodoroPanel.add(new JLabel("Session (min):"));
        pomodoroPanel.add(sessionField);
        pomodoroPanel.add(new JLabel("Break (min):"));
        pomodoroPanel.add(breakField);
        pomodoroPanel.add(startPomodoroButton);
        pomodoroPanel.add(pomodoroLabel);

        add(timerPanel);
        add(alarmPanel);
        add(pomodoroPanel);

        // Evenimente pentru butoane
        startTimerButton.addActionListener(e -> {
            int minutes = Integer.parseInt(minuteField.getText());
            int seconds = Integer.parseInt(secondField.getText());
            startTimer(minutes, seconds);
        });

        startAlarmButton.addActionListener(e -> {
            int hour = Integer.parseInt(alarmHourField.getText());
            int minute = Integer.parseInt(alarmMinuteField.getText());
            OtherTimers.setAlarm(hour, minute, alarmLabel);
        });

        startPomodoroButton.addActionListener(e -> {
            int sessionTime = Integer.parseInt(sessionField.getText());
            int breakTime = Integer.parseInt(breakField.getText());
            OtherTimers.startPomodoro(sessionTime, breakTime, pomodoroLabel);
        });
    }

    private void startTimer(int minutes, int seconds) {
        int totalSeconds = (minutes * 60) + seconds;
        if (generalTimer != null) {
            generalTimer.cancel();
        }

        generalTimer = new Timer();
        countdown(totalSeconds);
    }

    private void countdown(int timeLeft) {
        if (timeLeft < 0) {
            timerLabel.setText("Time's up!");
            OtherTimers.repeatBeep(5, 1000);
            return;
        }

        int min = timeLeft / 60;
        int sec = timeLeft % 60;
        timerLabel.setText(String.format("%02d:%02d", min, sec));

        generalTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                countdown(timeLeft - 1);
            }
        }, 1000);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainTimer mainTimer = new MainTimer();
            mainTimer.setVisible(true);
        });
    }
}
