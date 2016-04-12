import gpxUtilities.BufferedImageLoader;
import gpxUtilities.SpriteSheet;
import sun.applet.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Razor extends Obstacle {

    int imageOffcet = 2;
    int width = 110, height = 120;

    public Razor() {
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetCutMachine = null;
        boundingBox = new Rectangle(x, y, width, height);

        try {
            spriteSheetCutMachine = loader.loadImage("src/textures/cutMachine.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ss = new SpriteSheet(spriteSheetCutMachine);
    }

    public void PlayAnimation() {
        this.img = ss.grabSprite(imageOffcet, 0, width, height);
        imageOffcet += 116;
        if (imageOffcet > 450) {
            imageOffcet = 2;
        }
    }
}
