package multithread.guardedsuspension;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingTest {

    public static void main(String[] args) {
        RequestQueueBlocked requestQueueBlocked = new RequestQueueBlocked();
        new ClientThreadBlocked(requestQueueBlocked, "Dave", 234234L).start();
        new ServerThreadBlocked(requestQueueBlocked, "John", 567435L).start();
    }
}

class RequestQueueBlocked{
    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Request getRequest(){
        Request request = null;
        try{
            request = queue.take();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return request;
    }

    public void putRequest(Request request){
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



//Request 를 생성하고 요청하는 Client 클래스
class ClientThreadBlocked extends Thread{
    private final Random random;
    private final RequestQueueBlocked requestQueue;

    public ClientThreadBlocked(RequestQueueBlocked requestQueue, String name, long seed){
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("NO. " + i);
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            requestQueue.putRequest(request);

            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

//Client 가 요청한 것들을 큐에서 꺼내 처리하는 클래스
class ServerThreadBlocked extends Thread{

    private final Random random;
    private final RequestQueueBlocked requestQueue;

    public ServerThreadBlocked(RequestQueueBlocked requestQueue, String name, long seed){
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest(); //큐에서 요청을 가져와서
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}