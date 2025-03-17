package org.lab;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int x, y, z;
        x = 25;
        y = 34;
        z = 20;

        JFrame frame = new JFrame("Readers and Writers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();

        textPane.setText("Start\n");
        textPane.setFont(new Font("Arial", Font.PLAIN, 12));


        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.setSize(500, 500);


        Bibliotecary bibliotecary = new Bibliotecary();


        List<Thread> readers = new ArrayList<>();
        for (int i = 0; i < y; i++) {
            Thread reader = new Readers(doc, bibliotecary, z);
            readers.add(reader);
            reader.start();
        }


        List<Thread> writers = new ArrayList<>();
        int writerCount = 5;
        for (int i = 0; i < writerCount; i++) {
            Thread writer = new Writers(doc, bibliotecary, z);
            writers.add(writer);
            writer.start();
        }


        for (Thread reader : readers) {
            try {
                reader.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (Thread writer : writers) {
            try {
                writer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        JOptionPane optionPane = new JOptionPane("Sesiunea s-a terminat. Programul se va închide în 20 secunde.", JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Informație");
        dialog.setModal(false);
        dialog.setVisible(true);


        new javax.swing.Timer(20000, e -> {
            dialog.dispose();
            System.exit(0);
        }).start();
    }
}
