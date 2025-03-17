package org.lab;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

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
        boolean acquiredLock = false;
        Style styleGreen = doc.addStyle("GreenStyle", null);
        StyleConstants.setForeground(styleGreen, Color.GREEN);
        StyleConstants.setBold(styleGreen, true);

        try {
            // Încercăm să intrăm în bibliotecă folosind metoda care utilizează ReentrantLock.
            bibliotecary.enterLibrary();
            acquiredLock = true;
            doc.insertString(doc.getLength(), "Writer(" + id + ") a intrat în bibliotecă.\n", null);

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
                    doc.insertString(doc.getLength(), "Writer(" + id + ") a început să scrie cartea " + currentBook + ".\n", styleGreen);
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
        } catch (InterruptedException e) {
            try {
                doc.insertString(doc.getLength(), "Writer(" + id + ") a fost întrerupt deoarece biblioteca era ocupată.\n", null);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        } finally {
            if (acquiredLock) {
                bibliotecary.exitLibrary();
            }
        }
    }
}
