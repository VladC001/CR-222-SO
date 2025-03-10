import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AdditionalTimers {
    private static Timer timer1 = new Timer(); // Timer pentru ora exactă
    private static Timer timer2 = new Timer(); // Timer pentru delay fix
    private static Timer timer3 = new Timer(); // Timer pentru alte execuții

    public static void main(String[] args) {
        scheduleAtSpecificTime();
        scheduleWithFixedDelay();
        schedulePeriodicTask();

        // Oprim timerele după un timp de test (de exemplu, 30 secunde)
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                stopAllTimers();
            }
        }, 30000); // 30 secunde
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

        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task executat la ora exactă!");
            }
        }, delay);
    }

    // Cerința 3: Execută o sarcină cu o perioadă indicată (dar cu delay între execuții)
    public static void scheduleWithFixedDelay() {
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task executat cu delay fix între execuții.");
            }
        }, 2000, 5000); // Delay inițial de 2 secunde, apoi rulează la fiecare 5 secunde
    }

    // Adăugat un al treilea timer pentru un task periodic (Cerința 1)
    public static void schedulePeriodicTask() {
        timer3.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task periodic executat la fiecare 10 secunde.");
            }
        }, 0, 10000); // La fiecare 10 secunde
    }

    // Metodă pentru a opri toate timerele
    public static void stopAllTimers() {
        System.out.println("Oprire timere...");
        timer1.cancel();
        timer2.cancel();
        timer3.cancel();
        timer1.purge();
        timer2.purge();
        timer3.purge();
    }
}
