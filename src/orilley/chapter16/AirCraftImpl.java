package orilley.chapter16;

public class AirCraftImpl extends AirCraft {

    public AirCraftImpl(ATCMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " SENT = " + message);
        atcMediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " RECEIVED = " + message);
    }
}
