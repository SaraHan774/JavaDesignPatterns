package multithread.immutable;




public class Line {

    private final Point startPoint;
    private final Point endPoint;

    public Line(int startX, int startY, int endX, int endY){
        this.startPoint = new Point(startX, startY);
        this.endPoint = new Point(endX, endY);
    }

    public Line(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public int getStartX(){
        return startPoint.getX();
    }
    public int getStartY(){
        return startPoint.getY();
    }

    public int getEndX(){
        return endPoint.getX();
    }
    public int getEndY(){
        return endPoint.getY();
    }

    @Override
    public String toString() {
        return "Line{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}

class Point{
    public int x;
    public int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
