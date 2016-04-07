package graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }
    public  BufferedImage crop(int x, int y, int width, int height) {
        return  this.image.getSubimage(x, y, width, height);
    }
}
