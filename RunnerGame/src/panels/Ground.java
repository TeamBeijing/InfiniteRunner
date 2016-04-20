package panels;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Ground extends JPanel {

    private Image backgroundIMG;
    private Dimension screenSize;
    private int relativeY;
    private int width;

    public Ground() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (int) this.screenSize.getWidth();
        this.relativeY = (int) screenSize.getHeight() * 3 / 4;
        try {
            this.backgroundIMG = ImageIO.read(new File("src/textures/background.png"))
                    .getScaledInstance((int) this.screenSize.getWidth(), (int) this.screenSize.getHeight() / 4, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundIMG, 0 , 30, this);
        g.setColor(Color.black);
        g.fillRect(0, 0, this.width, 10);
    }
}