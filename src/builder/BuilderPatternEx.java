package builder;

import com.sun.deploy.ui.ProgressDialog;

import java.util.LinkedList;

interface IBuilder{
    void BuildBody();
    void InsertWheels();
    void AddHeadLights();
    Product GetVehicle();

}

class Product {

    private LinkedList<String> parts;

    public Product(){
        parts = new LinkedList<>();
    }

    public void Add(String part){
        //adding parts
        parts.addLast(part);
    }

    public void Show(){
        System.out.println("\n Product Completed as Below");
        for (int i = 0; i < parts.size(); i++) {
            System.out.println(parts.get(i));
        }
    }
}

class Car implements IBuilder{

    private Product product = new Product();

    @Override
    public void BuildBody() {
        product.Add("This is a body of a Car");
    }

    @Override
    public void InsertWheels() {
        product.Add("4 Wheels are added");
    }

    @Override
    public void AddHeadLights() {
        product.Add("2 Headlights are added");
    }

    @Override
    public Product GetVehicle() {
        return product;
    }
}

class MotorCycle implements IBuilder{

    private Product product = new Product();

    @Override
    public void BuildBody() {
        product.Add("This is a body of a Motor Cycle");
    }

    @Override
    public void InsertWheels() {
        product.Add("2 wheels are added");
    }

    @Override
    public void AddHeadLights() {
        product.Add("1 headlights are added");
    }

    @Override
    public Product GetVehicle() {
        return product;
    }
}


class Director{
    IBuilder myBuilder;

    public void Construct(IBuilder builder){
        myBuilder = builder;
        myBuilder.BuildBody();
        myBuilder.InsertWheels();
        myBuilder.AddHeadLights();
    }
}



public class BuilderPatternEx {


    public static void main(String[] args) {

        System.out.println("*** Builder Pattern Demo ***");

        Director director = new Director();

        IBuilder carBuilder = new Car();
        IBuilder motorBuilder = new MotorCycle();

        //making car
        director.Construct(carBuilder);
        Product car = carBuilder.GetVehicle();
        car.Show();

        //making motor cycle
        director.Construct(motorBuilder);
        Product motorCycle = motorBuilder.GetVehicle();
        motorCycle.Show();
        
    }

}
