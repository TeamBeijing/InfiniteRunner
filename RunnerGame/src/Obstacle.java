import gpxUtilities.SpriteSheet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public abstract class Obstacle implements ActionListener {

    protected BufferedImage img;
    protected SpriteSheet ss;

    Timer t = new Timer(20, this);
    int velx = 0, vely = 0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int relativeY = (int) screenSize.getHeight() * 3 / 4 - 150;
    int x = 1400, y = relativeY;
    public Rectangle boundingBox;

    public Obstacle() {
        t.start();
    }


    public void actionPerformed(ActionEvent e) {
        x += velx;
        y += vely;
        left();
        PlayAnimation();
        boundingBox = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    public void left() {
        vely = 0;
        velx = -10;
    }

    public abstract void PlayAnimation();

}
