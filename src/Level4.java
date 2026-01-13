import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Level4 implements LevelInformation {
    public int numberOfBalls() { return 3; }
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        v.add(Velocity.fromAngleAndSpeed(30, 6));
        v.add(Velocity.fromAngleAndSpeed(0, 6));
        v.add(Velocity.fromAngleAndSpeed(-30, 6));
        return v;
    }
    public int paddleSpeed() { return 10; }
    public int paddleWidth() { return 100; }
    public String levelName() { return "Final Four"; }
    public Sprite getBackground() { return new Level4Background(); }
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block b = new Block(new Rectangle(new Point(25 + (j * 50), 100 + (i * 20)), 50, 20));
                b.setColor(colors[i]);
                blocks.add(b);
            }
        }
        return blocks;
    }
    public int numberOfBlocksToRemove() { return 105; }
}