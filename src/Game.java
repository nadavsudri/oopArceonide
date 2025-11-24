import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.*;
import java.util.concurrent.locks.ReadWriteLock;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    KeyboardSensor keyboardSensor;

    public void addCollidable(Collidable c){
        environment.addCollidable(c);
    }
    public void addSprite(Sprite s){
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize(){

        //sets up GUI , Game environment and Sprites
        this.gui= new GUI("Arcanoide",800,600);
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        Ball ball = new Ball(500,400,5, Color.green,environment);

        //innit and adds Ball to the game and sprites
        ball.setVelocity(Velocity.fromAngleAndSpeed(100,5));
        Point[] wallPositions = {new Point(0, 0), new Point(0, 0), new Point(770, 0), new Point(0, 570)};
        sprites.addSprite(ball);
        //innit the walls Blocks for the game
        int[] wallWidths =  {30, 800, 30, 800};
        int[] wallHeights = {600, 30, 600, 30};
        for (int i = 0; i < 4; i++) {
            Rectangle rect = new Rectangle(wallPositions[i], wallWidths[i], wallHeights[i]);
            Block wall = new Block(rect);
            wall.setColor(Color.darkGray);
            environment.addCollidable(wall);
            sprites.addSprite(wall);
        }

        //adds the blocks Collidables
        for (int i=0;i<6;i++){
            java.awt.Color color = Ball.getRandomColor();
            for (int j=0;j<12-i;j++){
                Rectangle recBlock = new Rectangle(new Point(720-Constants.blockWidth*j,200+i*Constants.blockHieght),Constants.blockWidth,Constants.blockHieght);
                Block block = new Block(recBlock);
                block.setColor(color);
                environment.addCollidable(block);
                sprites.addSprite(block);
            }
        }
        //add a Keyboard sensor for movment
        KeyboardSensor key = gui.getKeyboardSensor();

        //adds Paddle
        Paddle paddle = new Paddle(new Rectangle(new Point(400,500),Constants.blockWidth+20,Constants.blockHieght),key);
        sprites.addSprite(paddle);
        environment.addCollidable(paddle);
    }

    // Run the game -- start the animation loop.
    public void run(){
        int fps = 60;
        int millisecondsPerFrame = 1000 / fps;
        Sleeper sleeper = new Sleeper();
        while (true){
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }
}