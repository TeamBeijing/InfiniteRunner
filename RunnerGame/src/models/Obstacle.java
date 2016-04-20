package models;

import gpxUtilities.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public abstract class Obstacle implements ActionListener {

    private Timer timer;
    private int velx = 0, vely = 0;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int relativeY = (int) screenSize.getHeight() * 3 / 4 - 150;

    protected int x = 1400, y = relativeY;
    protected Shape boundingBox;
    protected BufferedImage img;
    protected SpriteSheet ss;

    public Obstacle() {
        this.timer = new Timer(20, this);
        this.timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        this.x += this.velx;
        this.y += this.vely;
        this.left();
        PlayAnimation();
        if (getClass() == Razor.class) {
            this.boundingBox = new Ellipse2D.Float(this.x, this.y + 2, this.img.getWidth(), this.img.getWidth());
        } else {
            int Ys[] = new int[]{this.y + 120, this.y + 107, this.y + 104, this.y, this.y + 100, this.y + 102, this.y + 120};
            int Xs[] = new int[]{this.x, this.x, this.x + 12, this.x + 41, this.x + 71, this.x + 81, this.x + 87};
            this.boundingBox = new Polygon(Xs, Ys, Xs.length);
        }

    }

    public BufferedImage getImg(){
        return this.img;
    }

    public Shape getBoundingBox(){
        return this.boundingBox;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void left() {
        this.vely = 0;
        this.velx = -10;
    }

    public abstract void PlayAnimation();
}
