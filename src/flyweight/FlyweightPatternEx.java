package flyweight;

public class FlyweightPatternEx {

    public static void main(String[] args) {
        RobotFactory myFactory = new RobotFactory();
        System.out.println("*** Flyweight pattern example***");

        try {
            IRobot shape = myFactory.GetRobotFromFactory("small");
            shape.Print();

            //두 번 더 생성을 시도한다. => 결과 : 실패.
            for(int i = 0; i < 2; i++){
                shape = myFactory.GetRobotFromFactory("small");
                shape.Print();
            }

            int NumOfDistinctRobots = myFactory.TotalObjectsCreated();
            System.out.println("Distinct Robots created : " + NumOfDistinctRobots);

            //large robot 을 다섯 번 생성 시도한다. 두 번 째 시도부터 만들어지지 않는다.
            for(int i = 0; i < 5; i++){
                shape = myFactory.GetRobotFromFactory("large");
                shape.Print();
            }

            NumOfDistinctRobots = myFactory.TotalObjectsCreated();
            System.out.println("Final number of distinct robots created : " + NumOfDistinctRobots);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
