package models;

import gpxUtilities.BufferedImageLoader;
import gpxUtilities.SpriteSheet;
import models.Obstacle;
import sun.applet.Main;

import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Razor extends Obstacle {

    private int imageOffcet = 2;
    private int width = 110, height = 120;

    public Razor() {
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetCutMachine = null;
        super.boundingBox = new Ellipse2D.Float(super.x, super.y + 2, this.width, this.width);
        try {
            spriteSheetCutMachine = loader.loadImage("src/textures/cutMachine.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.ss = new SpriteSheet(spriteSheetCutMachine);
    }

    public void PlayAnimation() {
        super.img = ss.grabSprite(this.imageOffcet, 0, this.width, this.height);
        this.imageOffcet += 116;
        if (this.imageOffcet > 450) {
            this.imageOffcet = 2;
        }
    }
}
