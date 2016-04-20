package database;

import models.Obstacle;
import models.Razor;
import models.SingleWood;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObstacleDatabase implements ActionListener {
    private Obstacle obst;
    private Random rand;
    private Timer timer;

    public ObstacleDatabase(){
        this.rand = new Random();
        this.timer = new Timer(1200, this);
        this.obstacles = new ArrayList<Obstacle>();
        this.timer.start();
    }

    public ArrayList<Obstacle> obstacles;

    public Timer getTimer(){
        return this.timer;
    }

    public void actionPerformed(ActionEvent e) {
        this.obst = new Razor();
        this.addObstacles();
        this.removeObstacles();
    }

    private void addObstacles(){

        int random = this.rand.nextInt(2);
        switch (random){
            case 0:
                this.obstacles.add(new Razor());
                break;
            case 1:
                this.obstacles.add(new SingleWood());
        }
    }

    private void removeObstacles(){

        //Removing obstacles that are outside the screen
        Iterator<Obstacle> iter = this.obstacles.iterator();
        while (iter.hasNext()) {
            Obstacle obst = iter.next();
            if (obst.getX() < - 100) {
                iter.remove();
            }
        }
    }

}

