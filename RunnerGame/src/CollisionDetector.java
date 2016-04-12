public class CollisionDetector {
    public boolean CollisionDetector(Ninja n, ObstacleDatabase o) {

        for (int i = 0; i < o.obstacles.size(); i++) {
            if (o.obstacles.get(i).boundingBox.intersects(n.boundingBox)) {
                return true;
            }
        }
        return false;
    }

}
