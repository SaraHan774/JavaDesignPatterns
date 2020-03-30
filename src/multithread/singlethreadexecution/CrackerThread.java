package multithread.singlethreadexecution;

public class CrackerThread extends Thread{

    private final SecurityGate securityGate;

    public CrackerThread(SecurityGate securityGate){
        this.securityGate = securityGate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            securityGate.enter();
            securityGate.exit();
        }
    }
}
