import biuoop.DrawSurface;

import java.awt.*;

public class Block implements Collidable,Sprite {
    //Fields
    private int life=1;
    private Rectangle shape;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    private java.awt.Color color;

    //constructor
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
    public void setColor(java.awt.Color color) {
        this.color = color;
    }
    public int getLife() {
        return life;
    }
    //Covers the possible hit options and returns a new velocity.
    @Override
    public  Velocity hit(Point collisionPoint, Velocity currentVelocity){
        this.life--;
        if(top.isOnLine(collisionPoint)){
            return (new Velocity(currentVelocity.getDx(),-currentVelocity.getDy()));}
        if(bottom.isOnLine(collisionPoint)){
            return (new Velocity(currentVelocity.getDx(),-currentVelocity.getDy()));}
        if(left.isOnLine(collisionPoint)){
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));}
        if(right.isOnLine(collisionPoint)){
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));}
        return currentVelocity;
    }

    //Draw on a given Draw surface
    public void drawOn(DrawSurface surface){
        surface.setColor(color);
        surface.fillRectangle((int)shape.topLeft.getX(),(int)shape.topLeft.getY(),(int)shape.getWidth(),(int)shape.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int)shape.topLeft.getX(),(int)shape.topLeft.getY(),(int)shape.getWidth(),(int)shape.getHeight());
    }
    @Override
    public void timePassed(){


    }
}
