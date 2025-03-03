import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class OtherTimers {
    private static Timer alarmTimer;
    private static Timer pomodoroTimer;
    private static boolean isPomodoroSession = true;

    public static void setAlarm(int hour, int minute, JLabel alarmLabel) {
        if (alarmTimer != null) {
            alarmTimer.cancel();
        }

        alarmTimer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // Dacă ora alarmei a trecut, setează pentru ziua următoare
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        long alarmTimeMillis = calendar.getTimeInMillis();
        long delayInSeconds = (alarmTimeMillis - System.currentTimeMillis()) / 1000;

        alarmLabel.setText(String.format("Alarm Set: %02d:%02d", hour, minute));

        alarmTimer.scheduleAtFixedRate(new TimerTask() {
            long timeLeft = delayInSeconds;

            @Override
            public void run() {
                if (timeLeft > 0) {
                    long hours = timeLeft / 3600;
                    long minutes = (timeLeft % 3600) / 60;
                    long seconds = timeLeft % 60;
                    alarmLabel.setText(String.format("Alarm in: %02d:%02d:%02d", hours, minutes, seconds));
                    timeLeft--;
                } else {
                    alarmTimer.cancel();
                    repeatBeep(5, 1000);
                    alarmLabel.setText("Alarm Beeping!");

                    // Resetează alarma automat pentru ziua următoare
                    SwingUtilities.invokeLater(() -> setAlarm(hour, minute, alarmLabel));
                }
            }
        }, 0, 1000);
    }


    public static void startPomodoro(int sessionMinutes, int breakMinutes, JLabel pomodoroLabel) {
        if (pomodoroTimer != null) {
            pomodoroTimer.cancel();
        }
        pomodoroTimer = new Timer();
        runPomodoroCycle(sessionMinutes, breakMinutes, pomodoroLabel);
    }

    private static void runPomodoroCycle(int sessionMinutes, int breakMinutes, JLabel pomodoroLabel) {
        if (pomodoroTimer != null) {
            pomodoroTimer.cancel();
            pomodoroTimer.purge();
        }

        pomodoroTimer = new Timer();
        int duration = isPomodoroSession ? sessionMinutes * 60 : breakMinutes * 60;
        pomodoroLabel.setText(isPomodoroSession ? "Work Session" : "Break");

        pomodoroTimer.scheduleAtFixedRate(new TimerTask() {
            int timeLeft = duration;

            @Override
            public void run() {
                if (timeLeft > 0) {
                    int min = timeLeft / 60;
                    int sec = timeLeft % 60;
                    pomodoroLabel.setText(String.format("%s: %02d:%02d", isPomodoroSession ? "Work" : "Break", min, sec));
                    timeLeft--;
                } else {
                    pomodoroTimer.cancel();
                    repeatBeep(5, 1000);
                    isPomodoroSession = !isPomodoroSession;
                    SwingUtilities.invokeLater(() -> runPomodoroCycle(sessionMinutes, breakMinutes, pomodoroLabel));
                }
            }
        }, 0, 1000);
    }

    public static void repeatBeep(int times, int interval) {
        Timer beepTimer = new Timer();
        beepTimer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                if (count < times) {
                    Toolkit.getDefaultToolkit().beep();
                    count++;
                } else {
                    beepTimer.cancel();
                }
            }
        }, 0, interval);
    }
}
