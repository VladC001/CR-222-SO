import java.awt.*;
import java.util.Arrays;

public class DiningPhilosophersGUI extends Frame {
    private static final int N = 12;
    private static final int RADIUS = 200;
    private static final int CIRCLE_SIZE = 50;
    private static final String[] stari = new String[N];

    private TextArea consola;

    static {
        Arrays.fill(stari, "Gandeste");
    }

    public DiningPhilosophersGUI() {
        setTitle("Cina Filosofilor");
        setSize(700, 700);
        setLayout(new BorderLayout());

        consola = new TextArea("", 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        consola.setEditable(false);
        add(consola, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void actualizeazaStare(int id, String stareNoua) {
        stari[id] = stareNoua;
    }

    public void log(String mesaj) {
        consola.append(mesaj + "\n");
    }

    public void paint(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2 - 50;

        for (int i = 0; i < N; i++) {
            double angle = 2 * Math.PI * i / N;
            int x = (int) (centerX + RADIUS * Math.cos(angle)) - CIRCLE_SIZE / 2;
            int y = (int) (centerY + RADIUS * Math.sin(angle)) - CIRCLE_SIZE / 2;

            switch (stari[i]) {
                case "Mananca":
                    g.setColor(Color.RED);
                    break;
                case "Asteapta":
                    g.setColor(Color.YELLOW);
                    break;
                default:
                    g.setColor(Color.GREEN);
                    break;
            }

            g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
            g.setColor(Color.BLACK);
            g.drawString("F" + i, x + 15, y + 30);
        }
    }
}
