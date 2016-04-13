import java.awt.geom.Area;

public class CollisionDetector {
    public boolean CollisionDetector(Ninja n, ObstacleDatabase o) {

        for (int i = 0; i < o.obstacles.size(); i++) {
            Area intersect = new Area(o.obstacles.get(i).boundingBox);
            intersect.intersect(new Area(n.boundingBox));
            if (!intersect.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public int GetIndexOfObject(Ninja n, ObstacleDatabase o) {
        for (int i = 0; i < o.obstacles.size(); i++) {
            Area intersect = new Area(o.obstacles.get(i).boundingBox);
            intersect.intersect(new Area(n.boundingBox));
            if (!intersect.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

}
