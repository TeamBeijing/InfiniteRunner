package frames;

import panels.GamePanel;
import panels.Ground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



<<<<<<< HEAD:RunnerGame/src/frames/GameWindow.java
public class GameWindow extends JFrame {

    public GameWindow(String title) {
=======
    Color backgroundColor = new Color(230, 230, 230);
    public GameWindow(String title, int width, int height) {
>>>>>>> origin/master:RunnerGame/src/GameWindow.java
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

<<<<<<< HEAD:RunnerGame/src/frames/GameWindow.java
        GamePanel p = new GamePanel();
=======

        Panel p = new Panel();
        p.setBackground(backgroundColor);
>>>>>>> origin/master:RunnerGame/src/GameWindow.java
        p.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight() * 3/4 - 30);
        Ground g = new Ground();
        g.setBackground(backgroundColor);
        g.setBounds(0, (int)screenSize.getHeight() * 3/4 - 30, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        lpane.add(g, new Integer(0), 0);
        lpane.add(p, new Integer(1), 0);

        // remove border
        setUndecorated(true);


    }

}


