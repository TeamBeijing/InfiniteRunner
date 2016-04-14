
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import gpxUtilities.BufferedImageLoader;
import gpxUtilities.SpriteSheet;
import sun.applet.Main;

import javax.swing.*;

public class Ninja implements ActionListener, KeyListener {

    public BufferedImage Img;
    SpriteSheet ssRun, ssJump, ssDie;
    Timer t = new Timer(100, this);
    int velx = 0, vely = 0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int relativeY = (int) screenSize.getHeight() * 3 / 4 - 115; //the Y depends on the screen size
    int x = 150, y = relativeY;
    int jumpheight = relativeY - 220; //220px jump
    int runSpriteOffcet = 5, jumpSpriteOffcet = 0, dieSpriteOffcet = 5;
    boolean isOnGround = true, jumpAnimationAllowed = true;
    public Shape boundingBox;
    public boolean isDead = false;

    public Ninja() {

        t.start();
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetRun = null;
        BufferedImage spriteSheetJump = null;
        int[] Xs = new int[]{x, x + 30, x + 57, x + 83, x + 71, x + 50, x + 56, x + 50, x + 24, x + 12, x + 15, x};
        int[] Ys = new int[]{y + 22, y + 20, y, y + 21, y + 44, y + 55, y + 80, y + 86, y + 86, y + 65, y + 50, y + 35};
        boundingBox = new Polygon(Xs, Ys, Xs.length);
        BufferedImage spriteSheetDie = null;

        try {
            spriteSheetRun = loader.loadImage("src/textures/ninjaRun.png");
            spriteSheetJump = loader.loadImage("src/textures/ninjaJump.png");
            spriteSheetDie = loader.loadImage("src/textures/ninjaDie.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ssRun = new SpriteSheet(spriteSheetRun);
        ssJump = new SpriteSheet(spriteSheetJump);
        ssDie = new SpriteSheet(spriteSheetDie);
    }

    public void actionPerformed(ActionEvent e) {
        x += velx;
        y += vely;
        checkNinjaLocation();
        if (isOnGround) {
            int[] Xs = new int[]{x, x + 30, x + 57, x + 83, x + 71, x + 50, x + 56, x + 50, x + 24, x + 12, x + 15, x};
            int[] Ys = new int[]{y + 22, y + 20, y, y + 21, y + 44, y + 55, y + 80, y + 86, y + 86, y + 65, y + 50, y + 35};
            boundingBox = new Polygon(Xs, Ys, Xs.length);
        } else {
            int[] Xs = new int[]{x + 10, x + 30, x + 35, x + 72, x + 73, x + 62, x + 51, x + 35, x + 33, x + 10};
            int[] Ys = new int[]{y + 41, y + 34, y + 9, y + 9, y + 52, y + 65, y + 80, y + 80, y + 60, y + 56};
            boundingBox = new Polygon(Xs, Ys, Xs.length);
        }
    }

    public void up() {
        if (isOnGround) {
            t.setDelay(15);
            vely = -10;
            velx = 0;
            isOnGround = false;
            jump();
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left();
        }
    }

    public void down() {
        vely = 10;
        velx = 0;
    }

    public void left() {
        vely = 0;
        velx = -10;
    }

    public void right() {
        vely = 0;
        velx = 10;
    }

    public void jump() {
        if (jumpAnimationAllowed) {
            this.Img = ssJump.grabSprite(jumpSpriteOffcet, 0, 82, 85);
            jumpSpriteOffcet += 80;
        }
        if (jumpSpriteOffcet >= 240) {
            jumpSpriteOffcet = 240;
            jumpAnimationAllowed = false;
        }
        runSpriteOffcet = 5;
    }

    public void run() {
        this.Img = ssRun.grabSprite(runSpriteOffcet, 0, 79, 85);
        runSpriteOffcet += 82;
        if (runSpriteOffcet >= 450) {
            runSpriteOffcet = 5;
        }
        jumpAnimationAllowed = true;
        jumpSpriteOffcet = 0;
    }

    public void die() {
        if (y != relativeY) {
            y = relativeY;
        }
        velx = 0;
        this.Img = ssDie.grabSprite(dieSpriteOffcet, 0, 95, 85);
        dieSpriteOffcet += 98;
        if (dieSpriteOffcet >= 450) {
            dieSpriteOffcet = 0;
            isDead = true;
        }
        jumpAnimationAllowed = false;
    }

    public void checkNinjaLocation() {

        //If the ninja is at highest point at the jump tell it to fall down.
        if (y < jumpheight) {
            down();
        }
        //IF the ninja is on the ground set timer to go slower and make it run
        else if (y == relativeY) {
            isOnGround = true;
            t.setDelay(100);
            vely = 0;
            run();
        }
        //If ninja is going for jump play the animation
        else if (y > jumpheight && y < relativeY) {
            jump();
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}