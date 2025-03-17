package org.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.CountDownLatch;

public class Main extends Application {
    private TextArea displayArea;
    private final org.example.demo.Library library = new org.example.demo.Library();

    @Override
    public void start(Stage primaryStage) {
        displayArea = new TextArea();
        displayArea.setEditable(false);

        createWritersAndReaders(primaryStage);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(displayArea);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Scriitori și Cititori");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createWritersAndReaders(Stage primaryStage) {
        int totalThreads = 12 + 16;
        CountDownLatch latch = new CountDownLatch(totalThreads);

        // Scriitori
        for (int i = 1; i <= 12; i++) {
            int writerId = i;
            new Thread(() -> {
                Writer writer = new Writer("Scriitor " + writerId, library);
                for (int j = 1; j <= 11; j++) {
                    writer.writeBook();
                    updateDisplay(writer.getName() + " a scris cartea " + j + ".\n");
                }
                latch.countDown();
            }).start();
        }

        // Cititori
        for (int i = 1; i <= 16; i++) {
            int readerId = i;
            new Thread(() -> {
                Reader reader = new Reader("Cititor " + readerId, library);
                for (int j = 1; j <= 11; j++) {
                    reader.readBook();
                    updateDisplay(reader.getName() + " a citit cartea " + j + ".\n");
                }
                latch.countDown();
            }).start();
        }

        // Fir de execuție pentru închiderea aplicației
        new Thread(() -> {
            try {
                // Așteaptă finalizarea tuturor firelor
                latch.await();

                // Adaugă delay de 30 de secunde
                Thread.sleep(30000);

                // Închide interfața grafică
                Platform.runLater(() -> {
                    primaryStage.close();
                    Platform.exit();
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateDisplay(String message) {
        Platform.runLater(() -> displayArea.appendText(message));
    }

    public static void main(String[] args) {
        launch(args);
    }
}