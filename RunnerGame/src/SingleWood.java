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
    int width = 35, height = 120;

    public SingleWood() {
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheetCutMachine = null;
        int Ys[] = new int[]{y + 80, y, y + 80, y + height, y + height};
        int Xs[] = new int[]{x, x + width / 2, x + width, x + width, x};
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