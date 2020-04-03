package multithread.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GuardedSuspensionEx {

    public static void main(String[] args) {

        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ServerThread(requestQueue, "Bobby", 6535897L).start();
    }

}

class Request{
    private final String name;
    public Request(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}

//get request, put request 를 위한 클래스
class RequestQueue{
    private final Queue<Request> queue = new LinkedList<>();

    public synchronized Request getRequest(){
        //가져올 때 큐가 비어있다면, put 에서 notify 할때까지 기다린다.
        while(queue.peek() == null){
            System.out.println("Queue is empty");
            try{
                //다른 스레드가 notify 를 하기 전까지 해당 스레드를 대기시킨다.
                wait();
                //아래의 putRequest 가 실행되면 notifyAll 이 불려서 getRequest 메서드를
                //빠져나갈 수 있다.
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("returning ... ");
        return queue.remove();
    }

    public synchronized Request getRequestTryCatchOutside(){
            try{
                while(queue.peek() == null){
                    wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        return queue.remove();
    }


    public synchronized void putRequest(Request request){
        queue.offer(request); //큐에 요청 객체를 넣고
        notifyAll(); //대기중이던 스레드들을 깨운다.
    }
}


//Request 를 생성하고 요청하는 Client 클래스
class ClientThread extends Thread{
    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed){
        super(name);
        //아래의 생성자를 이용해서 name 이라는 매개변수로 스레드의 이름을 지정하며 생성한다.
        //    public Thread(String name) {
        //        init(null, null, name, 0);
        //    }
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
        //request 를 put 하는 타이밍에 변화를 주기위한 랜덤 값
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
class ServerThread extends Thread{

    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name, long seed){
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequestTryCatchOutside(); //큐에서 요청을 가져와서
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
