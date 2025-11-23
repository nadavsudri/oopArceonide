import java.util.ArrayList;

public class Rectangle {
    private double width;
    private double height;
    Point topLeft;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.topLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line){
        java.util.List<Point> intersectionPoints = new ArrayList<>();
        Point tl = this.topLeft;
        Point tr = new Point(topLeft.getX()+width, topLeft.getY());
        Point br = new Point(topLeft.getX()+width, topLeft.getY()+height);
        Point bl = new Point(topLeft.getX(), topLeft.getY()+height);
        Line upperLine = new Line(tl, tr);
        Line bottomLine = new Line(bl, br);
        Line leftLine = new Line(tl, bl);
        Line rightLine = new Line(br, tr);
        Line []lines = {upperLine, bottomLine, leftLine, rightLine};
        for (Line l :lines){
            if(l.isIntersecting(line)&&l.intersectionWith(line)!=null){
                intersectionPoints.add(l.intersectionWith(line));
            }

        }
        return intersectionPoints;
    }

    // Return the width and height of the rectangle
    public double getWidth(){return width;}
    public double getHeight(){return height;}

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft(){return topLeft;}
}