import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        GUI gui = new GUI("Test",Constants.WIDTH,Constants.HEIGHT);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();
        Rectangle rct = new Rectangle(new Point(400,550),20,40);
        Rectangle rct1 = new Rectangle(new Point(400,400),20,40);
        Rectangle rct2 = new Rectangle(new Point(140,140),20,40);
        Rectangle top = new Rectangle(new Point(0,0),800,40);
        Rectangle bottom = new Rectangle(new Point(0,560),800,40);
        Rectangle left = new Rectangle(new Point(0,0),40,600);
        Rectangle right = new Rectangle(new Point(760,0),40,600);
        Block b1 = new Block(rct);
        Block b2 = new Block(rct1);
        Block b3 = new Block(rct2);
        Block topBlock = new Block(top);
        Block bottomBlock = new Block(bottom);
        Block leftBlock = new Block(left);
        Block rightBlock = new Block(right);
        GameEnvironment gmENV = new GameEnvironment();
        gmENV.addCollidable(b1);
        gmENV.addCollidable(b2);
        gmENV.addCollidable(b3);
        gmENV.addCollidable(topBlock);
        gmENV.addCollidable(bottomBlock);
        gmENV.addCollidable(leftBlock);
        gmENV.addCollidable(rightBlock);


        Ball ball = new Ball(300,100,5, Color.green, gmENV);


        ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(270)+90,3));


        while(true){
            DrawSurface d = gui.getDrawSurface();
            b1.drawOn(d);
            b2.drawOn(d);
            b3.drawOn(d);
            topBlock.drawOn(d);
            bottomBlock.drawOn(d);
            leftBlock.drawOn(d);
            rightBlock.drawOn(d);
            ball.moveOneStep();
            //ball.getTrejectory().drawOn(d);
            ball.drawOn(d);
            sleeper.sleepFor(10);
            gui.show(d);
        }

    }
}
