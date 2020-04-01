package multithread.immutable;

public class LineTest {

    public static void main(String[] args) {

        Point sPoint = new Point(3, 4);
        Point ePoint = new Point(30, 40);

        Line line = new Line(sPoint, ePoint);
        System.out.println(line);
        sPoint.x = 123;
        sPoint.y = 234;
        ePoint.x = 300;
        ePoint.y = 4000;
        System.out.println(line);
    }
}
