//import biuoop.DrawSurface;
//import biuoop.GUI;
//import biuoop.Sleeper;
//import biuoop.KeyboardSensor;
//import java.awt.*;
//
//public class Game {
//
//    //fields
//    private SpriteCollection sprites;
//    private GameEnvironment environment;
//    private GUI gui;
//    private Counter blockCounter;
//    private Counter ballCounter;
//    private Counter scoreCounter;
//
//
//    public void addCollidable(Collidable c){
//        environment.addCollidable(c);
//    }
//    public void addSprite(Sprite s){
//        sprites.addSprite(s);
//    }
//
//    //removes sprite s from sprites (for collitions)
//    public void removeSprite(Sprite s){sprites.removeSprite(s);}
//
//    public void removeCollidable(Collidable c){environment.removeCollidable(c);}
//
//    // Initialize a new game: create the Blocks and Ball (and Paddle)
//    // and add them to the game.
//    public void initialize(){
//
//        //sets up GUI , Game environment and Sprites
//        this.gui= new GUI("Arcanoide",800,600);
//        this.environment = new GameEnvironment();
//        this.sprites = new SpriteCollection();
//        this.scoreCounter = new Counter();
//        //ball setup
//        Ball ball = new Ball(500,100,10, Color.green,environment);
//        Ball ball2 = new Ball(470,100,10, Color.blue,environment);
//        Ball ball3 = new Ball(520,100,10, Color.orange,environment);
//        this.ballCounter = new Counter();
//        BallRemover br = new BallRemover(this,ballCounter);
//        ball.setVelocity(Velocity.fromAngleAndSpeed(100,6));
//        ball2.setVelocity(Velocity.fromAngleAndSpeed(130,6));
//        ball3.setVelocity(Velocity.fromAngleAndSpeed(150,6));
//        ballCounter.increase(3);
//
//        ScoreTrackingListener scorelistener = new ScoreTrackingListener(scoreCounter);
//
//
//        //innit and adds Ball to the game and sprites
//
//        Point[] wallPositions = {new Point(0, 0), new Point(0, 0), new Point(770, 0), new Point(0, 570)};
//        sprites.addSprite(ball);
//        sprites.addSprite(ball2);
//        sprites.addSprite(ball3);
//        //innit the walls Blocks for the game
//        int[] wallWidths =  {30, 800, 30, 800};
//        int[] wallHeights = {600, 30, 600, 30};
//
//        for (int i = 0; i < 4; i++) {
//            Rectangle rect = new Rectangle(wallPositions[i], wallWidths[i], wallHeights[i]);
//            Block wall = new Block(rect);
//            wall.setLife((int)Double.POSITIVE_INFINITY);
//            wall.setColor(Color.darkGray);
//            environment.addCollidable(wall);
//            sprites.addSprite(wall);
//        }
//
//        //takes care of block removal objects
//        this.blockCounter = new Counter();
//        BlockRemover ht = new BlockRemover(this, blockCounter);
//
//        //death block
//        Block death = new Block(new Rectangle(new Point(0,569.9),800,40));
//        death.setColor(Color.darkGray);
//        environment.addCollidable(death);
//        sprites.addSprite(death);
//        death.addHitListener(br);
//
//        //adds the blocks Collidables
//        for (int i=0;i<6;i++){
//            java.awt.Color color = Ball.getRandomColor();
//            for (int j=0;j<12-i;j++){
//                Rectangle recBlock = new Rectangle(new Point(720-Constants.blockWidth*j,200+i*Constants.blockHieght),Constants.blockWidth,Constants.blockHieght);
//                Block block = new Block(recBlock);
//                block.addHitListener(ht);
//                block.addHitListener(scorelistener);
//                block.setColor(color);
//                environment.addCollidable(block);
//                sprites.addSprite(block);
//                blockCounter.increase(1);
//
//            }
//        }
//        //add a Keyboard sensor for movment
//        KeyboardSensor key = gui.getKeyboardSensor();
//        //adds Paddle
//        Paddle paddle = new Paddle(new Rectangle(new Point(400,550),Constants.blockWidth+40,Constants.blockHieght-5),key);
//        sprites.addSprite(paddle);
//        environment.addCollidable(paddle);
//
//    }
//
//    // Run the game -- start the animation loop.
//    public void run(){
//        int fps = 60;
//        int millisecondsPerFrame = 1000 / fps;
//        Sleeper sleeper = new Sleeper();
//        while (true){
//
//            long startTime = System.currentTimeMillis(); // timing
//            DrawSurface d = this.gui.getDrawSurface();
//            d.drawText(400,50,""+scoreCounter.getCount(),20);
//            this.sprites.drawAllOn(d);
//            gui.show(d);
//            this.sprites.notifyAllTimePassed();
//            long usedTime = System.currentTimeMillis() - startTime;
//            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
//            if (milliSecondLeftToSleep > 0) {
//                sleeper.sleepFor(milliSecondLeftToSleep);
//            }
//
//            //
//            if(this.blockCounter.getCount()==0){
//                scoreCounter.increase(100);
//                break;}
//            if(this.ballCounter.getCount()==0){
//                break;
//            }
//
//        }
//        System.exit(1);
//    }
//
//    public void removeBall(Ball b){
//        this.sprites.removeSprite(b);
//    }
//}