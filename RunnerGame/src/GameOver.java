import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameOver extends JPanel {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GameOver() {

        setOpaque(false);
        setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // This is faking transparency, so the background color
        // will be see through
        Graphics2D g2d = (Graphics2D) g.create();
        Composite old = g2d.getComposite();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setComposite(old);

        g.setColor(Color.black);
        g.setFont(new Font("Consolas", Font.BOLD, 80));
        g.drawString("GAME OVER", (int) screenSize.getWidth() / 2 - 220, (int) screenSize.getHeight() / 2 - 40);

    }
}

