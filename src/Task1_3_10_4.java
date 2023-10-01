import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Task1_3_10_4 extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static int speed = 2;
    int counter;

    static Snake_4 s = new Snake_4(5, 6, 5, 5, 5, 4);
    Apple apple = new Apple(Math.abs((int) (Math.random() * Task1_3_10_4.WIDTH - 1)), Math.abs((int) (Math.random() * Task1_3_10_4.HEIGHT - 1)));
    Apple2 apple2 = new Apple2(Math.abs((int) (Math.random() * Task1_3_10_4.WIDTH - 1)), Math.abs((int) (Math.random() * Task1_3_10_4.HEIGHT - 1)));
    Timer timer = new Timer(1000 / speed, this);

    public Task1_3_10_4() {
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

        g.setColor(Color.red);
        g.fillOval(apple.posX * SCALE + 4, apple.posY * SCALE + 4, SCALE - 8, SCALE - 8);
        g.fillOval(apple2.posX2 * SCALE + 4, apple2.posY2 * SCALE + 4, SCALE - 8, SCALE - 8);
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
                4. Доработайте змейку, что б на поле были фрукты. Если змейка их съедает -
                   она растет. Если съела все фрукты - игрок победил.

                Решение:\s
                Мною добавлено уточняющее условие: победа наступает, если змейка съедает 4 яблока.""");

        jFrame = new JFrame("Snake");
        jFrame.setSize(WIDTH * SCALE + 14, HEIGHT * SCALE + 37);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new Task1_3_10_4());
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        timer.start();

        //пишем условие для победы (съедено 4 яблока)
        if ((s.sX[0] == apple.posX) && (s.sY[0] == apple.posY)) {
            s.countApples++;

            if (s.countApples == 4) {
                s.length++;
                repaint();
                timer.stop();
                JOptionPane.showMessageDialog(null, "You win. Snake ate 4 apples. Game over.");
                jFrame.setVisible(false);
                System.exit(0);
            }
        }

        if ((s.sX[0] == apple2.posX2) && (s.sY[0] == apple2.posY2)) {
            s.countApples++;

            if (s.countApples == 4) {
                s.length++;
                repaint();
                timer.stop();
                JOptionPane.showMessageDialog(null, "You win. Snake ate 4 apples. Game over.");
                jFrame.setVisible(false);
                System.exit(0);
            }
        }
        //конец условия для победы

        //Условие, что происходит после съедения яблока и пересечения самой себя.
        if ((s.sX[0] == apple.posX) && (s.sY[0] == apple.posY)) {
            apple.setRandomPosition();
            s.length++;
        }

        if ((s.sX[0] == apple2.posX2) && (s.sY[0] == apple2.posY2)) {
            apple2.setRandomPosition2();
            while (apple2.posX2 == apple.posX) {
                apple2.setRandomPosition2();
            }
            s.length++;
        }

        for (int l = 1; l < s.length; l++) {
            if ((s.sX[l] == apple.posX) && (s.sY[l] == apple.posY)) {
                apple.setRandomPosition();
            }

            if ((s.sX[l] == apple2.posX2) && (s.sY[l] == apple2.posY2)) {
                apple2.setRandomPosition2();
                while (apple2.posX2 == apple.posX) {
                    apple2.setRandomPosition2();
                }
            }
            //Конец условия, что происходит после съедения яблока.

            //Snake crossed itself
            if ((s.sX[0] == s.sX[l]) && (s.sY[0] == s.sY[l])) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "You lost. Snake crossed itself. Game over."/* + "Start again?"*/);
                jFrame.setVisible(false);
                System.exit(0);
            }
        }
        //Конец условия, что происходит после съедения яблока и пересечения самой себя.

        //Snake collided into wall.
        if (s.sX[0] > Task1_3_10_4.WIDTH - 1) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake collided into wall. Game over.");
            jFrame.setVisible(false);
            System.exit(0);
        }
        if (s.sX[0] < 0) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake collided into wall. Game over.");
            jFrame.setVisible(false);
            System.exit(0);
        }
        if (s.sY[0] > Task1_3_10_4.HEIGHT - 1) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake collided into wall. Game over.");
            jFrame.setVisible(false);
            System.exit(0);
        }
        if (s.sY[0] < 0) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "You lost. Snake collided into wall. Game over.");
            jFrame.setVisible(false);
            System.exit(0);
        }
        repaint();
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