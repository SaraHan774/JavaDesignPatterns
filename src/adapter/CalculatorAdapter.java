package adapter;


public class CalculatorAdapter {

    Calculator calculator;
    Triangle triangle;

    public double getArea(Triangle triangle){
        calculator = new Calculator();
        this.triangle = triangle;
        Rect rect = new Rect();

        rect.length = triangle.base;
        rect.width = 0.5 * triangle.height;

        return calculator.getArea(rect);
    }
}
