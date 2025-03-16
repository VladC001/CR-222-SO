package org.lab;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int x , y , z;
        x = 25;
        y = 34;
        z = 20;
        //Frame
        JFrame frame = new JFrame("Readers and Writers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Text area for view processes
        JTextArea textArea = new JTextArea();
        textArea.setText("Start\n");
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setEditable(false);

        //scroll pane for view all history
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.setSize(500, 500);

        //none functional
        Bibliotecary bibliotecary = new Bibliotecary();

        //initialize y readers
        for (int i = 0; i < y; i++) {
            new Readers(textArea, bibliotecary, z).start();
        }
    }
}