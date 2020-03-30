package multithread.singlethreadexecution;

public class SecurityGate {
    //정답 : 안전하지 않은 클래스이다.
    private int counter;

    //이 클래스를 직접 테스트 하기 위해서 메서드를 아래와 같이 수정한다.
    //Counter 가 증감할 때 Thread.yield 를 호출하도록 한다.
    public synchronized void enter(){
        int currentCounter = counter;
        Thread.yield();
        counter = currentCounter + 1;
    }

    public synchronized void exit(){
        int currentCounter = counter;
        Thread.yield();
        counter = currentCounter + 1;
    }

    public synchronized int getCounter() {
        return counter;
    }

}


