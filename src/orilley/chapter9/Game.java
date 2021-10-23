package orilley.chapter9;

public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    // template method !
    // final 로 만드는 이유 : 서브 클래스가 이를 망치지 않도록
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}
