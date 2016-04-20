
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObstacleDatabase implements ActionListener {
    private Obstacle obst;
    Random r;
    Timer t = new Timer(1200, this);

    public ObstacleDatabase(){

        r = new Random();
        t.start();
        obstacles = new ArrayList<Obstacle>();
    }

    public ArrayList<Obstacle> obstacles;

    public void actionPerformed(ActionEvent e) {
        obst = new Razor();
        addObstacles();
        removeObstacles();
    }

    private void addObstacles(){

        int random = r.nextInt(2);
        switch (random){
            case 0:
                obstacles.add(new Razor());
                break;
            case 1:
                obstacles.add(new SingleWood());
        }
    }

    private void removeObstacles(){

        //Removing obstacles that are outside the screen
        Iterator<Obstacle> iter = obstacles.iterator();
        while (iter.hasNext()) {
            Obstacle obst = iter.next();
            if (obst.x < - 100) {
                iter.remove();
            }
        }
    }

}

