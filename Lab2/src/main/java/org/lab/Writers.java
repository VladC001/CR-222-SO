package org.lab;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class Writers extends Thread {
    private static int count = 0;
    private int id;
    private StyledDocument doc;
    private Bibliotecary bibliotecary;


    private static int totalBooksWritten = 0;
    private static int maxBooks = 0;
    private static boolean finishMessagePrinted = false;


    public Writers(StyledDocument doc, Bibliotecary bibliotecary, int maxBooks) {
        this.doc = doc;
        this.bibliotecary = bibliotecary;
        this.id = count++;

        synchronized (Writers.class) {
            if (Writers.maxBooks == 0) {
                Writers.maxBooks = maxBooks;
            }
        }
    }

    @Override
    public void run() {

        try {
            doc.insertString(doc.getLength(), "Writer(" + id + ") a intrat în bibliotecă.\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        while (true) {
            int currentBook;

            synchronized (Writers.class) {
                if (totalBooksWritten >= maxBooks) {
                    break; // Nu mai sunt cărți de scris
                }
                totalBooksWritten++;
                currentBook = totalBooksWritten;
            }


            try {
                doc.insertString(doc.getLength(), "Writer(" + id + ") a început să scrie cartea " + currentBook + ".\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }


            try {
                bibliotecary.writeBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                doc.insertString(doc.getLength(), "Writer(" + id + ") a terminat de scris cartea " + currentBook + ".\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }


        synchronized (Writers.class) {
            if (!finishMessagePrinted) {
                try {
                    doc.insertString(doc.getLength(), "Sesiunea scriitorilor s-a încheiat.\n", null);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                finishMessagePrinted = true;
            }
        }


        try {
            doc.insertString(doc.getLength(), "Writer(" + id + ") părăsește biblioteca.\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
