import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;
    private KeyboardSensor keyboard;

    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter scoreCounter) {
        this.levelInfo = levelInfo;
        this.keyboard = keyboard;
        this.runner = runner;
        this.scoreCounter = scoreCounter;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
    }

    public void addCollidable(Collidable c) { environment.addCollidable(c); }
    public void addSprite(Sprite s) { sprites.addSprite(s); }
    public void removeSprite(Sprite s) { sprites.removeSprite(s); }
    public void removeCollidable(Collidable c) { environment.removeCollidable(c); }

    public void initialize() {
        this.running = true;
        addSprite(levelInfo.getBackground());

        // Walls
        int thick = 20;
        Block top = new Block(new Rectangle(new Point(0, 20), 800, thick));
        Block left = new Block(new Rectangle(new Point(0, 20), thick, 600));
        Block right = new Block(new Rectangle(new Point(800 - thick, 20), thick, 600));

        Block[] walls = {top, left, right};
        for (Block wall : walls) {
            wall.setColor(Color.GRAY);
            addCollidable(wall);
            addSprite(wall);
        }

        // Death Region (Bottom)
        BallRemover br = new BallRemover(this, ballCounter);
        Block death = new Block(new Rectangle(new Point(0, 600), 800, 5));
        death.addHitListener(br);
        addCollidable(death);

        // Blocks from LevelInfo
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(scoreCounter);
        for (Block b : levelInfo.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
            addCollidable(b);
            addSprite(b);
            blockCounter.increase(1);
        }

        // Balls
        for (Velocity v : levelInfo.initialBallVelocities()) {
            Ball b = new Ball(400, 500, 5, Color.WHITE, environment);
            b.setVelocity(v);
            b.addToGame(this);
            ballCounter.increase(1);
        }

        // Paddle
        Paddle paddle = new Paddle(new Rectangle(new Point(400 - levelInfo.paddleWidth() / 2, 560),
                levelInfo.paddleWidth(), 15), keyboard);
        paddle.setSpeed(levelInfo.paddleSpeed());
        addCollidable(paddle);
        addSprite(paddle);

        // UI
        addSprite(new ScoreIndicator(scoreCounter));
        addSprite(new LevelNameDisplay(levelInfo.levelName()));
    }

    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.blockCounter.getCount() == 0 || this.ballCounter.getCount() == 0) {
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen(keyboard)));
        }
    }

    public boolean shouldStop() { return !this.running; }
    public int getRemainingBalls() { return ballCounter.getCount(); }
    public void run() { this.running = true; this.runner.run(this); }

    public void removeBall(Ball b) {
        this.sprites.removeSprite(b);
    }
}