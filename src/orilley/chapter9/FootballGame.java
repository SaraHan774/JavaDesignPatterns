package orilley.chapter9;

public class FootballGame extends Game {

    @Override
    void initialize() {
        System.out.println("init football game");
    }

    @Override
    void startPlay() {
        System.out.println("start football game");
    }

    @Override
    void endPlay() {
        System.out.println("end football game");
    }
}
