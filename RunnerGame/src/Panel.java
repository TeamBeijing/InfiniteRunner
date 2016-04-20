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

public class Panel extends JPanel implements ActionListener, KeyListener {

    Timer t = new Timer(2, this);
    Ninja n;
    ObstacleDatabase obstacleDB;
    private long tStart = System.currentTimeMillis();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    CollisionDetector checkForCollision;
    int lives = 3;
    Random r = new Random();
    int high = 1400;
    int low = 750;

    public String currentScores;

    public ArrayList<String> allScores = new ArrayList<>();

    public Panel() {

        t.start();
        addKeyListener(this);
        setFocusable(true);
        n = new Ninja();
        obstacleDB = new ObstacleDatabase();
        setFocusTraversalKeysEnabled(false);
        checkForCollision = new CollisionDetector();

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.draw(n.boundingBox); //see the boundingBox - Ninja

        //change the delay for (space between) obstacles
        int delay = r.nextInt(high - low) + low;
        obstacleDB.t.setDelay(delay);

        for (Obstacle obst : obstacleDB.obstacles) {
            g2.drawImage(obst.img, obst.x, obst.y, null);
            //g2.draw(obst.boundingBox);  //see the boundingBox - obstacles
        }
        g2.drawImage(n.Img, n.x, n.y, null);

        //print the score in the top right corner
        g2.setFont(new Font("Consolas", Font.BOLD, 40));
        g2.drawString(
                String.format("%1$10d",
                        (System.currentTimeMillis() - tStart) / 50)
                        .replaceAll(" ", "0"),
                        (int) screenSize.getWidth() - 240, 50);
        currentScores = String.format("%1$10d",
                ((System.currentTimeMillis() - tStart) / 50) + 1)
                .replaceAll(" ", "0");
        //Draw the lives
        g2.setColor(new Color(244, 49, 49));
        g2.setFont(new Font("SansSerif", Font.PLAIN, 40));
        g2.drawString(new String(new char[lives]).replace("\0", "♥"), 20, 50);

    }


    public void actionPerformed(ActionEvent e) {
        if (checkForCollision.CollisionDetector(n, obstacleDB)) {
            if (lives > 1) {
                lives--;
                obstacleDB.obstacles.remove(checkForCollision.GetIndexOfObject(n, obstacleDB));
            } else {
                lives = 0;
                n.die();
                t.setDelay(30);
            }

        }
        if (n.isDead) {
            t.stop();

            try {
                FileReader fr = new FileReader("scores.txt");
                BufferedReader br = new BufferedReader(fr);
                for (int i = 0; i < 5; i++) {
                    allScores.add(br.readLine());
                }
                allScores.add(currentScores);
                allScores.sort(Comparator.reverseOrder());
                allScores.remove(5);
                br.close();
                fr.close();

                FileWriter fileWriter = new FileWriter("scores.txt");
                PrintWriter write = new PrintWriter(fileWriter);
                for (int i = 0; i < 5; i++) {
                write.printf("%s%n", allScores.get(i));
                }
                fileWriter.close();
                write.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            GameOver go = new GameOver();
            go.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
            this.getParent().getParent().getParent().add(go, new Integer(2), 0);
        }

        repaint();

    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
           if (n.isOnGround)
            n.left();
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (n.isOnGround)
            n.right();
        }

        if (key == KeyEvent.VK_UP) {
            n.up();
        }

        if (key == KeyEvent.VK_DOWN) {
            n.down();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
            n.velx = 0;
        }
    }
}