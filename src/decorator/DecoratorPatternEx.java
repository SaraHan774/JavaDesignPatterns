package decorator;

abstract class Component{
    public abstract void doJob();
}

class ConcreteComponent extends Component{
    @Override
    public void doJob() {
        System.out.println("I am concrete component. " +
                "I am closed for modification");
    }
}

abstract class AbstractDecorator extends Component{
    protected Component component;
    public void SetTheComponent(Component c){
        component = c;
    }

    @Override
    public void doJob() {
        if(component != null){
            component.doJob();
        }
    }
}

class ConcreteDecoratorEx_1 extends AbstractDecorator{
    @Override
    public void doJob() {
        super.doJob();
        //add additional thing if necessary
        System.out.println("I am explicitly from Ex_1");
    }
}

class ConcreteDecoratorEx_2 extends AbstractDecorator{
    @Override
    public void doJob() {
        super.doJob();
        System.out.println("I am explicitly from Ex_2");
        System.out.println("END Ex_2");
    }
}

public class DecoratorPatternEx {

    public static void main(String[] args) {
        System.out.println("*** Decorator Pattern Demo ***");

        ConcreteComponent concreteComponent = new ConcreteComponent();
        ConcreteDecoratorEx_1 ex_1 = new ConcreteDecoratorEx_1();

        //decorating concrete component
        ex_1.SetTheComponent(concreteComponent);
        ex_1.doJob();

        ConcreteDecoratorEx_2 ex_2 = new ConcreteDecoratorEx_2();
        ex_2.SetTheComponent(ex_1);
        ex_2.doJob();

    }
}
