import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {

    final static int SCREEN_SIZE = 500;
    final static int SNAKE_SIZE = 10;
    final static int SPEED = 50;

    final static Color BACKGROUND_COLOR = new Color(0,0,0);
    final static Color APPLE_COLOR = new Color(255, 0, 0);

    int headX = SCREEN_SIZE/2;
    int headY = SCREEN_SIZE/2;

    int appleX = (int) (Math.random()*(SCREEN_SIZE/10))*10;
    int appleY = (int) (Math.random()*(SCREEN_SIZE/10))*10;

    Snake snake = new Snake();
    int snakeLength = 2;

    int direction = 0; //(0 ->north) (1 -> east) (2 -> south) (3 -> west)

    boolean listenForKey;

    public void paintComponent(Graphics g) {
        listenForKey = true;

        if (direction == 0) {
            headY -= SNAKE_SIZE;  //north
        } else if (direction == 1) {
            headX += SNAKE_SIZE; //east
        } else if (direction == 2) {
            headY += SNAKE_SIZE; //south
        } else if (direction == 3) {
            headX -= SNAKE_SIZE; //west
        }

        g.setColor(APPLE_COLOR);
        g.fillRect(appleX, appleY, SNAKE_SIZE,SNAKE_SIZE);

        snake.paint(headX, headY, snakeLength, g);

        if (headX == appleX && headY == appleY) {
            snakeLength++;
            newApple();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37 && direction != 1 && listenForKey) {
            direction = 3;

        } else if (e.getKeyCode() == 39 && direction != 3 && listenForKey) {
            direction = 1;

        } else if (e.getKeyCode() == 40 && direction != 0 && listenForKey) {
            direction = 2;

        } else if (e.getKeyCode() == 38 && direction != 2 && listenForKey) {
            direction = 0;
        }
        listenForKey = false;

    }

    public void keyReleased(KeyEvent e) {  }

    public void keyTyped(KeyEvent e) {  }

    public void newApple() {
        appleX = (int) (Math.random()*(SCREEN_SIZE/10))*10;
        appleY = (int) (Math.random()*(SCREEN_SIZE/10))*10;
        int appPos = appleX*1000000 + appleY;

        for (int[] node : snake.nodes) {
            int nodePos = node[0]*1000000 + node[1];
            while (nodePos == appPos) {
                appleX = (int) (Math.random()*(SCREEN_SIZE/10))*10;
                appleY = (int) (Math.random()*(SCREEN_SIZE/10))*10;
                appPos = appleX*1000000 + appleY;
            }
        }
    }

    public Main() {
        Timer timer = new Timer(SPEED, arg0 -> repaint());
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main panel = new Main();
            panel.setBackground(BACKGROUND_COLOR);

            panel.addKeyListener(panel);
            panel.setFocusable(true);

            JFrame frame = new JFrame("Snake Game");
            frame.setSize(SCREEN_SIZE, SCREEN_SIZE+22);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}