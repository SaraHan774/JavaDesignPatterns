package adapter;

public class AdapterPattern {

    public static void main(String[] args) {
        System.out.println(" === Adapter Pattern Demo === ");
        CalculatorAdapter calculatorAdapter = new CalculatorAdapter();
        Triangle triangle = new Triangle(12, 20);
        System.out.println("\n Example \n");
        System.out.println("Area of the triangle is "+ calculatorAdapter.getArea(triangle));
    }
}
