public class Tail extends SnakeNode {
    public int prevx;
    public int prevy;

    public Tail(int x, int y) {
        super(x, y);
        prevx = x;
        prevy = y;
    }

    @Override
    public void move(int x, int y) {
        if (noMove) {
            noMove = false;
        } else {
            prevx = this.x;
            prevy = this.y;
            this.x = x;
            this.y = y;
            if (next != null) {
                next.move(prevx, prevy);
            }
        }
    }
}