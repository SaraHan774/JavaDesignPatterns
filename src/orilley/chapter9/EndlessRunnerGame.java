package orilley.chapter9;

public class EndlessRunnerGame extends Game {
    @Override
    void initialize() {
        System.out.println("endless runner initializing ... ");
    }

    @Override
    void startPlay() {
        System.out.println("endless runner starting ... ");
    }

    @Override
    void endPlay() {
        System.out.println("endless runner ending ... ");
    }

    // add more methods ...
}
