package gpxUtilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoader {

    public BufferedImage loadImage(String pathRelativeToThis) throws IOException{
        BufferedImage img = ImageIO.read(new File(pathRelativeToThis));
        return  img;
    }
}