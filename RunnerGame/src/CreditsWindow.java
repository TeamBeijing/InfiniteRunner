import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreditsWindow extends JFrame {

    private int width = 640;
    private int height = 500;

    public CreditsWindow(String title) {
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
        CreditsPanel cp = new CreditsPanel();
        cp.setBounds(0, 0, width, height);
        add(cp);
        lpane.add(cp, new Integer(0), 0);
    }

/////+
    public static class CreditsPanel extends JPanel implements ActionListener {

        Image softUniLogo;
        Image maleAvatar;
        Image femaleAvatar;
        Image[] allImages;
        Font customFont;
        Font customFont2;
        private JButton back;

        public CreditsPanel() {
            setOpaque(false);
            setLayout(null);

            try {
                customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/textures/ka1.ttf")).deriveFont(14f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
                customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/textures/Lato-Black.ttf")).deriveFont(14f);
                ge.registerFont(customFont2);
                softUniLogo = ImageIO.read(new File("src/textures/softuni.png")).getScaledInstance(100, 70, Image.SCALE_SMOOTH);
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

            JLabel team = new JLabel("Team Beijing", JLabel.CENTER);
            team.setFont(new Font(customFont.getFontName(), Font.PLAIN, 30));
            team.setBounds(0, 0, 640 / 2, 40);
            team.setLocation(640 / 2 - 640 / 4, 102);
            add(team);

            ArrayList<JLabel> lables = new ArrayList<>();
            lables.add(new JLabel("Konstantin", JLabel.CENTER));
            lables.add(new JLabel("Valentin", JLabel.CENTER));
            lables.add(new JLabel("Milena", JLabel.CENTER));
            lables.add(new JLabel("Spas", JLabel.CENTER));
            lables.add(new JLabel("Yasen", JLabel.CENTER));
            lables.add(new JLabel("Kurtev", JLabel.CENTER));
            lables.add(new JLabel("Mladenov", JLabel.CENTER));
            lables.add(new JLabel("Sapunova", JLabel.CENTER));
            lables.add(new JLabel("Spasov", JLabel.CENTER));
            lables.add(new JLabel("Vasilev", JLabel.CENTER));

            for (int i = 0; i < lables.size(); i++) {
                JLabel jl = lables.get(i);
                int X = 25 + (595 / 5) * i;
                int Y = 282;
                if (i > 4) {
                    X = 25 + (595 / 5) * (i - 5);
                    Y += 19;
                }
                jl.setFont(new Font(customFont2.getName(), Font.PLAIN, 16));
                jl.setBounds(0, 0, 595 / 5, 22);
                jl.setLocation(X, Y);
                jl.setForeground(new Color(35, 68, 101));
                add(jl);
            }

            ArrayList<JLabel> textLines = new ArrayList<>();

            textLines.add(new JLabel("This game was created as part of a team project for the", JLabel.CENTER));
            textLines.add(new JLabel("Java Fundamentals course (March 2016) at Software University.", JLabel.CENTER));
            for (int i = 0; i < textLines.size(); i++) {
                JLabel text = textLines.get(i);
                text.setBounds(0, 0, 640, 40);
                text.setFont(new Font(customFont2.getName(), Font.PLAIN, 17));
                text.setForeground(Color.darkGray);
                text.setLocation(0, 350 + 20 * i);
                add(text);
            }

            back = new JButton("Back");
            back.addActionListener(this);
            back.setBorder(BorderFactory.createEmptyBorder());
            back.setUI(new CustomizedButtonUI(new Color(35, 68, 101),
                    new Color(255, 160, 0), new Color(100, 100, 100),
                    new Font(customFont.getName(), Font.PLAIN, 17), Color.white));
            back.setBounds(0, 0, 120, 40);
            back.setLocation(640 / 2 - 120 / 2, 440);
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
            g2.drawImage(softUniLogo, (getWidth() - softUniLogo.getWidth(this)) / 2, 15, this);

            for (int i = 0; i < 5; i++) {
                g2.setColor(new Color(255, 160, 0));
                Ellipse2D circle = new Ellipse2D.Float(27 + 118 * i, 172, 115, 110);
                g2.fill(circle);
                g2.drawImage(allImages[i], 50 + 118 * i, 190, this);
            }

        }

    }

}