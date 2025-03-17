package org.lab;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Random;

public class Readers extends Thread {
    private static int count = 0;
    private int id;
    private static StyledDocument doc;
    private static Bibliotecary bibliotecary;
    private static int z;
    public Readers(StyledDocument doc, Bibliotecary bibliotecary, int z) {
        Readers.doc = doc;
        Readers.bibliotecary = bibliotecary;
        Readers.z = z;
    }
    @Override
    public void run() {
        Random rand = new Random();

        Style styleRed = doc.addStyle("RedStyle", null);
        StyleConstants.setForeground(styleRed, Color.RED);
        StyleConstants.setBold(styleRed, true);

        synchronized (this) {
            //Unique id for readers
            id = count++;
            //Notify to start session
            try {
                doc.insertString(doc.getLength(),"Reader(" + id + ") start session\n",null);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
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
                doc.insertString(doc.getLength(),"Reader(" + id + ") start reading " + n + " book\n",null);

                //wait random times between 0.5 and 3 seconds
                Thread.sleep(500 + rand.nextInt(3000 - 500 + 1));

                //display end of reading book
                doc.insertString(doc.getLength(),"Reader(" + id + ") end reading " + n + " book\n",null);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            n++;
        }
        //display end of session
        try {
            doc.insertString(doc.getLength(),"Reader("+id+") end session\n", styleRed);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }
}
