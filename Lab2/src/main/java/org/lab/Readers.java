package org.lab;

import javax.swing.*;

public class Readers extends Thread {
    private static int count = 0;
    private static JTextArea textArea;
    public Readers(JTextArea textArea) {
        Readers.textArea = textArea;
    }
    @Override
    public void run() {
        textArea.setEditable(true);
        textArea.setText("Reader("+count+") start session");
        textArea.setEditable(false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
