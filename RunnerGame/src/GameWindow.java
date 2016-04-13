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

        //full screen - optional
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setSize(width, height);

        //ScreenSize
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //add panel

        JLayeredPane lpane = new JLayeredPane();
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        Panel p = new Panel();
        p.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight() * 3/4 - 30);
        Ground g = new Ground();
        g.setBounds(0, (int)screenSize.getHeight() * 3/4 - 30, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        lpane.add(g, new Integer(0), 0);
        lpane.add(p, new Integer(1), 0);

        // remove border
        setUndecorated(true);


    }

}


