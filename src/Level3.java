import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Level3 implements LevelInformation {
    public int numberOfBalls() { return 2; }
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        v.add(Velocity.fromAngleAndSpeed(40, 5));
        v.add(Velocity.fromAngleAndSpeed(-40, 5));
        return v;
    }
    public int paddleSpeed() { return 8; }
    public int paddleWidth() { return 100; }
    public String levelName() { return "Green 3"; }
    public Sprite getBackground() { return new Level3Background(); }
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Block b = new Block(new Rectangle(new Point(730 - (j * 50), 150 + (i * 20)), 50, 20));
                b.setColor(colors[i]);
                blocks.add(b);
            }
        }
        return blocks;
    }
    public int numberOfBlocksToRemove() { return 40; }
}