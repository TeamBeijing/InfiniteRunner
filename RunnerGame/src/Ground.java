import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Ground extends JPanel {

    private Image backgroundIMG;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int relativeY = (int) screenSize.getHeight() * 3 / 4;
    int width = (int) screenSize.getWidth();

    public Ground() {

        try {
            backgroundIMG = ImageIO.read(new File("src/textures/background.png"))
                    .getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight() / 4, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundIMG, 0 , 30, this);
        g.setColor(Color.black);
        g.fillRect(0, 0, width, 10);
    }
}