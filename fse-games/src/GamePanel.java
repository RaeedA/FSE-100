import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GamePanel extends GridPane {
    private Rectangle[][] map;
    private ArrayList<SnakeNode> snakeSections = new ArrayList<SnakeNode>();
    private final int speed = 100;
    private int length;
    private int foodx;
    private int foody;
    private Head head;
    private final Paint BODY = Color.GREEN;
    private final Paint FOOD = Color.RED;
    private final Paint GROUND = Color.BLACK;
    private final int UP = 2;
    private final int RIGHT = 3;
    private final int DOWN = 4;
    private final int LEFT = 5;
    private EventHandler<KeyEvent> keyLog;
    private int inputDirection;
    private int currentDirection;
    private int nextDirection;
    public final int STOP = 0;
    public final int PLAY = 1;
    public final int PAUSE = 2;
    private int state;
    private Label scoreLabel;
    private int score;

    public GamePanel() {
        super();
        int height = 21;
        int len = 21;
        this.length = 1;
        this.map = new Rectangle[height][len];
        this.currentDirection = RIGHT;
        this.nextDirection = RIGHT;
        this.inputDirection = RIGHT;
        for (int h = 0; h < height; h++) {
            for (int l = 0; l < len; l++) {
                Rectangle temp = new Rectangle(30, 30, GROUND);
                setConstraints(temp, l, h);
                getChildren().addAll(temp);
                map[h][l] = temp;
            }
        }
        Tail body = new Tail(10, 8);
        this.head = new Head(10, 8, body);
        snakeSections.add(head);
        snakeSections.add(body);
        this.foodx = 10;
        this.foody = 12;
        state = STOP;

        keyLog = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        inputDirection = UP;
                        break;
                    case DOWN:
                        inputDirection = DOWN;
                        break;
                    case LEFT:
                        inputDirection = LEFT;
                        break;
                    case RIGHT:
                        inputDirection = RIGHT;
                        break;
                    case SHIFT:
                        update();
                        break;
                    default:
                        break;
                }
                if ((currentDirection % 2 != inputDirection % 2)) {
                    nextDirection = inputDirection;
                }
            }
        };

        setOnKeyPressed(keyLog);

        render();
    }

    private void update() {
        move();

        if (head.x == foodx && head.y == foody) {
            Tail temp = (Tail) snakeSections.get(length);
            Tail t = new Tail(temp.x, temp.y);
            temp.add(t);
            snakeSections.add(t);
            length++;
            this.map[temp.prevx][temp.prevy].setFill(GROUND);
            boolean foundPos = false;
            while (!foundPos) {
                foodx = ThreadLocalRandom.current().nextInt(0, 21);
                foody = ThreadLocalRandom.current().nextInt(0, 21);
                foundPos = map[foodx][foody].getFill() != BODY;
            }
        }
        score = length - 1;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scoreLabel.setText("" + score);
            }
        });
        render();
    }

    private void move() {
        currentDirection = nextDirection;
        switch (currentDirection) {
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
        this.map[foodx][foody].setFill(FOOD);
        try {
            Tail temp = (Tail) snakeSections.get(snakeSections.size() - 1);
            this.map[temp.prevx][temp.prevy].setFill(GROUND);
            this.map[head.x][head.y].setFill(BODY);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You Lose!");
            System.exit(0);
        }
    }

    public EventHandler<KeyEvent> getKeyLogger() {
        return keyLog;
    }

    public void start() {
        try {
            state = PLAY;
            while (state == PLAY) {
                TimeUnit.MILLISECONDS.sleep(speed);
                update();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        state = PAUSE;
    }

    public void resume() {
        start();
    }

    public void setScore(Label l) {
        scoreLabel = l;
    }
}