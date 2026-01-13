import biuoop.DrawSurface;
import java.awt.Color;

public class ScoreIndicator implements Sprite {
    private Counter score;
    public ScoreIndicator(Counter score) { this.score = score; }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + score.getCount(), 15);
    }
    public void timePassed() {}
}