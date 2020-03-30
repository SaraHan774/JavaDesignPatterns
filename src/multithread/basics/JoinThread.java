package multithread.basics;


public class JoinThread {

    //다른 스레드가 종료되는 것을 기다렸다가 실행해야 하는 경우
    //계산 작업 -> 작업 결과 반환받는 경우
    //Thead.join() 을 사용한다.
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        System.out.println("STATE (1) : " + sumThread.getState());
        sumThread.start();

        try{
            //Main 쓰레드는 SumThread 가 작업을 마칠때까지 일시 정시 상태에 있다가
            //계산을 마치면 결과를 받는다.
            System.out.println("STATE (2) : " + sumThread.getState());
            sumThread.join();
            System.out.println("STATE (3) : " + sumThread.getState());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("SUM RESULT : " + sumThread.getSum());
    }

/*
STATE (1) : NEW
STATE (2) : RUNNABLE
STATE (3) : TERMINATED
SUM RESULT : 500500
*/
}


class SumThread extends Thread{
    private long sum;

    public long getSum() {
        return sum;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            sum += i;
        }
    }
}