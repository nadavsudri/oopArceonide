import biuoop.DrawSurface;
import java.awt.Color;

public class Level3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 120, 0));
        d.fillRectangle(0, 0, 800, 600);
        // Draw a simple building/tower
        d.setColor(Color.BLACK);
        d.fillRectangle(65, 450, 100, 150);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(75 + j * 18, 460 + i * 25, 10, 15);
            }
        }
    }
    @Override
    public void timePassed() {}
}