import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    public final double witdh = Constants.WIDTH;
    public final double hight = Constants.HEIGHT;
    private List<Collidable> collidables = new ArrayList<Collidable>();    // add the given collidable to the environment.
    public void addCollidable(Collidable c){
        collidables.add(c);
    }
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory){
        Point closestCollitionPoint = null;
        Collidable closestCollidable = null;
        for(Collidable collidable : collidables){
            Rectangle collisionRectangle = collidable.getCollisionRectangle();
            List<Point> l = collisionRectangle.intersectionPoints(trajectory);//for debug
            if(!collisionRectangle.intersectionPoints(trajectory).isEmpty()){
                Point tempCollition = trajectory.closestIntersectionToStartOfLine(collisionRectangle);
                if(tempCollition==null)continue;
                if(closestCollitionPoint == null){
                    closestCollitionPoint=tempCollition;
                    closestCollidable=collidable;
                }
                else if(tempCollition.distance(trajectory.start)<closestCollitionPoint.distance(trajectory.start)){
                    closestCollitionPoint = tempCollition;
                    closestCollidable = collidable;
                }
            }
        }
        if(closestCollidable==null){return null;}
        return new CollisionInfo(closestCollitionPoint,closestCollidable);
    }
    public List<Collidable> getCollidables() {
        return collidables;
    }

}