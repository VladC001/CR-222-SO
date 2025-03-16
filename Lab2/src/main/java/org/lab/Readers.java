package org.lab;

import javax.swing.*;
import java.util.Random;

public class Readers extends Thread {
    private static int count = 0;
    private int id;
    private static JTextArea textArea;
    private static Bibliotecary bibliotecary;
    private static int z;
    public Readers(JTextArea textArea, Bibliotecary bibliotecary, int z) {
        Readers.textArea = textArea;
        Readers.bibliotecary = bibliotecary;
        Readers.z = z;
    }
    @Override
    public void run() {
        Random rand = new Random();
        synchronized (this) {
            id = count++;
            textArea.setEditable(true);
            textArea.append("Reader(" + id + ") start session\n");
            textArea.setEditable(false);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int n = 0;
        while (n < z){
            try {
                textArea.setEditable(true);
                textArea.append("Reader(" + id + ") start reading " + n + " book\n");
                textArea.setEditable(false);

                Thread.sleep(500 + rand.nextInt(3000 - 500 + 1));

                textArea.setEditable(true);
                textArea.append("Reader(" + id + ") end reading " + n + " book\n");
                textArea.setEditable(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            n++;
        }
        textArea.setEditable(true);
        textArea.append("Reader("+id+") end session\n");
        textArea.setEditable(false);
    }
}
