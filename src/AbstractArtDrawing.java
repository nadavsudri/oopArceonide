import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {

    public void drawRandomCircles() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        DrawSurface d2 = gui.getDrawSurface();
        Line[] lines = new Line[10];
        //iterates trough lines array and drawing a random line each iteration
        // checks if the new line is intersecting with the created lines.
        // draws the intersection points and middle point for each line.
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1= rand.nextInt(300) + 1; // get integer in range 1-300
            int y2= rand.nextInt(300) + 1; // get integer in range 1-300
            d.setColor(Color.black);
            d.drawLine(x1, y1, x2, y2);
            Line l = new Line(x1, y1, x2, y2);
            lines[i] = l;
            Point mid = l.middle();
            for (Line line : lines) {
                if(line == null) {break;}
                if (l.isIntersecting(line)){
                    d.setColor(Color.blue);
                    d.fillCircle((int)l.intersectionWith(line).getX(),(int) l.intersectionWith(line).getY(),3);
                }
            }
            d.setColor(Color.red);
            d.fillCircle((int)mid.getX(),(int)mid.getY(),3);
        }

        gui.show(d);

    }

    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomCircles();
    }
}