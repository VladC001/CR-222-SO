package MultiTimer;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private Timer1 timer1;
    private Timer2 timer2;
    private Timer3 timer3;
    private JFrame frame;
    private Clip clip;

    public Main() {
        frame = new JFrame("Multi Timer App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Timer 1 Panel
        JPanel timer1Panel = new JPanel();
        JButton startTimer1Button = new JButton("Start");
        JButton stopTimer1Button = new JButton("Stop");

        startTimer1Button.addActionListener(e -> timer1.start());
        stopTimer1Button.addActionListener(e -> timer1.stop());

        timer1Panel.add(startTimer1Button);
        timer1Panel.add(stopTimer1Button);
        tabbedPane.addTab("Timer 1", timer1Panel);

        // Timer 2 Panel (Alarmă unică)
        JPanel timer2Panel = new JPanel();
        timer2Panel.setLayout(new GridLayout(3, 2));

        JLabel hourLabel = new JLabel("Ora:");
        JLabel minuteLabel = new JLabel("Minutul:");
        String[] hours = new String[24];
        String[] minutes = new String[60];

        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i);
        }
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }

        JComboBox<String> hourDropdown = new JComboBox<>(hours);
        JComboBox<String> minuteDropdown = new JComboBox<>(minutes);
        JButton setAlarmButton = new JButton("Setează Alarmă");

        setAlarmButton.addActionListener(e -> {
            int selectedHour = Integer.parseInt((String) hourDropdown.getSelectedItem());
            int selectedMinute = Integer.parseInt((String) minuteDropdown.getSelectedItem());
            timer2.setAlarm(selectedHour, selectedMinute);
        });

        timer2Panel.add(hourLabel);
        timer2Panel.add(hourDropdown);
        timer2Panel.add(minuteLabel);
        timer2Panel.add(minuteDropdown);
        timer2Panel.add(setAlarmButton);

        tabbedPane.addTab("Timer 2", timer2Panel);

        // Timer 3 Panel (cu selecție ore, minute, secunde)
        JPanel timer3Panel = new JPanel();
        timer3Panel.setLayout(new GridLayout(5, 2));

        JLabel hourLabel3 = new JLabel("Ora:");
        JLabel minuteLabel3 = new JLabel("Minute:");
        JLabel secondLabel3 = new JLabel("Secunde:");

        String[] hours3 = new String[24];
        String[] minutes3 = new String[60];
        String[] seconds3 = new String[60];

        for (int i = 0; i < 24; i++) {
            hours3[i] = String.format("%02d", i);
        }
        for (int i = 0; i < 60; i++) {
            minutes3[i] = String.format("%02d", i);
            seconds3[i] = String.format("%02d", i);
        }

        JComboBox<String> hourDropdown3 = new JComboBox<>(hours3);
        JComboBox<String> minuteDropdown3 = new JComboBox<>(minutes3);
        JComboBox<String> secondDropdown3 = new JComboBox<>(seconds3);

        JButton startTimer3Button = new JButton("Start");
        JButton stopTimer3Button = new JButton("Stop");

        // Label pentru afișarea timpului rămas
        JLabel timer3Display = new JLabel("00:00:00", SwingConstants.CENTER);
        timer3Display.setFont(new Font("Arial", Font.BOLD, 20));

        startTimer3Button.addActionListener(e -> {
            int h = Integer.parseInt((String) hourDropdown3.getSelectedItem());
            int m = Integer.parseInt((String) minuteDropdown3.getSelectedItem());
            int s = Integer.parseInt((String) secondDropdown3.getSelectedItem());
            timer3.setTimer(h, m, s);
            timer3.start();
        });

        stopTimer3Button.addActionListener(e -> timer3.stop());

        timer3Panel.add(hourLabel3);
        timer3Panel.add(hourDropdown3);
        timer3Panel.add(minuteLabel3);
        timer3Panel.add(minuteDropdown3);
        timer3Panel.add(secondLabel3);
        timer3Panel.add(secondDropdown3);
        timer3Panel.add(startTimer3Button);
        timer3Panel.add(stopTimer3Button);
        timer3Panel.add(timer3Display); // Adaugă JLabel-ul în UI

        tabbedPane.addTab("Timer 3", timer3Panel);

        // Adăugare TabbedPane în fereastră
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);

        // Inițializare Timere
        timer1 = new Timer1(this, startTimer1Button, stopTimer1Button);
        timer2 = new Timer2(this);
        timer3 = new Timer3(this, startTimer3Button, timer3Display);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void changeButtonColor(JButton button, Color color) {
        SwingUtilities.invokeLater(() -> button.setBackground(color));
    }

    public void playSound() {
        try {
            File soundFile = new File("C:\\Users\\Lenovo\\OneDrive\\Рабочий стол\\ASO_Lab\\CR-222-SO\\SO_Lab1\\src\\notification.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public void showAlarmPopup() {
        JDialog dialog = new JDialog(frame, "Alarmă!", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel("⏰ A sunat alarma!", SwingConstants.CENTER);
        JButton okButton = new JButton("OK");

        // Oprire sunet când se apasă OK
        okButton.addActionListener(e -> {
            stopSound();  // Oprește sunetul
            dialog.dispose();
        });

        dialog.add(label, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    public void showTimer3Popup() {
        shakeWindow(); // Activează tremuratul ferestrei

        JDialog dialog = new JDialog(frame, "Timer 3 a Finalizat!", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel("⏰ Timer 3 a expirat!", SwingConstants.CENTER);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(e -> dialog.dispose());

        dialog.add(label, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    public void shakeWindow() {
        int originalX = frame.getLocation().x;
        int originalY = frame.getLocation().y;

        Timer shakeTimer = new Timer();
        shakeTimer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                if (count >= 20) { // Tremură de 10 ori și se oprește
                    frame.setLocation(originalX, originalY);
                    shakeTimer.cancel();
                } else {
                    int offsetX = (int) (Math.random() * 10 - 5); // Random între -5 și 5
                    int offsetY = (int) (Math.random() * 10 - 5);
                    frame.setLocation(originalX + offsetX, originalY + offsetY);
                    count++;
                }
            }
        }, 0, 50); // Mișcare la fiecare 50ms
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
