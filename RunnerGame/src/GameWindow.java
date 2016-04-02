import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class GameWindow extends JFrame {

    private Image backgroundIMG;

    public GameWindow(String title, int width, int height) {
        setTitle(title);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 255, 255, 50));
        setLayout(null);

        //Escape should close the window
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");
        am.put("cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //ScreenSize
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //add player
        Player p = new Player();
        p.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() * 3 / 4);
        add(p);

        //full screen - optional
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setSize(width, height);

        // remove border
        setUndecorated(true);

        //background
        try {
            backgroundIMG = ImageIO.read(new File("src/textures/background.png"))
                    .getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight() / 4, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        JLabel background = new JLabel(new ImageIcon(backgroundIMG));
        background.setBounds(0, (int) screenSize.getHeight() * 3 / 4, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 4);
        add(background);


    }

}


