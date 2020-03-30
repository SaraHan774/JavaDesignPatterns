package multithread.singlethreadexecution;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Log{
    public static void println(String s){
        System.out.println(Thread.currentThread().getName() + " : " + s);
    }
}

//수의 제한이 있는 리소스
class BoundedResource{
    private final Semaphore semaphore;
    private final int permits; //리소스의 수
    private final static Random RANDOM = new Random(314159);

    public BoundedResource(int permits){
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    public void use() throws InterruptedException{
        semaphore.acquire();
        try{
            doUse();
        }finally {
            semaphore.release(); //해제를 보장하기 위해 finally 에서 실행한다.
        }
    }

    protected void doUse() throws InterruptedException{
        Log.println("BEGIN : used = " + (permits - semaphore.availablePermits()));
        Thread.sleep(RANDOM.nextInt(500));
        Log.println("END : used = " + (permits - semaphore.availablePermits()));
    }
}

class SemUserThread extends Thread{
    private final static Random RANDOM = new Random(26535);
    private final BoundedResource boundedResource;

    public SemUserThread(BoundedResource boundedResource){
        this.boundedResource = boundedResource;
    }

    @Override
    public void run() {
        try{
            while(true){
                boundedResource.use();
                Thread.sleep(RANDOM.nextInt(3000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class SemaphoreEx {

    public static void main(String[] args) {
        BoundedResource boundedResource = new BoundedResource(3);

        //10개의 쓰레드가 사용한다.
        for (int i = 0; i < 10; i++) {
            new SemUserThread(boundedResource).start();
        }

    }
}
