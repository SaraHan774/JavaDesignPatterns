package flyweight.modified;

import java.util.Random;

public class FlyweightPatternModifiedEx {

    public static void main(String[] args) {

        RobotFactory myFactory = new RobotFactory();
        System.out.println("*** Flyweight pattern example modified ***");
        //king 타입을 세 개 생성 시도

        Robot shape;


            try {
                for(int i = 0; i < 3; i++){
                shape = (Robot) myFactory.GetRobotFromFactory("king");
                shape.setColor(getRandomColor());
                shape.Print();
                }

                //3개의 queen 타입을 생성 시도
                for(int i = 0; i < 3; i++){
                    shape = (Robot) myFactory.GetRobotFromFactory("queen");
                    shape.setColor(getRandomColor());
                    shape.Print();
                }

                int NumOfDistinctRobots = myFactory.TotalObjectsCreated();
                System.out.println("Distinct robots created : " + NumOfDistinctRobots);


            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private static String getRandomColor() {
        Random r = new Random();
        int random = r.nextInt();
        if(random %2 ==0){
            return "red";
        }else{
            return "green";
        }
    }

}
