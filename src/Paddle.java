import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.*;

public class Paddle implements Sprite, Collidable {
    Rectangle shape;
    private int speed = 15;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    private biuoop.KeyboardSensor keyboard;
    private GUI gui;
    public Paddle(Rectangle shape,KeyboardSensor keyboard) {
        this.shape = shape;
        this.top = new Line(shape.topLeft, new Point(shape.topLeft.getX()+shape.getWidth(), shape.topLeft.getY()));
        this.bottom = new Line(new Point(shape.topLeft.getX(),shape.topLeft.getY()+shape.getHeight()), new Point(shape.topLeft.getX()+shape.getWidth(), shape.topLeft.getY()+shape.getHeight()));
        this.left = new Line(shape.topLeft, new Point(shape.topLeft.getX(), shape.topLeft.getY()+shape.getHeight()));
        this.right = new Line(new Point(shape.topLeft.getX()+shape.getWidth(),shape.topLeft.getY()), new Point(shape.topLeft.getX()+shape.getWidth(), shape.topLeft.getY()+shape.getHeight()));
        this.keyboard = keyboard;
    }
    private Line[] splitTop() {
        Line[] tops = new Line[5];
        double segment = top.start.distance(top.end())/5.0;
        for (int i = 0; i < 5; i++) {
            tops[i] = new Line(new Point(i*segment+top.start.getX(),top.start.getY()), new Point((i+1)*segment+top.start().getX(),top.end().getY()));
        }
        return tops;
    }
    public void moveLeft(){
        if(this.shape.topLeft.getX()>30){
        this.shape.topLeft=new Point(this.shape.topLeft.getX()-speed,this.shape.topLeft.getY());}

    }
    public void moveRight(){
        if(this.shape.topLeft.getX()+this.shape.getWidth()<770){
        this.shape.topLeft=new Point(this.shape.topLeft.getX()+speed,this.shape.topLeft.getY());}
    }
    // Sprite
    public void timePassed(){
        KeyboardSensor keyboard = this.keyboard;
        if(keyboard.isPressed(KeyboardSensor.LEFT_KEY)){
            moveLeft();
            updateEdges();
        }
        if(keyboard.isPressed(KeyboardSensor.RIGHT_KEY)){
            moveRight();
            updateEdges();
        }
    }
    //Paddle has infinte life
    public int getLife(){
        return (int)Double.POSITIVE_INFINITY;
    }

    //draw on a drawsurface
    public void drawOn(DrawSurface d){
        d.setColor(Color.yellow);
        d.fillRectangle((int)shape.topLeft.getX(),(int)shape.topLeft.getY(),(int)shape.getWidth(),(int)shape.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int)shape.topLeft.getX(),(int)shape.topLeft.getY(),(int)shape.getWidth(),(int)shape.getHeight());
    }
    // Collidable
    public Rectangle getCollisionRectangle(){
        return shape;
    }
    //covers the hit options -> the array is for "fun paddle".
    public Velocity hit(Ball hitter , Point collisionPoint, Velocity currentVelocity){
        Line [] tops = splitTop();
        //middle part -> returns stright up.
        if(tops[2].isOnLine(collisionPoint)){
            return (Velocity.fromAngleAndSpeed(0,8)) ;}

        // edges -> return 1.5 the X velocity
        if(tops[0].isOnLine(collisionPoint)){
            return (Velocity.fromAngleAndSpeed(120,8)) ;}
        if(tops[4].isOnLine(collisionPoint)){
            return (Velocity.fromAngleAndSpeed(240,8));}

        // the between sections -> returns 1.2 the X velocity
        if(tops[1].isOnLine(collisionPoint)){
            return (Velocity.fromAngleAndSpeed(60,8));}
        if(tops[3].isOnLine(collisionPoint)){
            return (Velocity.fromAngleAndSpeed(320,8));}

        //the rest of the lines -> returns like a regular block
        if(bottom.isOnLine(collisionPoint)){
            return (new Velocity(currentVelocity.getDx(),-currentVelocity.getDy()));}
        if(left.isOnLine(collisionPoint)){
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));}
        if(right.isOnLine(collisionPoint)){
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));}
        return currentVelocity;
    }

    //updates the lines that makes the shape each movment
    private void updateEdges() {
        //update the points
        Point tl = shape.topLeft;
        Point tr = new Point(tl.getX() + shape.getWidth(), tl.getY());
        Point bl = new Point(tl.getX(), tl.getY() + shape.getHeight());
        Point br = new Point(tl.getX() + shape.getWidth(), tl.getY() + shape.getHeight());
        //update the lines
        this.top = new Line(tl, tr);
        this.bottom = new Line(bl, br);
        this.left = new Line(tl, bl);
        this.right = new Line(tr, br);
    }
    // Add this paddle to the game.
    public void addToGame(Game g){

    }
}