package models;

import gpxUtilities.BufferedImageLoader;
import gpxUtilities.SpriteSheet;
import models.Obstacle;
import sun.applet.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleWood extends Obstacle {

    private int imageOffcet = 0;
    private int width = 87, height = 120;

    public SingleWood() {
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetCutMachine = null;
        int Ys[] = new int[]{super.y + 120, super.y + 107, super.y + 104, super.y, super.y + 100, super.y + 102, super.y + 120};
        int Xs[] = new int[]{super.x, super.x, super.x + 12, super.x + 41, super.x + 71, super.x + 81, super.x + 87};
        super.boundingBox = new Polygon(Xs, Ys, Xs.length);
        try {
            spriteSheetCutMachine = loader.loadImage("src/textures/singleWood.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.ss = new SpriteSheet(spriteSheetCutMachine);
    }

    public void PlayAnimation() {
        super.img = ss.grabSprite(this.imageOffcet, 0, this.width, this.height);
    }
}