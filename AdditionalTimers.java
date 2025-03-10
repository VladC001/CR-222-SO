import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AdditionalTimers {
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        scheduleAtSpecificTime();
        scheduleWithFixedDelay();
    }

    // Cerința 2: Execută un task la o anumită oră
    public static void scheduleAtSpecificTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15); // Setează ora 15:00
        calendar.set(Calendar.MINUTE, 30); // Setează minutul 30
        calendar.set(Calendar.SECOND, 0);

        long delay = calendar.getTimeInMillis() - System.currentTimeMillis();
        if (delay < 0) {
            delay += 24 * 60 * 60 * 1000; // Dacă ora a trecut, setează pentru ziua următoare
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task executat la ora exactă!");
            }
        }, delay);
    }

    // Cerința 3: Execută o sarcină cu o perioadă indicată (dar cu delay între execuții)
    public static void scheduleWithFixedDelay() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task executat cu delay fix între execuții.");
            }
        }, 2000, 5000); // Delay inițial de 2 secunde, apoi rulează la fiecare 5 secunde
    }
}
