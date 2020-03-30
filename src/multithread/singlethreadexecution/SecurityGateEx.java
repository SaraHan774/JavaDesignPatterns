package multithread.singlethreadexecution;

public class SecurityGateEx {

    public static void main(String[] args) {

        System.out.println("*** Testing SecurityGate *** ");

        for (int trial = 0; true; trial++) {
            SecurityGate securityGate = new SecurityGate();
            CrackerThread [] threads = new CrackerThread[5];

            //CrackerThread 기동
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new CrackerThread(securityGate);
                threads[i].start();
            }

            //CrackerThread 종료 대기
            for (int i = 0; i < threads.length; i++) {
                try{
                    threads[i].join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            //카운터 값 확인하기
            if(securityGate.getCounter() == 0){
                System.out.println(".");
            }else{
                System.out.println("Security Gate is NOT safe!");
                System.out.println("getCounter() == " + securityGate.getCounter());
                System.out.println("trial = " + trial);
                break;
            }
        }


    }
}
