package multithread.basics;

import java.awt.*;

public class SleepThread {

    public static void main(String[] args) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 10; i++) {
            toolkit.beep(); //윈도우 에서는 뜨르롱 ~ 소리 나는 것
            System.out.println("Wait for 3 seconds ...");
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                //주어진 시간이 되기 전에 interrupt() 메서드가 호출되는 경우
                e.printStackTrace();
            }
        }
    }
}
