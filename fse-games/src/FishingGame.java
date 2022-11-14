import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

public class FishingGame extends GridPane {
    private Rectangle[][] map;
    private ArrayList<Fish> fishes = new ArrayList<Fish>();
    private ArrayList<Fish> removedFishes = new ArrayList<Fish>();
    private final int speed = 100;
    private Basket basket;
    private final Paint SKY = Color.CORNFLOWERBLUE;
    private final Paint BASKET = Color.BURLYWOOD;
    private final Paint WATER = Color.BLUE;
    public final int STOP = 0;
    public final int PLAY = 1;
    public final int PAUSE = 2;
    private int state;
    private Label scoreLabel;
    private int score;
    private int height = 1;

    public FishingGame() {
        super();
        int height = 21;
        int len = 21;
        this.map = new Rectangle[height][len];
        for (int h = 0; h < height; h++) {
            for (int l = 0; l < len; l++) {
                Rectangle temp;
                if (l >= 7) {
                    temp = new Rectangle(30, 30, WATER);
                    temp.setStroke(WATER);
                } else {
                    temp = new Rectangle(30, 30, SKY);
                    temp.setStroke(SKY);
                }
                setConstraints(temp, l, h);
                getChildren().addAll(temp);
                map[h][l] = temp;
            }
        }
        this.basket = new Basket(len - 2, height / 8 + 1);

        for (int i = -2; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.map[basket.x - j][basket.y + i].setFill(BASKET);
                this.map[basket.x - j][basket.y + i].setStroke(BASKET);
            }
        }
        this.map[basket.x - 1][basket.y + 1].setFill(SKY);
        this.map[basket.x - 1][basket.y + 1].setStroke(SKY);
        this.map[basket.x - 1][basket.y - 1].setFill(SKY);
        this.map[basket.x - 1][basket.y - 1].setStroke(SKY);
        this.map[basket.x - 1][basket.y + 0].setFill(SKY);
        this.map[basket.x - 1][basket.y + 0].setStroke(SKY);

        state = STOP;
        render();
    }

    private void render() {
        int boundL = 7;
        int boundR = 20;
        for (Fish f : removedFishes) {
            for (int i = 0; i < f.length + 1; i++) {
                if (f.x - f.speed * i <= boundR && f.x - f.speed * i >= boundL) {
                    map[f.y][f.x - f.speed * i].setFill(WATER);
                    map[f.y][f.x - f.speed * i].setStroke(WATER);
                    map[f.y][f.x - f.speed * i].removeEventHandler(MouseEvent.MOUSE_PRESSED, f.handler);
                }
            }
        }
        removedFishes.clear();
        for (Fish f : fishes) {
            for (int i = 0; i < f.length; i++) {
                if (f.x - f.speed * i <= boundR && f.x - f.speed * i >= boundL) {
                    map[f.y][f.x - f.speed * i].setFill(f.color);
                    map[f.y][f.x - f.speed * i].setStroke(f.color);
                    map[f.y][f.x - f.speed * i].addEventHandler(MouseEvent.MOUSE_PRESSED, f.handler);
                }
            }
            if (f.x - (f.speed * f.length) <= boundR && f.x - (f.speed * f.length) >= boundL) {
                map[f.y][f.x - (f.speed * f.length)].setFill(WATER);
                map[f.y][f.x - (f.speed * f.length)].setStroke(WATER);
                map[f.y][f.x - (f.speed * f.length)].removeEventHandler(MouseEvent.MOUSE_PRESSED, f.handler);
            }
        }
    }

    private void update() {
        generateFish();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scoreLabel.setText("" + score);
            }
        });
        move();
        render();
    }

    private void move() {
        for (Fish f : fishes) {
            int pos = f.move();
            if (pos + f.length <= 6 || pos - f.length >= 21) {
                removedFishes.add(f);
            }
        }
        for (Fish f : removedFishes) {
            fishes.remove(f);
        }
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

    private boolean generateFish() {
        Random rand = new Random();
        if (rand.nextFloat() >= .9) {
            int speed = 0;
            int x;
            int y;
            if (rand.nextFloat() >= .5) {
                speed++;
                x = 6;
            } else {
                speed--;
                x = 21;
            }
            y = rand.nextInt(21);
            Paint color;
            switch (rand.nextInt(7)) {
                case 0:
                    color = Color.RED;
                    break;
                case 1:
                    color = Color.ORANGE;
                    break;
                case 2:
                    color = Color.YELLOW;
                    break;
                case 3:
                    color = Color.GREEN;
                    break;
                case 4:
                    color = Color.PURPLE;
                    break;
                case 5:
                    color = Color.DARKORCHID;
                    break;
                case 6:
                    color = Color.GHOSTWHITE;
                    break;
                default:
                    color = Color.PALEGREEN;
                    break;
            }
            int length = rand.nextInt(3) + 1;
            Fish f = new Fish(x, y, speed, color, length, new MouseChecker(color, length));
            fishes.add(f);
            return true;
        } else {
            return false;
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

    private class MouseChecker implements EventHandler<MouseEvent> {

        Paint color;
        int length;

        public MouseChecker(Paint color, int length) {
            super();
            this.color = color;
            this.length = length;
        }

        @Override
        public void handle(MouseEvent event) {
            int pos = new Random().nextInt(4 - length) - 1;
            for (int i = 0; i < length; i++) {
                map[basket.x - height][basket.y - (i + pos)].setFill(color);
                map[basket.x - height][basket.y - (i + pos)].setStroke(Color.BLACK);
            }
            height++;
            if (height > 21) {
                height = 21;
            }
            score++;
        }

    }
}