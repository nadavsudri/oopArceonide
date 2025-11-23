import biuoop.DrawSurface;

public class Line {
    Point start;
    Point end;
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1,y1);
        this.end = new Point(x2,y2);
    }
    public double slope(){
        double delX = this.end.getX() - this.start.getX();
        double delY = this.end.getY() - this.start.getY();
        return delY/delX;

    }
    //calc f(x) as f is the liner equation of line.
    public double f (double x){
        double m  =this.slope();
        return m*(x-this.start.getX())+this.start.getY();
    }
    //calc f^(-1)(x)
    public double f_inv(double y){
        double m  =this.slope();
        return m*(y-this.start.getY())+this.start.getX();

    }

    // Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    // Returns the middle point of the line
    public Point middle() {
    return new Point((this.start.getX() + this.end.getX())/2, (this.start.getY() + this.end.getY())/2);
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }
    public double iners_Y(){
        return this.f(0);
    }

    // Returns true if the lines intersect, false otherwise
    // uses basic algebra and calculus -> find any intersections -> checks if it's within the line's bounds.
    public boolean isIntersecting(Line other) {
        double this_slope = this.slope();
        double other_slope = other.slope();
        //both slopes are infinite
        if(Double.isInfinite(this_slope) && Double.isInfinite(other_slope)){return false;}
        //other is infinite
        if (Double.isInfinite((other_slope))){
            double x_inf = other.start.getX();
            double f1 = this.f(x_inf);
            double minY = Math.min(other.start.getY(), other.end.getY());
            double maxY = Math.max(other.start.getY(), other.end.getY());
            return f1 >= minY && f1 <= maxY;
        }
        //this is infinite
        if(Double.isInfinite((this_slope))){
            double x_inf = this.start.getX();
            double f1 = other.f(x_inf);
            double minY = Math.min(other.start.getY(), other.end.getY());
            double maxY = Math.max(other.start.getY(), other.end.getY());
            return f1 >= minY && f1 <= maxY;}
        //both are finite
        if (this_slope == other_slope) {return false;}
        double x_inter = (other.iners_Y()-this.iners_Y())/(this_slope-other_slope);
        // checks if the x and y are in the line's point_start and point_end
        boolean x_is_in = x_inter >= Math.min(this.start.getX(), this.end.getX()) && x_inter <= Math.max(this.start.getX(), this.end.getX()) && x_inter >= Math.min(other.start.getX(), other.end.getX()) && x_inter <= Math.max(other.start.getX(), other.end.getX());
        return x_is_in ;
    }
    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double m1 = this.slope();
        double m2 = other.slope();
        // both vertical
        if ((Double.isInfinite(m1)) && (Double.isInfinite(m2))) return null;
        // this vertical
        if (Double.isInfinite(m1)) {
            double x = this.start.getX();
            double y = other.f(x);
            if (this.isOnLine(new Point(x,y)) && other.isOnLine(new Point(x,y)))
                return new Point(x,y);
            return null;
        }

        // other vertical
        if (Double.isInfinite(m2)) {
            double x = other.start.getX();
            double y = this.f(x);
            if (this.isOnLine(new Point(x,y)) && other.isOnLine(new Point(x,y)))
                return new Point(x,y);
            return null;
        }

        // parallel
        if (m1 == m2) return null;
        double x = (other.iners_Y() - this.iners_Y()) / (m1 - m2);
        double y = this.f(x);
        Point p = new Point(x,y);
        if (this.isOnLine(p) && other.isOnLine(p)) return p;
        return null;
    }
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }
    //calcukate the closest intersection point between a rec and line
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) { return null; }

        Point minDist = points.getFirst();
        for (Point p : points) {
            if (this.start.distance(p) < this.start.distance(minDist)) {
                minDist = p;
            }
        }
        return minDist;
    }
    public boolean isOnLine(Point p) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x = p.getX();
        double y = p.getY();
        double area = (x - x1)*(y2 - y1) - (y - y1)*(x2 - x1);
        if (Math.abs(area) > Constants.EPS) return false;
        return x >= Math.min(x1,x2) - Constants.EPS &&
                x <= Math.max(x1,x2) + Constants.EPS &&
                y >= Math.min(y1,y2) - Constants.EPS &&
                y <= Math.max(y1,y2) + Constants.EPS;
    }
    public void drawOn(DrawSurface d){
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

}