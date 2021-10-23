package orilley.chapter1;

import orilley.chapter1.model.Balloon;
import orilley.chapter1.model.Clown;
import orilley.chapter1.model.SquareBalloon;

public class Strategy {

    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard();
        System.out.println("balloon tap score : ");
        scoreBoard.algorithmBase = new Balloon(); // balloon 의 calculate score 를 사용한다
        scoreBoard.showScore(10, 5); // showScore 은 한군데에 있지만 각기 다른 base 에 대해서 다른 결과를 보여준다.

        System.out.println("clown tap score : ");
        scoreBoard.algorithmBase = new Clown();
        scoreBoard.showScore(10, 5);

        System.out.println("square balloon tap score : ");
        scoreBoard.algorithmBase = new SquareBalloon();
        scoreBoard.showScore(10, 5);

        // 각 클래스마다 showScore 를 오버라이딩 하지 않고도, interface 를 통해서 다른 것을 적용할 수 있다. (전략적으로?)
    }

}
