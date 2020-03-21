import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Snake {
    final static Color SNAKE_COLOR = new Color(0,255,0);

    ArrayList<int[]> nodes = new ArrayList<>();

    public void paint(int headX, int headY, int length, Graphics g) {
        g.setColor(SNAKE_COLOR);

        int[] head = new int[]{headX, headY};
        nodes.add(0, head);

        if (length < nodes.size()) {
            if (hasDuplicate(new ArrayList<>(nodes.subList(0, length - 1)))) {
                System.exit(0);
            }
        }
        if (headX < 0
            || headX >= Main.SCREEN_SIZE
            || headY < 0
            || headY >= Main.SCREEN_SIZE) {
            System.exit(0);
        }

        int nodeX;
        int nodeY;

        for (int i = 0; i < length; i++) {
            if (nodes.size() <= i) {
                nodeX = Main.SCREEN_SIZE/2;
                nodeY = Main.SCREEN_SIZE/2;
            } else {
                nodeX = nodes.get(i)[0];
                nodeY = nodes.get(i)[1];
            }

            g.fillRect(nodeX, nodeY, Main.SNAKE_SIZE, Main.SNAKE_SIZE);

        }

    }

    public boolean hasDuplicate(ArrayList<int[]> nodes) {
        Set set = new HashSet<Integer>();

        int position;
        for (int[] node : nodes) {
            position = node[0] * 1000000 + node[1];
            set.add(position);
        }
        return set.size() < nodes.size();
    }
}