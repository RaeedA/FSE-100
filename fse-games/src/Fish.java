import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class Fish {
    public int speed;
    public int x;
    public int y;
    public Paint color;
    public int length;
    public EventHandler<MouseEvent> handler;

    public Fish(int x, int y, int speed, Paint color, int length, EventHandler<MouseEvent> handler) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.color = color;
        this.length = length;
        this.handler = handler;
    }

    public int move() {
        x += speed;
        return x;
    }
}
