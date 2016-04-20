package models;

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

public class Ninja implements ActionListener {

    private BufferedImage Img;
    private SpriteSheet ssRun, ssJump, ssDie;
    private Timer timer;
    private int velx = 0, vely = 0;
    private Dimension screenSize;
    private int relativeY;
    private int x = 150, y;
    private int jumpheight;
    private int runSpriteOffcet = 5, jumpSpriteOffcet = 0, dieSpriteOffcet = 5;
    private boolean isOnGround = true, jumpAnimationAllowed = true;
    private Shape boundingBox;
    private boolean isDead = false;
    private int lives = 3;

    public Ninja() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.relativeY = (int) screenSize.getHeight() * 3 / 4 - 115; //the Y depends on the screen size
        this.y = relativeY;
        this.jumpheight = relativeY - 220; //220px jump
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetRun = null;
        BufferedImage spriteSheetJump = null;
        int[] Xs = new int[]{this.x, this.x + 30, this.x + 57, this.x + 83, this.x + 71, this.x + 50, this.x + 56, this.x + 50, this.x + 24, this.x + 12, this.x + 15, this.x};
        int[] Ys = new int[]{this.y + 22, this.y + 20, this.y, this.y + 21, this.y + 44, this.y + 55, this.y + 80, this.y + 86, this.y + 86, this.y + 65, this.y + 50, this.y + 35};
        this.boundingBox = new Polygon(Xs, Ys, Xs.length);
        BufferedImage spriteSheetDie = null;

        try {
            spriteSheetRun = loader.loadImage("src/textures/ninjaRun.png");
            spriteSheetJump = loader.loadImage("src/textures/ninjaJump.png");
            spriteSheetDie = loader.loadImage("src/textures/ninjaDie.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.ssRun = new SpriteSheet(spriteSheetRun);
        this.ssJump = new SpriteSheet(spriteSheetJump);
        this.ssDie = new SpriteSheet(spriteSheetDie);
        this.timer = new Timer(100, this);
        this.timer.start();
    }

    public void setIsDead(){
        this.isDead = true;
    }

    public boolean getIsDead(){
        return this.isDead;
    }

    public boolean getIsOnGround(){
        return this.isOnGround;
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

    public void setVelX(int value){
        this.velx = value;
    }

    public int getLives(){
        return this.lives;
    }

    public BufferedImage getImg(){
        return this.Img;
    }

    public void actionPerformed(ActionEvent e) {
        this.x += this.velx;
        if (this.x > (int) this.screenSize.getWidth() - 85) {
            this.x = (int) this.screenSize.getWidth() - 85;
        }
        if (this.x < 0) {
            this.x = 0;
        }
        this.y += this.vely;
        if (this.y > this.relativeY) {
            this.y = this.relativeY;
        }
        checkNinjaLocation();
        if (this.isOnGround) {
            int[] Xs = new int[]{this.x, this.x + 30, this.x + 57, this.x + 83, this.x + 71, this.x + 50, this.x + 56, this.x + 50, this.x + 24, this.x + 12, this.x + 15,this.x};
            int[] Ys = new int[]{this.y + 22, this.y + 20, this.y, this.y + 21, this.y + 44, this.y + 55, this.y + 80, this.y + 86, this.y + 86, this.y + 65, this.y + 50, this.y + 35};
            this.boundingBox = new Polygon(Xs, Ys, Xs.length);
        } else {
            int[] Xs = new int[]{this.x + 10, this.x + 30, this.x + 35, this.x + 72, this.x + 73, this.x + 62, this.x + 51, this.x + 35, this.x + 33, this.x + 10};
            int[] Ys = new int[]{this.y + 41, this.y + 34,this.y + 9, this.y + 9, this.y + 52, this.y + 65, this.y + 80, this.y + 80, this.y + 60, this.y + 56};
            this.boundingBox = new Polygon(Xs, Ys, Xs.length);
        }
    }

    public void up() {
        if (this.isOnGround) {
            this.timer.setDelay(15);
            this.vely = -10;
            this.velx = 0;
            this.isOnGround = false;
            this.jump();
        }
    }


    public void down() {
        this.vely = 10;
        this.velx = 0;
    }

    public void left() {
        this.vely = 0;
        this.velx = -15;
    }

    public void right() {
        this.vely = 0;
        this.velx = 15;
    }

    public void takeDamage(){
        if (this.lives > 1) {
            lives--;
        } else {
            this.lives = 0;
            this.die();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    private void jump() {
        if (this.jumpAnimationAllowed) {
            this.Img = this.ssJump.grabSprite(this.jumpSpriteOffcet, 0, 82, 85);
            this.jumpSpriteOffcet += 80;
        }
        if (this.jumpSpriteOffcet >= 240) {
            this.jumpSpriteOffcet = 240;
            this.jumpAnimationAllowed = false;
        }
        this.runSpriteOffcet = 5;
    }

    private void run() {
        this.Img = this.ssRun.grabSprite(this.runSpriteOffcet, 0, 79, 85);
        this.runSpriteOffcet += 82;
        if (this.runSpriteOffcet >= 450) {
            this.runSpriteOffcet = 5;
        }
        this.jumpAnimationAllowed = true;
        this.jumpSpriteOffcet = 0;
    }

    private void die() {
        if (this.y != this.relativeY) {
            this.y = this.relativeY;
        }
        this.velx = 0;
        this.Img = this.ssDie.grabSprite(this.dieSpriteOffcet, 0, 95, 85);
        this.dieSpriteOffcet += 98;
        if (this.dieSpriteOffcet >= 450) {
            this.dieSpriteOffcet = 0;
            this.isDead = true;
        }else{
            this.die();
        }
        this.jumpAnimationAllowed = false;
    }

    private void checkNinjaLocation() {

        //If the ninja is at highest point at the jump tell it to fall down.
        if (this.y < this.jumpheight) {
            this.down();
        }
        //IF the ninja is on the ground set timer to go slower and make it run
        else if (this.y == this.relativeY) {
            this.isOnGround = true;
            this.timer.setDelay(100);
            this.vely = 0;
            this.run();
        }
        //If ninja is going for jump play the animation
        else if (this.y > this.jumpheight && this.y < this.relativeY) {
            this.jump();
        }
    }
}