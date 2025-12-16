public class Velocity {
    private double dx;
    private double dy;
    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }
    public double getDx() {
        return dx;
    }
    public double getDy() {
        return dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    //uses trigonometry to convert dx and dy to angle and speed
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx =Math.cos(angle)*speed;
        double dy =Math.sin(angle)*speed;
        return new Velocity(dx, dy);
    }
}