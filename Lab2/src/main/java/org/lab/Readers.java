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
            //Unique id for readers
            id = count++;
            textArea.setEditable(true);
            //Notify to start session
            textArea.append("Reader(" + id + ") start session\n");
            textArea.setEditable(false);
        }
        try {
            //wait before start reading
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //initialize contour
        int n = 0;
        while (n < z){
            try {
                //display current book to read
                textArea.setEditable(true);
                textArea.append("Reader(" + id + ") start reading " + n + " book\n");
                textArea.setEditable(false);

                //wait random times between 0.5 and 3 seconds
                Thread.sleep(500 + rand.nextInt(3000 - 500 + 1));

                //display end of reading book
                textArea.setEditable(true);
                textArea.append("Reader(" + id + ") end reading " + n + " book\n");
                textArea.setEditable(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            n++;
        }
        //display end of session
        textArea.setEditable(true);
        textArea.append("Reader("+id+") end session\n");
        textArea.setEditable(false);
    }
}
