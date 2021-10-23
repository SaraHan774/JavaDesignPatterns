package orilley.chapter16;

public class Main {
    // mediator pattern
    public static void main(String[] args) {
        ATCMediator mediator = new ATCMediatorImpl();

        // create a few air crafts
        AirCraft airCraft1 = new AirCraftImpl(mediator, "Boing277");
        AirCraft airCraft2 = new AirCraftImpl(mediator, "Hellicopter123");
        AirCraft airCraft3 = new AirCraftImpl(mediator, "AircraftRandom123");

        // adding to mediator
        mediator.addAircraft(airCraft1);
        mediator.addAircraft(airCraft2);
        mediator.addAircraft(airCraft3);

        // send message
        airCraft1.send("hello from 1");
    }
}
