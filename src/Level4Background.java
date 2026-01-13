import biuoop.DrawSurface;
import java.awt.Color;

public class Level4Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 153, 255));
        d.fillRectangle(0, 0, 800, 600);
        // Draw some simple clouds
        d.setColor(Color.WHITE);
        d.fillCircle(150, 400, 30);
        d.fillCircle(170, 420, 35);
        d.fillCircle(190, 390, 30);
    }
    @Override
    public void timePassed() {}
}