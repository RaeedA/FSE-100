import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel {
    private JLabel[][] map;
    private ArrayList<SnakeNode> snakeSections = new ArrayList<SnakeNode>();
    private final int speed = 100;
    private int length;
    private int area;
    private int foodx;
    private int foody;
    private Head head;
    private final Color BODY = Color.green;
    private final Color FOOD = Color.RED;
    private final Color GROUND = Color.BLACK;
    private final int UP = 2;
    private final int RIGHT = 3;
    private final int DOWN = 4;
    private final int LEFT = 5;
    private KeyAdapter keyLog;
    private int inputDirection;
    private int direction;

    public GamePanel() {
        super();
        int height = 21;
        int len = 21;
        this.length = 1;
        this.area = height * len;
        this.map = new JLabel[height][len];
        this.direction = RIGHT;
        this.inputDirection = RIGHT;
        for (int h = 0; h < height; h++) {
            for (int l = 0; l < len; l++) {
                JLabel temp = new JLabel();
                temp.setOpaque(true);
                this.add(temp);
                this.map[h][l] = temp;
            }
        }
        Tail body = new Tail(10, 7);
        this.head = new Head(10, 8, body);
        snakeSections.add(head);
        snakeSections.add(body);
        this.foodx = 10;
        this.foody = 12;
        this.setLayout(new GridLayout(height, len));

        keyLog = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    inputDirection = UP;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    inputDirection = DOWN;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    inputDirection = LEFT;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    inputDirection = RIGHT;
                }

                if ((direction % 2 != inputDirection % 2)) {
                    direction = inputDirection;
                }
            }
        };

        render();
    }

    private void update() {
        move();
        if (head.x == foodx && head.y == foody) {
            SnakeNode temp = snakeSections.get(snakeSections.size() - 1);
            Tail t = new Tail(temp.x, temp.y);
            temp.add(t);
            snakeSections.add(t);
            length++;
            foodx = ThreadLocalRandom.current().nextInt(0, 21);
            foody = ThreadLocalRandom.current().nextInt(0, 21);
        }
        render();
    }

    private void move() {
        switch (direction) {
            case UP:
                head.move(head.x - 1, head.y);
                break;
            case DOWN:
                head.move(head.x + 1, head.y);
                break;
            case LEFT:
                head.move(head.x, head.y - 1);
                break;
            case RIGHT:
                head.move(head.x, head.y + 1);
                break;
        }
    }

    private void render() {
        for (int h = 0; h < 21; h++) {
            for (int l = 0; l < 21; l++) {
                this.map[h][l].setBackground(GROUND);
            }
        }

        this.map[foodx][foody].setBackground(FOOD);
        for (SnakeNode node : snakeSections) {
            try {
                this.map[node.x][node.y].setBackground(BODY);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You Lose!");
                System.exit(0);
            }
        }
    }

    public KeyAdapter getKeyLogger() {
        return keyLog;
    }

    public void start() {
        try {
            System.out.println("3");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("2");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("1");
            TimeUnit.SECONDS.sleep(1);
            for (int i = 0; i < 500; i++) {
                TimeUnit.MILLISECONDS.sleep(speed);
                update();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}