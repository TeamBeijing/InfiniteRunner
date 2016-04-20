package frames;

import UI.CustomizedButtonUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

public class MenuFrame extends JFrame implements ActionListener {

    private Font customFont;
    private Font customFont2;
    private JButton start;
    private JButton score;
    private JButton credits;
    private JButton exit;
    private Image backgroundIMG;

    public MenuFrame(String title) {
        super(title);
        setSize(640, 423);
        setLocationRelativeTo(null);
        setUndecorated(true);
        //getContentPane().setBackground(new Color(60, 60, 60));
        //setShape(new Ellipse2D.Double(0, 0, getWidth(), getHeight())); //making the window circle
        //setResizable(false);
        //setLayout(null);
        //pack();
        try {
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/textures/ka1.ttf")).deriveFont(14f);
            GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gEnvironment.registerFont(this.customFont);
            this.backgroundIMG = ImageIO.read(new File("src/textures/Menubackground4.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (FontFormatException e) {
            System.out.println(e.getMessage());
        }

        Border emptyBorder = BorderFactory.createEmptyBorder();

        JLabel gameTitle = new JLabel("Ninja Runner");
        gameTitle.setBounds(0, 0, 245, 40);
        //Border border = BorderFactory.createLineBorder(Color.BLACK, 1); - see border
        //gameTitle.setBorder(border);
        gameTitle.setFont(new Font(this.customFont.getName(), Font.ITALIC, 25));
        gameTitle.setForeground(Color.white);
        gameTitle.setLocation(640/2 - 120, 10);
        add(gameTitle);

        this.start = new JButton("Start");
        this.start.addActionListener(this);
        this.start.setBorder(emptyBorder);
        this.start.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(this.customFont.getName(), Font.PLAIN, 17), Color.white));
        this.start.setBounds(70, 142, 160, 40);
        add(this.start);

        this.score = new JButton("Scores");
        this.score.addActionListener(this);
        this.score.setBorder(emptyBorder);
        this.score.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(this.customFont.getName(), Font.PLAIN, 17), Color.white));
        this.score.setBounds(70, 194, 160, 40);
        add(this.score);

        this.credits = new JButton("Credits");
        this.credits.addActionListener(this);
        this.credits.setBorder(emptyBorder);
        this.credits.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(this.customFont.getName(), Font.PLAIN, 17), Color.white));
        this.credits.setBounds(70, 246, 160, 40);
        add(this.credits);

        this.exit = new JButton("Exit");
        this.exit.addActionListener(this);
        this.exit.setBorder(emptyBorder);
        this.exit.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(this.customFont.getName(), Font.PLAIN, 17), Color.white));
        this.exit.setBounds(70, 298, 160, 40);
        add(this.exit);

        //Layer
        JLabel background = new JLabel(new ImageIcon(this.backgroundIMG));
        background.setBounds(28, getHeight() - 438, 400, 438);
        add(background);

    }

    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == this.exit) {
            System.exit(0);
        }
        else if (action.getSource() == this.start) {
            //start a new game
            GameWindow gWindow = new GameWindow("Runner");
            gWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gWindow.setVisible(true);
        }
        else if (action.getSource() == credits){
            CreditsWindow cWindow = new CreditsWindow("Credits");
            cWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            cWindow.setVisible(true);
            //here we can add a window with info about the game
            // and the contributors e.g. This was created as part of the SoftUni program bla bla.
        }
        else if (action.getSource() == score){
            ScoreWindow sWindow = new ScoreWindow("Scores");
            sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sWindow.setVisible(true);
        }
    }

}

