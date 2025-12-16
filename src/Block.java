import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable,Sprite,HitNotifier {
    //Fields
    private int life = 1;
    private Rectangle shape;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();


    //constructor
    public Block(Rectangle shape) {
        this.shape = shape;
        this.top = new Line(shape.topLeft, new Point(shape.topLeft.getX() + shape.getWidth(), shape.topLeft.getY()));
        this.bottom = new Line(new Point(shape.topLeft.getX(), shape.topLeft.getY() + shape.getHeight()), new Point(shape.topLeft.getX() + shape.getWidth(), shape.topLeft.getY() + shape.getHeight()));
        this.left = new Line(shape.topLeft, new Point(shape.topLeft.getX(), shape.topLeft.getY() + shape.getHeight()));
        this.right = new Line(new Point(shape.topLeft.getX() + shape.getWidth(), shape.topLeft.getY()), new Point(shape.topLeft.getX() + shape.getWidth(), shape.topLeft.getY() + shape.getHeight()));
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return shape;
    }

    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    public int getLife() {
        return this.life;
    }

    //Covers the possible hit options and returns a new velocity.
    @Override
    public Velocity hit(Ball hitter , Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);

        if (top.isOnLine(collisionPoint)) {
            return (new Velocity(currentVelocity.getDx(), -currentVelocity.getDy()));
        }

        if (bottom.isOnLine(collisionPoint)) {
            return (new Velocity(currentVelocity.getDx(), -currentVelocity.getDy()));
        }

        if (left.isOnLine(collisionPoint)) {
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));
        }

        if (right.isOnLine(collisionPoint)) {
            return (new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()));
        }



        return currentVelocity;
    }

    //Draw on a given Draw surface
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) shape.topLeft.getX(), (int) shape.topLeft.getY(), (int) shape.getWidth(), (int) shape.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) shape.topLeft.getX(), (int) shape.topLeft.getY(), (int) shape.getWidth(), (int) shape.getHeight());
    }

    @Override
    public void timePassed() {


    }

    // adds listner object
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    //remove listner object
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}