package orilley.chapter16;

public abstract class AirCraft {
    protected ATCMediator atcMediator; // focused place of communication
    protected String name;

    public AirCraft(ATCMediator mediator, String name) {
        this.atcMediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}
