package collision;

import database.ObstacleDatabase;
import models.Ninja;
import models.Obstacle;

import java.awt.geom.Area;

public class CollisionDetector {
    public boolean CollisionDetector(Ninja ninja, ObstacleDatabase obstacleDB) {

        for (int i = 0; i < obstacleDB.obstacles.size(); i++) {
            Area intersect = new Area(obstacleDB.obstacles.get(i).getBoundingBox());
            intersect.intersect(new Area(ninja.getBoundingBox()));
            if (!intersect.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public int GetIndexOfObject(Ninja ninja, ObstacleDatabase obstacleDB) {
        for (int i = 0; i < obstacleDB.obstacles.size(); i++) {
            Area intersect = new Area(obstacleDB.obstacles.get(i).getBoundingBox());
            intersect.intersect(new Area(ninja.getBoundingBox()));
            if (!intersect.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

}
