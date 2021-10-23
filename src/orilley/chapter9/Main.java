package orilley.chapter9;

public class Main {
    public static void main(String[] args) {
        Game endless = new EndlessRunnerGame();
        Game football = new FootballGame();

        endless.play();
        football.play();
    }
}
