import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Level2 implements LevelInformation {
    public int numberOfBalls() { return 10; }
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        for (int i = 0; i < 10; i++) { v.add(Velocity.fromAngleAndSpeed(-45 + (i * 10), 4)); }
        return v;
    }
    public int paddleSpeed() { return 2; }
    public int paddleWidth() { return 550; }
    public String levelName() { return "Wide Easy"; }
    public Sprite getBackground() { return new Level2Background(); }
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
        for (int i = 0; i < 15; i++) {
            Block b = new Block(new Rectangle(new Point(20 + i * 50.6, 250), 50.6, 20));
            b.setColor(colors[i / 3]);
            blocks.add(b);
        }
        return blocks;
    }
    public int numberOfBlocksToRemove() { return 15; }
}