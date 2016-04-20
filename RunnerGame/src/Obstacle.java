import gpxUtilities.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public abstract class Obstacle implements ActionListener {

    protected BufferedImage img;
    protected SpriteSheet ss;

    Timer t = new Timer(20, this);
    int velx = 0, vely = 0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int relativeY = (int) screenSize.getHeight() * 3 / 4 - 150;
    int x = 1400, y = relativeY;
    public Shape boundingBox;

    public Obstacle() {
        t.start();
    }


    public void actionPerformed(ActionEvent e) {
        x += velx;
        y += vely;
        left();
        PlayAnimation();
        if (getClass() == Razor.class) {
            boundingBox = new Ellipse2D.Float(x, y + 2, img.getWidth(), img.getWidth());
        } else {
            int Ys[] = new int[]{y + 120, y + 107, y + 104, y, y + 100, y + 102, y + 120};
            int Xs[] = new int[]{x, x, x + 12, x + 41, x + 71, x + 81, x + 87};
            boundingBox = new Polygon(Xs, Ys, Xs.length);
        }

    }

    public void left() {
        vely = 0;
        velx = -10;
    }

    public abstract void PlayAnimation();

}
