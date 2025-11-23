public class test {
    public static void main(String[] args) {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 5);
        Point p3 = new Point(-1, 3);
        Point p4 = new Point(4, 3);


        Line l = new Line(p1,p2);
        System.out.println();
        System.out.println(l.slope());
        Line l1 = new Line(p3,p4);
        System.out.println(l1.slope());
        System.out.println(l1.isIntersecting(l));
        System.out.println(l1.intersectionWith(l));
    }
}
