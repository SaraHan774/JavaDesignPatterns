package multithread.singlethreadexecution;

import javax.swing.*;

public class UserThread extends Thread{

    //각 필드는 생성자로 초기화된 후 다시 대입하지 않으므로 final 선언한다
    //final 선언하면 잘못해서 다시 대입하는 코드를 적더라도 컴파일 타임에 검출됨.
    //필드 선언 부분에 초기화 하지 않고 생성자로 초기화 하는 것 => blank final

    private final Gate gate;
    private final String myName;
    private final String myAddress;

    public UserThread(Gate gate, String myName, String myAddress){
        this.gate = gate;
        this.myName = myName;
        this.myAddress = myAddress;
    }

    @Override
    public void run() {
        System.out.println(myName + " BEGIN");
        while(true){
            gate.pass(myName, myAddress);
        }
    }
}
