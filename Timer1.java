import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class Timer1 extends TimerTask {
    private String[] arr = new String[]{
            "more footwork",
            "more passion",
            "more energy",
            "more energy",
            "more passion",
            "hoy hoy hoyyyyy"};
    private int n = 20;
    private Timer timer;
    private JTextArea textArea;

    public Timer1(Timer timer, JTextArea textArea) {
        this.timer = timer;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        if (n > 0) {
            textArea.append(rand() + "\n");
            n--;
        } else {
            cancel();
            timer.cancel();
            //textArea.append("\nTask completat. Timerul s-a oprit.\n");
        }
    }

    private String rand() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(arr.length);
        return arr[randomIndex];
    }
}