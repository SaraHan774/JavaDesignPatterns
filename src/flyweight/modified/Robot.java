package flyweight.modified;

public class Robot implements IRobot{

    private String robotType;
    public String colorOfRobot;

    public Robot(String robotType){
        this.robotType = robotType;
    }

    public void setColor(String colorOfRobot) {
        this.colorOfRobot = colorOfRobot;
    }

    @Override
    public void Print() {
        System.out.println("This is a robot type " + robotType + " type robot with " + colorOfRobot + " color");
    }
}
