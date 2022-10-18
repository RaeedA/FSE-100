import javax.swing.*;

public class App {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        GamePanel p = new GamePanel();
        frame.getContentPane().add(p);
        frame.addKeyListener(p.getKeyLogger());
        frame.setVisible(true);
        p.start();
    }
}