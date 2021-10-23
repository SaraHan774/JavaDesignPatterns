package orilley.chapter16;

public interface ATCMediator {
    void sendMessage(String message, AirCraft airCraft);
    void addAircraft(AirCraft airCraft);
}
