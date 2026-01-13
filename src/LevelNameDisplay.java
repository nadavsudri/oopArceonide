import biuoop.DrawSurface;
import java.awt.Color;

public class LevelNameDisplay implements Sprite {
    private String name;
    public LevelNameDisplay(String name) { this.name = name; }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "Level: " + name, 15);
    }
    public void timePassed() {}
}