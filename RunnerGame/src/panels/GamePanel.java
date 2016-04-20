package panels;

import collision.CollisionDetector;
import database.ObstacleDatabase;
import models.Ninja;
import models.Obstacle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private int high = 1400;
    private int low = 750;
    private Timer timer;
    private Ninja ninja;
    private ObstacleDatabase obstacleDB;
    private long tStart;
    private Dimension screenSize;
    private CollisionDetector checkForCollision;
    private Random r = new Random();
    private String currentScores;
    private ArrayList<String> allScores = new ArrayList<>();

    public GamePanel() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        addKeyListener(this);
        setFocusable(true);
        this.ninja = new Ninja();
        this.obstacleDB = new ObstacleDatabase();
        setFocusTraversalKeysEnabled(false);
        this.checkForCollision = new CollisionDetector();
        this.timer = new Timer(2, this);
        this.timer.start();
        this.tStart = System.currentTimeMillis();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.draw(n.boundingBox); //see the boundingBox - Ninja

        //change the delay for (space between) obstacles
        int delay = r.nextInt(this.high - this.low) + this.low;
        this.obstacleDB.getTimer().setDelay(delay);

        for (Obstacle obst : this.obstacleDB.obstacles) {
            g2.drawImage(obst.getImg(), obst.getX(), obst.getY(), null);
            //g2.draw(obst.boundingBox);  //see the boundingBox - obstacles
        }
        g2.drawImage(this.ninja.getImg(), this.ninja.getX(), this.ninja.getY(), null);

        //print the score in the top right corner
        g2.setFont(new Font("Consolas", Font.BOLD, 40));
        g2.drawString(
                String.format("%1$10d",
                        (System.currentTimeMillis() - this.tStart) / 50)
                        .replaceAll(" ", "0"),
                        (int) this.screenSize.getWidth() - 240, 50);
        this.currentScores = String.format("%1$10d",
                ((System.currentTimeMillis() - this.tStart) / 50) + 1)
                .replaceAll(" ", "0");
        //Draw the lives
        g2.setColor(new Color(244, 49, 49));
        g2.setFont(new Font("SansSerif", Font.PLAIN, 40));
        g2.drawString(new String(new char[this.ninja.getLives()]).replace("\0", "♥"), 20, 50);
    }

    public void actionPerformed(ActionEvent e) {
        if (this.checkForCollision.CollisionDetector(this.ninja, this.obstacleDB)) {
            this.ninja.takeDamage();
            this.obstacleDB.obstacles.remove(this.checkForCollision.GetIndexOfObject(this.ninja, this.obstacleDB));
        }
        if (this.ninja.getIsDead()) {
            this.timer.stop();
            try {
                FileReader fReader = new FileReader("src/files/scores.txt");
                BufferedReader bReader = new BufferedReader(fReader);
                for (int i = 0; i < 5; i++) {
                    this.allScores.add(bReader.readLine());
                }
                this.allScores.add(this.currentScores);
                this.allScores.sort(Comparator.reverseOrder());
                this.allScores.remove(5);
                bReader.close();
                fReader.close();

                FileWriter fileWriter = new FileWriter("src/files/scores.txt");
                PrintWriter write = new PrintWriter(fileWriter);
                for (int i = 0; i < 5; i++) {
                write.printf("%s%n", this.allScores.get(i));
                }
                fileWriter.close();
                write.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            GameOver gameOver = new GameOver();
            gameOver.setBounds(0, 0, (int) this.screenSize.getWidth(), (int) this.screenSize.getHeight());
            this.getParent().getParent().getParent().add(gameOver, new Integer(2), 0);
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
           if (this.ninja.getIsOnGround())
            this.ninja.left();
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (this.ninja.getIsOnGround())
            this.ninja.right();
        }

        if (key == KeyEvent.VK_UP) {
            this.ninja.up();
        }

        if (key == KeyEvent.VK_DOWN) {
            this.ninja.down();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
            this.ninja.setVelX(0);
        }
    }
}