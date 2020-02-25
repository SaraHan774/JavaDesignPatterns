package flyweight.modified;

import java.util.HashMap;
import java.util.Map;

public class RobotFactory {

    Map<String, IRobot> shapes = new HashMap<>();

    public int TotalObjectsCreated(){
        return shapes.size();
    }

    public IRobot GetRobotFromFactory(String robotType) throws Exception{
        IRobot robotCategory = null;

        if(shapes.containsKey(robotType)){
            robotCategory = shapes.get(robotType);
        }else{
            switch (robotType){
                case "king" :
                    System.out.println("We do not have king robot. we are creating a king robot now.");
                    robotCategory = new Robot("king");
                    shapes.put("king", robotCategory);
                    break;
                case "queen" :
                    System.out.println("we do not have queen robot. so we are creating a queen robot now.");
                    robotCategory = new Robot("queen");
                    shapes.put("queen", robotCategory);
                    break;
                default:
                    throw new Exception("Robot Factory can create only king and queen type robots.");
            }
        }
        return robotCategory;
    }
}
