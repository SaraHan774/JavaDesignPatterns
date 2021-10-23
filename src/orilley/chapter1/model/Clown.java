package orilley.chapter1.model;

import orilley.chapter1.ScoreAlgorithmBase;

public class Clown extends ScoreAlgorithmBase {
    @Override
    public int calculateScore(int taps, int multiplier) {
        return (taps * multiplier) - 10;
    }
}
