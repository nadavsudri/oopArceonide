public class Point {
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double distance(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }
    public static double distance(Point p1 , Point p2) {
        return p1.distance(p2);
    }
    public boolean equals(Point p) {
        return Math.abs(this.x - p.x)<Constants.EPS && Math.abs(this.y - p.y)<Constants.EPS;
    }
    //getters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    //prints point as (x,y)
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
