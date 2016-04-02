import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends JPanel implements ActionListener, KeyListener {

    private BufferedImage Img;

    Timer t = new Timer(5, this);
    int velx = 0, vely = 0;
    int x = 40, y = 50;


    public Player() {

        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        try {
            Img = ImageIO.read(new File("src/textures/run.gif"));
        }
        catch(IOException ex)  {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Img, x, y, null);
    }


    public void actionPerformed(ActionEvent e) {
        repaint();
        x += velx;
        y += vely;
    }

    public void up() {
        vely = -1;
        velx = 0;
    }

    public void down() {
        vely = 1;
        velx = 0;
    }

    public void left() {
        vely = 0;
        velx = -1;
    }

    public void right() {
        vely = 0;
        velx = 1;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left();
        }

        if (key == KeyEvent.VK_RIGHT) {
            right();
        }

        if (key == KeyEvent.VK_UP) {
            up();
        }

        if (key == KeyEvent.VK_DOWN) {
            down();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}