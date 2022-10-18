public class Head extends SnakeNode {

    public Head(int x, int y, SnakeNode next) {
        super(x, y);
        add(next);
    }
}