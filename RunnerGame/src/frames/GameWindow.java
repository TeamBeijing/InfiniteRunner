package frames;

import panels.GamePanel;
import panels.Ground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class GameWindow extends JFrame {

    private Dimension screenSize;
    private JLayeredPane lpane;
    private GamePanel gPanel;
    private Ground ground;

    public GameWindow(String title) {
        Color backgroundColor = new Color(230, 230, 230);
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
         this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //add panel
        this.lpane = new JLayeredPane();
        setLayout(new BorderLayout());
        add(this.lpane, BorderLayout.CENTER);
        this.lpane.setBounds(0, 0, (int) this.screenSize.getWidth(), (int) this.screenSize.getHeight());

        this.gPanel= new GamePanel();
        this.gPanel.setBackground(backgroundColor);
        this.gPanel.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight() * 3/4 - 30);
        this.ground = new Ground();
        this.ground .setBackground(backgroundColor);
        this.ground .setBounds(0, (int)screenSize.getHeight() * 3/4 - 30, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        lpane.add(this.ground , new Integer(0), 0);
        lpane.add(this.gPanel, new Integer(1), 0);

        // remove border
        setUndecorated(true);


    }
}


