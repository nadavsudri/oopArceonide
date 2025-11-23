public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collided;
    public CollisionInfo(Point collisionPoint, Collidable collided) {
        this.collisionPoint = collisionPoint;
        this.collided = collided;}
    // the point at which the collision occurs.
    public Point collisionPoint(){
        return collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return collided;
    }
}