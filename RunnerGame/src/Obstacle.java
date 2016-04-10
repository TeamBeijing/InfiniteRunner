import gpxUtilities.BufferedImageLoader;
import gpxUtilities.SpriteSheet;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Obstacle implements ActionListener {
    public BufferedImage Img;
    SpriteSheet ssCut;
    Timer t = new Timer(20, this);
    int velx = 0, vely = 0;
    int x = 350, y = 200;
    int imageOffcet = 0;

    public Obstacle() {
        t.start();
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetCutMachine = null;
        try{
            spriteSheetCutMachine = loader.loadImage("src/textures/cutMachine.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ssCut = new SpriteSheet(spriteSheetCutMachine);

    }


    public void actionPerformed(ActionEvent e) {
            x += velx;
            y += vely;
            left();
            cut();
    }


    public void left() {
        vely = 0;
        velx = -10;
    }

    public void cut() {
        this.Img = ssCut.grabSprite(imageOffcet, 0, 110, 85);
        imageOffcet += 116;
        if(imageOffcet > 450){
            imageOffcet = 2;
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
