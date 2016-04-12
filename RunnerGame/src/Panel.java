import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Panel extends JPanel implements ActionListener, KeyListener {

    Timer t = new Timer(2, this);
    Ninja n;
    ObstacleDatabase obstacleDB;
    private long tStart = System.currentTimeMillis();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    CollisionDetector checkForCollision;

    public Panel() {

        t.start();
        addKeyListener(this);
        setFocusable(true);
        n = new Ninja();
        obstacleDB = new ObstacleDatabase();
        setFocusTraversalKeysEnabled(false);
        checkForCollision = new CollisionDetector();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.draw(n.boundingBox); //see the boundingBox - Ninja
        for (Obstacle obst : obstacleDB.obstacles) {
            g2.drawImage(obst.img, obst.x, obst.y, null);
            //g2.draw(obst.boundingBox);  //see the boundingBox - obstacles
        }
        g2.drawImage(n.Img, n.x, n.y, null);
        //print the score in the top right corner
        g2.setFont(new Font("Consolas", Font.BOLD, 40));
        g2.drawString(
                String.format("%1$10d",
                        (System.currentTimeMillis() - tStart) / 50)
                        .replaceAll(" ", "0"),
                (int) screenSize.getWidth() - 240, 50);
    }


    public void actionPerformed(ActionEvent e) {
        if (checkForCollision.CollisionDetector(n, obstacleDB)) {
            t.stop();
            GameOver go = new GameOver();
            go.setBounds(0,0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
            this.getParent().getParent().getParent().add(go, new Integer(2), 0);

        }
        repaint();
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            n.left();
        }

        if (key == KeyEvent.VK_RIGHT) {
            n.right();
        }

        if (key == KeyEvent.VK_UP) {
            n.up();
        }

        if (key == KeyEvent.VK_DOWN) {
            n.down();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}