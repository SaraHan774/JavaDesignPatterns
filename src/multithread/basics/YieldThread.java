package multithread.basics;


//스레드가 처리하는 작업은 반복문을 포함하는 경우가 많다.
class ThreadAnonymous extends Thread{
    private boolean flag;

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        while(true){
            //flag 가 false 가 되면 while 문에서는 어떠한 작업도 처리하지 않는다.
            //동작을 하지 않는 스레드는 실행 대기 상태로 가는 것이 전체 프로그램
            //성능에 도움이 된다.
            if(flag){
                System.out.println("Thread A is working ... ");
            }
        }
    }
}
class ThreadB extends Thread{

    private boolean stop = false;
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        while(!stop){
            if(flag){
                System.out.println("Thread B is working .... ");
            }else{
                //flag 가 false 가 되면 다른 스레드에게 실행을 양보한다.
                Thread.yield();
            }
        }
    }
}

class ThreadA extends Thread{

    private boolean stop = false;
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        while(!stop){
            if(flag){
                System.out.println("Thread A is working .... ");
            }else{
                //flag 가 false 가 되면 다른 스레드에게 실행을 양보한다.
                Thread.yield();
            }
        }
    }
}



public class YieldThread {

    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        threadA.start();
        threadB.start();

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //Thread B 만 실행한다.
        threadA.setFlag(false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Thread A 만 실행한다.
        threadA.setFlag(true);
        threadB.setFlag(false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Thread 를 종료한다.
        threadA.setStop(true);
        threadB.setStop(true);
    }
}
