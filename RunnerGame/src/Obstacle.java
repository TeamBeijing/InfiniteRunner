import gpxUtilities.BufferedImageLoader;
import gpxUtilities.SpriteSheet;
import sun.applet.Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public abstract class Obstacle implements ActionListener {

    protected BufferedImage img;
    protected SpriteSheet ss;

    Timer t = new Timer(20, this);
    int velx = 0, vely = 0;
    int x = 1400, y = 200;

    public Obstacle() {
        t.start();
    }


    public void actionPerformed(ActionEvent e) {
        x += velx;
        y += vely;
        left();
        PlayAnimation();
    }

    public void left() {
        vely = 0;
        velx = -10;
    }

    public abstract void PlayAnimation();

}
