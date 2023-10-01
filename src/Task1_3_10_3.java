
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Task1_3_10_3 extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static int speed = 2;
    int counter;

    static Snake_4 s = new Snake_4(5, 6, 5, 5, 5, 4);
    Timer timer = new Timer(1000 / speed, this);

    public Task1_3_10_3() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true); //Это чтобы находилось в центре экрана
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        for (int x = 0; x < WIDTH * SCALE; x += SCALE) {
            g.setColor(Color.black);
            g.drawLine(x, 0, x, HEIGHT * SCALE);
        }
        for (int y = 0; y < HEIGHT * SCALE; y += SCALE) {
            g.setColor(Color.black);
            g.drawLine(0, y, WIDTH * SCALE, y);
        }

        for (int l = 1; l < s.length; l++) {
            g.setColor(Color.green);
            g.fillRect(s.sX[l] * SCALE + 3, s.sY[l] * SCALE + 3, SCALE - 6, SCALE - 6);
            g.setColor(Color.white);
            g.fillRect(s.sX[0] * SCALE + 3, s.sY[0] * SCALE + 3, SCALE - 6, SCALE - 6);
        }
    }

    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                4. Доработайте змейку, что б при врезании в стену (край поля) - игра
                   оканчивалась
                
                Решение:\s""");

        jFrame = new JFrame("Snake");
        jFrame.setSize(WIDTH * SCALE + 14, HEIGHT * SCALE + 37);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new Task1_3_10_3());
        jFrame.setVisible(true);

    }

    public static int count(int counter) {
        counter = 0;
        return counter = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Появление первоначальной таблички про победу после 4-х яблок.
        s.move();
        timer.start();
        //Конец: Появление первоначальной таблички про победу после 4-х яблок.


        //Snake collided into wall.
        if (s.sX[0] > Task1_3_10_3.WIDTH - 1) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake crashed into the wall. Game over."/* + "Start again?"*/);
            jFrame.setVisible(false);
            System.exit(0);
        }
        if (s.sX[0] < 0) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake crashed into the wall. Game over."/* + "Start again?"*/);
            jFrame.setVisible(false);
            System.exit(0);
        }
        if (s.sY[0] > Task1_3_10_3.HEIGHT - 1) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake crashed into the wall. Game over."/* + "Start again?"*/);
            jFrame.setVisible(false);
            System.exit(0);
        }
        if (s.sY[0] < 0) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake crashed into the wall. Game over."/* + "Start again?"*/);
            jFrame.setVisible(false);
            System.exit(0);
        }
        repaint();

        counter = count (counter) + 1;
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed (KeyEvent event) {
            int key = event.getKeyCode();

            if ((key == KeyEvent.VK_UP) && (s.direction != 2)) s.direction = 0;
            if ((key == KeyEvent.VK_DOWN) && (s.direction != 0)) s.direction = 2;
            if ((key == KeyEvent.VK_RIGHT) && (s.direction != 3)) s.direction = 1;
            if ((key == KeyEvent.VK_LEFT) && (s.direction != 1)) s.direction = 3;
        }
    }
}
