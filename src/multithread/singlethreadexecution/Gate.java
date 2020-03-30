package multithread.singlethreadexecution;

public class Gate {

    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    //synchronized 키워드를 통해 쓰레드 세이프한 메서드가 되었다.
    /*public synchronized void pass(String name, String address){
        this.counter++;
        this.name = name;
        this.address = address;
        check();
    }*/

    //critical section 을 길게 만들면 에러 검출 가능성을 높일 수 있다.
    public void pass(String name, String address){
        this.counter++;
        this.name = name;
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("***CRITICAL SECTION***");
        }
        this.address = address;
        check();
    }

/*    @Override
    public synchronized String toString() {
        //toString 메서드 또한 필드를 참조하는 메서드이며
        // 참조하는 필드들의 값의 일관성이 중요하므로 synchronized 키워드.
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    private void check(){
        if(name.charAt(0) != address.charAt(0)){
            System.out.println("****BROKEN**** " + toString());
        }
    }
}
