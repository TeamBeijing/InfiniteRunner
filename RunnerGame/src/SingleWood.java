import gpxUtilities.BufferedImageLoader;
import gpxUtilities.SpriteSheet;
import sun.applet.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleWood extends Obstacle {

    int imageOffcet = 0;
    int width = 87, height = 120;

    public SingleWood() {
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetCutMachine = null;
        int Ys[] = new int[]{y + 120, y + 107, y + 104, y, y + 100, y + 102, y + 120};
        int Xs[] = new int[]{x, x, x + 12, x + 41, x + 71, x + 81, x + 87};
        boundingBox = new Polygon(Xs, Ys, Xs.length);

        try {
            spriteSheetCutMachine = loader.loadImage("src/textures/singleWood.png");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ss = new SpriteSheet(spriteSheetCutMachine);
    }

    public void PlayAnimation() {
        this.img = ss.grabSprite(imageOffcet, 0, width, height);
    }
}