import biuoop.DrawSurface;
import java.awt.Color;

public class Level1Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 162, 60);
        d.drawCircle(400, 162, 90);
        d.drawCircle(400, 162, 120);
        d.drawLine(400, 42, 400, 282);
        d.drawLine(280, 162, 520, 162);
    }
    @Override
    public void timePassed() {}
}