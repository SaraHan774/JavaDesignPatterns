package multithread.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;

public class Testings {

    public static void main(String[] args) {
        ReqQueue reqQueue1 = new ReqQueue();
        ReqQueue reqQueue2 = new ReqQueue();

        reqQueue1.putRequest(new Request("Hello!"));
        new TalkThread(reqQueue1, reqQueue2, "TALK1").start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new TalkThread(reqQueue2, reqQueue1, "TALK2").start();

    }
}

class TalkThread extends Thread{

    private final ReqQueue reqQueue1;
    private final ReqQueue reqQueue2;

    public TalkThread(ReqQueue reqQueue1, ReqQueue reqQueue2, String name){
        super(name);
        this.reqQueue1 = reqQueue1;
        this.reqQueue2 = reqQueue2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : BEGIN ");
        Request request1;
        Request request2;
        for (int i = 0; i < 20; i++) {
            //상대로부터 리퀘스트를 받는다
            request1 = reqQueue1.getRequest();
            System.out.println(Thread.currentThread().getName() + " gets " + request1);
            //느낌표를 붙여서 상대방에게 돌려준다.
            request2 = new Request(request1.getName() + "!");
            System.out.println(Thread.currentThread().getName() + " puts " + request2);

            reqQueue2.putRequest(request2);
        }
        System.out.println(Thread.currentThread().getName() + " : END ");
    }
}

class ReqQueue{

    private final Queue<Request> requestQueue = new LinkedList<>();

    public synchronized Request getRequest(){
        while(requestQueue.peek() == null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return requestQueue.remove();
    }

    public synchronized void putRequest(Request request){
        requestQueue.offer(request);
        notifyAll();
    }
}