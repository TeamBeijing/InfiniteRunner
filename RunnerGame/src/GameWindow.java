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

        // Obstacle o = new Obstacle();
        // o.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() * 3 / 4);
       // add(o);
        //full screen - optional
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setSize(width, height);

        //ScreenSize
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //add panel
        //this.setContentPane(new Ground());
        Panel p = new Panel();
        p.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight() * 3/4 - 30);
        Ground g = new Ground();
        g.setBounds(0, (int)screenSize.getHeight() * 3/4 - 30, (int) screenSize.getWidth(), (int) screenSize.getHeight());
        add(p);
        add(g);

        // remove border
        setUndecorated(true);


    }

}


