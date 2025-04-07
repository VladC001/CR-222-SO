import javax.swing.*;
import java.awt.*;

class PhilosopherGUI extends JFrame {
    private final JTextArea statusArea;

    public PhilosopherGUI(int numPhilosophers) {
        setTitle("Dining Philosophers");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusArea = new JTextArea();
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void updateStatus(int id, String status) {
        SwingUtilities.invokeLater(() -> {
            statusArea.append("Philosopher " + id + " is " + status + "\n");
            statusArea.setCaretPosition(statusArea.getDocument().getLength());
        });
    }
}
