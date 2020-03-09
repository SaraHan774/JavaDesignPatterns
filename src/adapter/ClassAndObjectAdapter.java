package adapter;


interface  IIntegerValue{
    public int getInteger();
}

class IntegerValue implements IIntegerValue{

    @Override
    public int getInteger() {
        return 5;
    }
}

class ClassAdapter extends  IntegerValue{
    public int getInteger(){
        //다중 상속 처럼 보이는 것
        return 2 + super.getInteger();
    }
}

class ObjectAdapter{

    private IIntegerValue myInt;

    public ObjectAdapter(IIntegerValue myInt){
        this.myInt = myInt;
    }

    public int getInteger(){
        return 2 + this.myInt.getInteger();
    }
}

public class ClassAndObjectAdapter {

    public static void main(String[] args) {
        System.out.println(" === Class and Object Adapter Demo === ");

        ClassAdapter classAdapter = new ClassAdapter();
        System.out.println("Class Adapter is returning : " + classAdapter.getInteger());

        ClassAdapter classAdapter1 = new ClassAdapter();
        ObjectAdapter objectAdapter = new ObjectAdapter(new IntegerValue());
        System.out.println("Object Adapter is returning : " + objectAdapter.getInteger());
    }
}
