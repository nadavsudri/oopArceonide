import java.util.List;

/**
 * LevelInformation stores all the data needed for a specific level.
 */
public interface LevelInformation {
    int numberOfBalls();
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    String levelName();
    Sprite getBackground();
    List<Block> blocks();
    int numberOfBlocksToRemove();
}