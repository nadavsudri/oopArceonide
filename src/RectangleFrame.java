import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * this class was created to set the idea of a frame, giving its Ball objects their bounds.
 *the class itself is not much, but it helps with abstraction and role assignment.
 * **/
public class RectangleFrame {
    int length;
    int width;
    Point topLeft;
    java.awt.Color color;
    Ball[] balls;
    //constructor
    public RectangleFrame(Point topLeft,int length, int width,java.awt.Color color,int ballAmnt) {
        this.length = length;
        this.width = width;
        this.color = color;
        this.topLeft =topLeft;
        balls = new Ball[ballAmnt];
    }
    // draw itself on a GUI
    public void drawOnFrame(DrawSurface surface){
        surface.setColor(color);
        surface.fillRectangle((int)topLeft.getX(),(int)topLeft.getY(),width,width);
    }

}
