import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ScoreWindow extends JFrame{
    private int width = 600;
    private int height = 700;

    public ScoreWindow(String title) {
        super(title);
        setSize(width, height);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);

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

        JLayeredPane lpane = new JLayeredPane();
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, width, height);
        ScorePanel cp = new ScorePanel();
        cp.setBounds(0, 0, width, height);
        add(cp);
        lpane.add(cp, new Integer(0), 0);
    }
    public static class ScorePanel extends JPanel implements ActionListener {


        Image runnerLogo;
        Image maleAvatar;
        Image femaleAvatar;
        Image[] allImages;
        Font customFont;
        Font customFont2;
        private JButton back;

        public ScorePanel() {
            setOpaque(false);
            setLayout(null);



            try {
                customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/textures/ka1.ttf")).deriveFont(14f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
                customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/textures/Lato-Black.ttf")).deriveFont(14f);
                ge.registerFont(customFont2);
                runnerLogo = ImageIO.read(new File("src/textures/runnerLogo.png")).getScaledInstance(100, 70, Image.SCALE_SMOOTH);
                maleAvatar = ImageIO.read(new File("src/textures/maleAvatar.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                femaleAvatar = ImageIO.read(new File("src/textures/femaleAvatar.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                allImages = new Image[5];
                for (int i = 0; i < 5; i++) {
                    Image img = maleAvatar;
                    if (i == 2) {
                        img = femaleAvatar;
                    }
                    allImages[i] = img;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (FontFormatException f) {
                f.printStackTrace();
            }

            JLabel team = new JLabel("Infinity Runner Scores", JLabel.CENTER);
            team.setFont(new Font(customFont.getFontName(), Font.PLAIN, 30));
            team.setBounds(0, 0, 1000 / 2, 40);
            team.setLocation(395 / 2 - 600 / 4, 102);
            add(team);

            ArrayList<String> allScores = new ArrayList<>();
            try {
                FileReader fr = new FileReader("scores.txt");
                BufferedReader br = new BufferedReader(fr);
                for (int i = 0; i < 5; i++) {
                    allScores.add(br.readLine());
                }
                br.close();
                fr.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            ArrayList<JLabel> lables = new ArrayList<>();
            lables.add(new JLabel("BEST SCORE:", JLabel.RIGHT));
            lables.add(new JLabel("BETER SCORE:", JLabel.RIGHT));
            lables.add(new JLabel("GOOD SCORE:", JLabel.RIGHT));
            lables.add(new JLabel("BAD SCORE:", JLabel.RIGHT));
            lables.add(new JLabel("WORST SCORE:", JLabel.RIGHT));

            lables.add(new JLabel(allScores.get(0), JLabel.RIGHT));
            lables.add(new JLabel(allScores.get(1), JLabel.RIGHT));
            lables.add(new JLabel(allScores.get(2), JLabel.RIGHT));
            lables.add(new JLabel(allScores.get(3), JLabel.RIGHT));
            lables.add(new JLabel(allScores.get(4), JLabel.RIGHT));



            for (int i = 0; i < lables.size(); i++) {
                JLabel jl = lables.get(i);
                int X = 160;
                int Y = (200 * (i + 1)) - (95 * i);
                if (i > 4) {
                    X = 260;
                    Y = (200 * (i - 4)) - (95 * (i - 5));
                }

                jl.setFont(new Font(customFont2.getName(), Font.PLAIN, 16));
                jl.setBounds(0, 0, 125, 22);
                jl.setLocation(X, Y);
                jl.setForeground(new Color(35, 68, 101));
                add(jl);
            }



            back = new JButton("Back");
            back.addActionListener(this);
            back.setBorder(BorderFactory.createEmptyBorder());
            back.setUI(new CustomizedButtonUI(new Color(35, 68, 101),
                    new Color(255, 160, 0), new Color(100, 100, 100),
                    new Font(customFont.getName(), Font.PLAIN, 17), Color.white));
            back.setBounds(0, 0, 120, 40);
            back.setLocation(470, 650);
            add(back);

        }

        public void actionPerformed(ActionEvent action) {
            if (action.getSource() == back) {
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(runnerLogo, (getWidth() - runnerLogo.getWidth(this)) / 2, 15, this);

            for (int i = 0; i < 5; i++) {
                g2.setColor(new Color(255, 160, 0));
                Ellipse2D circle = new Ellipse2D.Float(49, (160 * (i + 1)) - (55 * i), 107, 102);
                g2.fill(circle);
                g2.drawImage(allImages[i], 70, (170 * (i + 1)) - (65 * i), 70, 70, this);
            }

        }

    }
}
