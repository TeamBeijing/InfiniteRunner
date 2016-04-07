package graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static SpriteSheet player;
    public static void init() {
        background = ImageLoader.LoadImage("/background.jpg");
        player = new SpriteSheet(ImageLoader.LoadImage("/player.png"));
    }
}
