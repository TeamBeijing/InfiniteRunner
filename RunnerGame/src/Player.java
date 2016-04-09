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

public class Player extends JPanel implements ActionListener, KeyListener {

    private BufferedImage Img;
    SpriteSheet ssRun, ssJump;
    Timer t = new Timer(100, this);
    int velx = 0, vely = 0;
    int x = 150, y = 200;
    int runSpriteOffcet = 5, jumpSpriteOffcet = 0;
    boolean isOnGround = true, jumpAnimationAloud = true;

    public Player() {

        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetRun = null;
        BufferedImage spriteSheetJump = null;
        try{
            spriteSheetRun = loader.loadImage("src/textures/ninjaRun.png");
            spriteSheetJump = loader.loadImage("src/textures/ninjaJump.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ssRun = new SpriteSheet(spriteSheetRun);
        ssJump = new SpriteSheet(spriteSheetJump);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Img, x, y, null);
        //This method check if the ninja is on ground or in the air.
        checkNinjaLocation();
    }


    public void actionPerformed(ActionEvent e) {
        repaint();
        x += velx;
        y += vely;
    }

    public void up() {
        if(isOnGround) {
            t.setDelay(25);
            vely = -10;
            velx = 0;
            isOnGround = false;
            jump();
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

    private void jump() {
        if(jumpAnimationAloud) {
            this.Img = ssJump.grabSprite(jumpSpriteOffcet, 0, 82, 85);
            jumpSpriteOffcet += 80;
        }
        if(jumpSpriteOffcet >= 240){
            jumpSpriteOffcet = 240;
            jumpAnimationAloud = false;
        }
        runSpriteOffcet = 5;
    }

    private void run(){
        this.Img = ssRun.grabSprite(runSpriteOffcet, 0, 79, 85);
        runSpriteOffcet += 80 + 2;
        if(runSpriteOffcet >= 450){
            runSpriteOffcet = 5;
        }
        jumpAnimationAloud = true;
        jumpSpriteOffcet = 0;
    }

    private void checkNinjaLocation() {
        //If the ninja is at highest point at the jump tell it to fall down.
        if(y < 25)
        {
            down();
        }
        //IF the ninja is on the ground set timer to go slower and make it run
        if(y == 200) {
            isOnGround = true;
            t.setDelay(100);
            vely = 0;
            run();
        }
        //If ninja is going for jump play the animation
        if(y > 25 && y < 200) {
            jump();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}