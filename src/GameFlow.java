import biuoop.KeyboardSensor;
import java.util.List;

/**
 * GameFlow manages moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private Counter score;

    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboard, runner, score);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getRemainingBalls() == 0) {
                // Run Game Over Screen
                break;
            }
        }
    }
}