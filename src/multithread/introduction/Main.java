package multithread.introduction;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class MyThread extends Thread{
    @Override
    public void run() {
        //새로 기동되는 쓰레드의 동작을 run 메서드에 기술한다.
        System.out.println("MY NEW THREAD");
        //run 메서드가 종료되면 쓰레드도 종료된다.
    }
}


class Bank{
    private int money;
    private String name;

    public Bank(int money, String name){
        this.name = name;
        this.money = money;
    }

    public synchronized void deposit(int money){
        this.money += money;
    }

    public synchronized boolean withdraw(int money){
        if(this.money >= money){
            this.money -= money;
            return true;
        }else{
            return false;
        }
    }

    public String getName() {
        return name;
    }
}


public class Main {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        //start 호출 -> 새로운 스레드 생성 -> 생성된 스레드가 run 메소드 호출
        for (int i = 0; i < 3; i++) {
            System.out.println("MAIN THREAD");
        }

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Thread Factory!");
            }
        }).start();
    }
/* 실행 결과
MAIN THREAD
MAIN THREAD
MAIN THREAD
MY NEW THREAD
*/
}
