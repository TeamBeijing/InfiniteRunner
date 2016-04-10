import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Panel extends JPanel implements ActionListener, KeyListener {

    Timer t = new Timer(2, this);
    Ninja n;
    Obstacle o;
    public Panel() {

        t.start();
        addKeyListener(this);
        setFocusable(true);
        setBounds(0, 0, 582, 583);
        n = new Ninja();
        o = new Obstacle();
        setFocusTraversalKeysEnabled(false);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(n.Img, n.x, n.y, null);
        g2.drawImage(o.Img, o.x, o.y, null);
        System.out.println(n.y);
        //This method check if the ninja is on ground or in the air.

    }


    public void actionPerformed(ActionEvent e) {
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