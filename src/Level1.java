import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Level1 implements LevelInformation {
    public int numberOfBalls() { return 1; }
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        v.add(new Velocity(0, -4));
        return v;
    }
    public int paddleSpeed() { return 5; }
    public int paddleWidth() { return 80; }
    public String levelName() { return "Direct Hit"; }
    public Sprite getBackground() { return new Level1Background(); }
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block b = new Block(new Rectangle(new Point(390, 150), 20, 20));
        b.setColor(Color.RED);
        blocks.add(b);
        return blocks;
    }
    public int numberOfBlocksToRemove() { return 1; }
}