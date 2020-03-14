package state;

abstract class RemoteControl{

    public abstract void pressSwitch(TV context);

}

class Off extends  RemoteControl{

    @Override
    public void pressSwitch(TV context) {
        System.out.println("I am off. Going to be On now");
        context.setState(new On());
    }
}

class On extends RemoteControl{

    @Override
    public void pressSwitch(TV context) {
        System.out.println("I am already On. Going to be Off now");
        context.setState(new Off());
    }
}

class TV{

    private RemoteControl state;

    public RemoteControl getState(){
        return state;
    }

    public void setState(RemoteControl state){
        this.state = state;
    }

    public TV(RemoteControl state){
        this.state = state;
    }

    public void pressButton(){
        state.pressSwitch(this);
    }
}

public class StatePatternEx {

    public static void main(String[] args) {
        System.out.println("*** State Pattern Demo ***");
        //초기에는 TV가 off 인 상태이다.
        Off initialState = new Off();
        TV tv = new TV(initialState);
        //처음 누른 경우
        tv.pressButton();
        //두번 째 누른 경우
        tv.pressButton();
    }
}
