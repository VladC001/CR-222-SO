package MultiTimer;

import javax.swing.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Timer2 {
    private Timer timer;
    private Main app;
    private boolean isAlarmSet = false;

    public Timer2(Main app) {
        this.app = app;
    }

    public void setAlarm(int hour, int minute) {
        if (isAlarmSet) {
            JOptionPane.showMessageDialog(app.getFrame(), "⚠️ O alarmă este deja setată!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Calendar now = Calendar.getInstance();
        Calendar alarmTime = (Calendar) now.clone();
        alarmTime.set(Calendar.HOUR_OF_DAY, hour);
        alarmTime.set(Calendar.MINUTE, minute);
        alarmTime.set(Calendar.SECOND, 0);

        long delay = alarmTime.getTimeInMillis() - now.getTimeInMillis();
        if (delay < 0) {
            JOptionPane.showMessageDialog(app.getFrame(), "⚠️ Ora selectată este în trecut!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        timer = new Timer();
        isAlarmSet = true;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                app.playSound();
                app.showAlarmPopup();
                isAlarmSet = false;
            }
        }, delay);

        JOptionPane.showMessageDialog(app.getFrame(), "✅ Alarmă setată pentru " + hour + ":" + String.format("%02d", minute), "Succes", JOptionPane.INFORMATION_MESSAGE);
    }
}
