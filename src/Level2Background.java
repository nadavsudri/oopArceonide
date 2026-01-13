import biuoop.DrawSurface;
import java.awt.Color;

public class Level2Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        // Draw a sun
        d.setColor(new Color(239, 231, 176));
        for (int i = 0; i < 100; i++) {
            d.drawLine(150, 150, i * 8, 250);
        }
        d.fillCircle(150, 150, 60);
        d.setColor(new Color(255, 225, 24));
        d.fillCircle(150, 150, 50);
    }
    @Override
    public void timePassed() {}
}