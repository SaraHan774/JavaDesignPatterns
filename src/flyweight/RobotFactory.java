package flyweight;

import java.util.HashMap;
import java.util.Map;

public class RobotFactory {

    Map<String, IRobot> shapes = new HashMap<>();

    public int TotalObjectsCreated(){
        return shapes.size();
    }

    public IRobot GetRobotFromFactory(String RobotCategory) throws Exception{

        IRobot robotCategory = null;

        if(shapes.containsKey(RobotCategory)){
            //이미 존재한다면 존재하는 객체를 반환하고, 새로 생성하지 않는다.
            robotCategory = shapes.get(RobotCategory);
        }else{
            switch (RobotCategory){
                case "small":
                    System.out.println("We do not have small robot. So we are creating a small robot now.");
                    robotCategory = new SmallRobot();
                    shapes.put("small", robotCategory);
                    break;
                case "large":
                    System.out.println("We do not have large robot. So we are creating a large robot now.");
                    robotCategory = new LargeRobot();
                    shapes.put("large", robotCategory);
                    break;
                default:
                    throw new Exception("Robot factory can create only small and large shapes.");
            }
        }
        return robotCategory;
    }
}
