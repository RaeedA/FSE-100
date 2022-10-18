public abstract class SnakeNode {
    public int x;
    public int y;
    protected boolean isLast;
    SnakeNode next;
    protected boolean noMove;

    public SnakeNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.isLast = true;
        this.next = null;
        this.noMove = true;
    }

    public void move(int x, int y) {
        if (noMove) {
            noMove = false;
        } else {
            int tempx = this.x;
            int tempy = this.y;
            this.x = x;
            this.y = y;
            if (next != null) {
                next.move(tempx, tempy);
            }
        }
    }

    public void add(SnakeNode node) {
        node.isLast = false;
        noMove = false;
        this.next = node;
    }
}