import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

public class Ball implements Sprite{
    Point center;
    double radius;
    java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameENV;
    public Ball(double x,double y , int r, java.awt.Color color, GameEnvironment gameENV) {

        this.center = new Point(x,y);
        this.radius = r;
        this.color = color;
        this.gameENV = gameENV;
    }
    //Ball has infinte life
    public int getLife(){
        return (int)Double.POSITIVE_INFINITY;
    }

    // accessors
    public int getX(){
        return (int) this.center.getX();
    }
    public int getY(){
        return (int) this.center.getY();
    }
    public int getSize(){
       // return (int) (this.radius*this.radius*Math.PI);
        return (int) this.radius;
    }

    public java.awt.Color getColor(){
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface){
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    public void setVelocity(Velocity v) {
        this.velocity = v;

    }

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);

    }
    /**Moves a Ball within the bounds given (Frame / GUI)
     * moves by velocity updates location.
     * hit on bound causes the velocity to change direction**/
    public Velocity getVelocity() {
        return this.velocity;
    }
    //returns the trejectory
    public Line getTrejectory() {
        Point start = this.center;
        Velocity v = this.getVelocity();
        double L = 1; // length of traj (useful for debugging)
        Point end = new Point(start.getX() + v.getDx() * L, start.getY() + v.getDy() * L);
        return new Line(start, end);

        //return new Line(this.center,new Point(this.center.getX()+5*this.velocity.getDx(),this.center.getY()+5*velocity.getDy()));
    }

    /**
     * the moveOneStep method gets A and B as bound for movement (relevant when the frame changes).
     * if the edge of the ball (calculated as Radius + center point) touches the bound -> the corresponding parameter of the velocity
     * changes to its negative value (change direction)
     * Ex. the ball touched the upper bound -> it should go down -> the dy sets as -dy.
     *
     **/

    public void moveOneStep() {
       CollisionInfo closestCollidable = this.gameENV.getClosestCollision(this.getTrejectory());
       if (closestCollidable == null) {
           this.center = this.getVelocity().applyToPoint(this.center);
           return;
       }
       Point collition = closestCollidable.collisionPoint();
       Collidable collitionObject = closestCollidable.collisionObject();
       this.center = new Point(collition.getX()-this.velocity.getDx()*Constants.EPS, collition.getY()-this.velocity.getDy()*Constants.EPS);
       this.velocity = collitionObject.hit(this,collition,this.velocity);
    }
    //returns random color from java.awt.Color lib
    public static java.awt.Color getRandomColor() {
        Random rand = new Random();
        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);
        return new java.awt.Color(red,green,blue);
    }
    @Override
    public void timePassed(){
        moveOneStep();
    }

}
