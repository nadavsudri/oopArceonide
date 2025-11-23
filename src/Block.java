import biuoop.DrawSurface;
import biuoop.GUI;

public class Block implements Collidable {
    private Rectangle shape;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    public Block(Rectangle shape) {
        this.shape = shape;
        this.top = new Line(shape.topLeft, new Point(shape.topLeft.getX()+shape.getWidth(), shape.topLeft.getY()));
        this.bottom = new Line(new Point(shape.topLeft.getX(),shape.topLeft.getY()+shape.getHeight()), new Point(shape.topLeft.getX()+shape.getWidth(), shape.topLeft.getY()+shape.getHeight()));
        this.left = new Line(shape.topLeft, new Point(shape.topLeft.getX(), shape.topLeft.getY()+shape.getHeight()));
        this.right = new Line(new Point(shape.topLeft.getX()+shape.getWidth(),shape.topLeft.getY()), new Point(shape.topLeft.getX()+shape.getWidth(), shape.topLeft.getY()+shape.getHeight()));
    }
    @Override
    public Rectangle getCollisionRectangle(){
        return shape;
    }
    @Override
    public  Velocity hit(Point collisionPoint, Velocity currentVelocity){
        System.out.println("hit!" + collisionPoint);
        if(top.isOnLine(collisionPoint)){
            System.out.println("top is online");
            System.out.println(-currentVelocity.getDy());
            return (new Velocity(currentVelocity.getDx(),-currentVelocity.getDy()));
        }
        if(bottom.isOnLine(collisionPoint)){
            System.out.println("bottom is online");
            return (new Velocity(currentVelocity.getDx(),-currentVelocity.getDy()));}
        if(left.isOnLine(collisionPoint)){
            System.out.println("left is online");
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));}
        if(right.isOnLine(collisionPoint)){
            System.out.println("right is online");
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));}
        return currentVelocity;
    }
    public void drawOnGUI(DrawSurface surface){
        surface.setColor(java.awt.Color.BLACK);
        surface.fillRectangle((int)shape.topLeft.getX(),(int)shape.topLeft.getY(),(int)shape.getWidth(),(int)shape.getHeight());
    }
}
