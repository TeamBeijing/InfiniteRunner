package panels;

import UI.CustomizedButtonUI;
import UI.PlaceholderTextField;
import frames.ScoreWindow;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameOver extends JPanel implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static PlaceholderTextField usernameTextArea;
    JButton ok;
    Font customFont;
    List<String> allNames = new ArrayList<>();
    int index;

    public GameOver(boolean showUserInput, int index) {
        this.setOpaque(false);
        this.setVisible(true);
        this.setLayout((LayoutManager) null);
        this.index = index;

        if (showUserInput) {
            usernameTextArea = new PlaceholderTextField();
            usernameTextArea.setBounds(0, 0, 260, 35);
            usernameTextArea.setFont(new Font("Consolas", 1, 30));
            usernameTextArea.setLocation((int) this.screenSize.getWidth() / 2 - usernameTextArea.getWidth() / 2, (int) this.screenSize.getHeight() / 2 - usernameTextArea.getHeight() / 2 + 40);
            usernameTextArea.setBackground(new Color(150, 150, 150));
            usernameTextArea.setForeground(Color.white);
            usernameTextArea.setBorder(BorderFactory.createEmptyBorder());
            usernameTextArea.setPlaceholder("Enter your name");
            usernameTextArea.setHorizontalAlignment(0);
            usernameTextArea.setAlignmentY(0.0F);
            this.add(usernameTextArea);

            try {
                this.customFont = Font.createFont(0, new File("src/textures/ka1.ttf")).deriveFont(14.0F);
                GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
                e.registerFont(this.customFont);
            } catch (IOException var3) {
                System.out.println(var3.getMessage());
            } catch (FontFormatException var4) {
                System.out.println(var4.getMessage());
            }

            this.ok = new JButton("OK");
            this.ok.addActionListener(this);
            this.ok.setBorder(BorderFactory.createEmptyBorder());
            this.ok.setUI(new CustomizedButtonUI(new Color(20, 20, 20), new Color(100, 100, 100), new Color(150, 150, 150), new Font(this.customFont.getName(), 0, 17), Color.white));
            this.ok.setBounds(0, 0, 100, 40);
            this.ok.setLocation((int) this.screenSize.getWidth() / 2 - this.ok.getWidth() / 2, (int) this.screenSize.getHeight() / 2 - this.ok.getHeight() / 2 + 100);
            this.add(this.ok);
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Composite old = g2d.getComposite();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5F));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(old);
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", 1, 80));
        g.drawString("GAME OVER", (int) this.screenSize.getWidth() / 2 - 220, (int) this.screenSize.getHeight() / 2 - 40);
    }

    public void actionPerformed(ActionEvent action) {
        String name = usernameTextArea.getText();
        if ("".equals(name)){
            name = "No name";
        }

        try {
            FileReader fReader = new FileReader("src/files/names.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            for (int i = 0; i < 5; i++) {
                this.allNames.add(bReader.readLine());
            }
            this.allNames.add(index, name);
            bReader.close();
            fReader.close();

            FileWriter fileWriter = new FileWriter("src/files/names.txt");
            PrintWriter write = new PrintWriter(fileWriter);
            for (int i = 0; i < 5; i++) {
                write.printf("%s%n", this.allNames.get(i));
            }
            fileWriter.close();
            write.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        SwingUtilities.getWindowAncestor(this).dispose();
        ScoreWindow sWindow = new ScoreWindow("Scores");
        sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sWindow.setVisible(true);
    }
}
