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
    private JButton start;
    private JButton score;
    private JButton credits;
    private JButton exit;
    private Image backgroundIMG;


    public MenuFrame(String title) {
        super(title);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setUndecorated(true);
        getContentPane().setBackground(new Color(60, 60, 60));
        setShape(new Ellipse2D.Double(0, 0, getWidth(), getHeight())); //making the window circle
        setResizable(false);
        setLayout(null);
        //pack();

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/textures/ka1.ttf")).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            backgroundIMG = ImageIO.read(new File("src/textures/Menubackground3.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (FontFormatException e) {
            System.out.println(e.getMessage());
        }

        Border emptyBorder = BorderFactory.createEmptyBorder();

        start = new JButton("Start");
        start.addActionListener(this);
        start.setBorder(emptyBorder);
        start.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(customFont.getName(), Font.PLAIN, 17), Color.white));
        start.setBounds(148, 90, 160, 40);
        add(start);

        score = new JButton("Scores");
        score.addActionListener(this);
        score.setBorder(emptyBorder);
        score.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(customFont.getName(), Font.PLAIN, 17), Color.white));
        score.setBounds(148, 142, 160, 40);
        add(score);

        credits = new JButton("Credits");
        credits.addActionListener(this);
        credits.setBorder(emptyBorder);
        credits.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(customFont.getName(), Font.PLAIN, 17), Color.white));
        credits.setBounds(148, 194, 160, 40);
        add(credits);

        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setBorder(emptyBorder);
        exit.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                new Color(100, 100, 100), new Color(150, 150, 150),
                new Font(customFont.getName(), Font.PLAIN, 17), Color.white));
        exit.setBounds(148, 246, 160, 40);
        add(exit);

        //Layer
        JLabel background = new JLabel(new ImageIcon(backgroundIMG));
        background.setBounds(28, getHeight() - 438, 400, 438);
        add(background);

    }

    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == exit) {
            System.exit(0);
        }
        else if (action.getSource() == start) {
            //start a new game
            GameWindow f = new GameWindow("Runner", 600, 400);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        }
        else if (action.getSource() == credits){
            CreditsWindow c = new CreditsWindow("Credits");
            c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            c.setVisible(true);
            //here we can add a window with info about the game
            // and the contributors e.g. This was created as part of the SoftUni program bla bla.
        }
        else if (action.getSource() == score){
            //display window with score table
        }
    }

}

