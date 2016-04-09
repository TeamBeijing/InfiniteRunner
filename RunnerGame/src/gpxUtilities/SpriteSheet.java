package gpxUtilities;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    public BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage bI) {
        this.spriteSheet = bI;
    }

    public BufferedImage grabSprite(int x, int y, int width, int height){
        BufferedImage sprite = this.spriteSheet.getSubimage(x, y, width, height);
        return sprite;
    }
}