package frames;

import UI.CustomizedButtonUI;

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


public class ScoreWindow extends JFrame {
    public int width = 640;
    public int height = 600;

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

        Image ninjaLogo;
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
                ninjaLogo = ImageIO.read(new File("src/textures/NinjaLogo.png")).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                maleAvatar = ImageIO.read(new File("src/textures/ninja2.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                allImages = new Image[5];
                for (int i = 0; i < 5; i++) {
                    Image img = maleAvatar;
                    allImages[i] = img;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (FontFormatException f) {
                f.printStackTrace();
            }

            JLabel team = new JLabel("Ninja Runner Scores", JLabel.CENTER);
            team.setFont(new Font(customFont.getFontName(), Font.PLAIN, 30));
            team.setBounds(0, 0, 500, 40);
            team.setLocation(640 / 2 - team.getWidth() / 2, 105);
            add(team);

            ArrayList<String> allScores = new ArrayList<>();
            ArrayList<String> allNames = new ArrayList<>();

            try {
                FileReader fr = new FileReader("src/files/scores.txt");
                BufferedReader br = new BufferedReader(fr);
                FileReader frNames = new FileReader("src/files/names.txt");
                BufferedReader brNames = new BufferedReader(frNames);
                for (int i = 0; i < 5; i++) {
                    allScores.add(br.readLine());
                }

                for (int i = 0; i < 5; i++) {
                    allNames.add(brNames.readLine());
                }

                frNames.close();
                brNames.close();
                br.close();
                fr.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            ArrayList<JLabel> lables = new ArrayList<>();
            lables.add(new JLabel(allNames.get(0), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(1), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(2), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(3), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(4), JLabel.LEFT));

            lables.add(new JLabel(allScores.get(0), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(1), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(2), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(3), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(4), JLabel.LEFT));


            for (int i = 0; i < lables.size(); i++) {
                JLabel jl = lables.get(i);
                int X = 140;
                int Y = (195 + i*70);
                jl.setFont(new Font(customFont.getName(), Font.PLAIN, 22));
                jl.setForeground(new Color(50, 50, 50));
                if (i > 4) {
                    X = 430;
                    Y = (195 + (i - 5) * 70);
                    jl.setFont(new Font("Consolas", Font.BOLD, 24));
                    jl.setForeground(new Color(70, 70, 70));
                }


                jl.setBounds(0, 0, 400, 22);
                jl.setLocation(X, Y);

                add(jl);
            }


            back = new JButton("Back");
            back.addActionListener(this);
            back.setBorder(BorderFactory.createEmptyBorder());
            back.setUI(new CustomizedButtonUI(new Color(20, 20, 20),
                    new Color(100, 100, 100), new Color(150, 150, 150),
                    new Font(customFont.getName(), Font.PLAIN, 17), Color.white));
            back.setBounds(0, 0, 120, 40);
            back.setLocation(640/2 - 120/2, 550);
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
            g2.drawImage(ninjaLogo, (getWidth() - ninjaLogo.getWidth(this)) / 2, 15, this);

            for (int i = 0; i < 5; i++) {
                g2.drawImage(allImages[i], 60, (180 + i*70), 50, 50, this);
            }

        }

    }
}
