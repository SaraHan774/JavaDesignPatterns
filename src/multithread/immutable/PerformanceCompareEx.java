package multithread.immutable;

public class PerformanceCompareEx {

    public static final long CALL_OUT = 1000_000_000L;

    public static void main(String[] args) {

        trial("Not Synchronized", CALL_OUT, new NotSync());
        trial("Synchronized", CALL_OUT, new Sync());
    }

    private static void trial(String msg, long count, Object object){
        System.out.println(msg + " : BEGIN ");
        long start_time = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            object.toString();
        }

        System.out.println(msg + " : END ");
        System.out.println("Elapsed Time = " + (System.currentTimeMillis() -start_time) + "ms");
    }
}

class NotSync{
    private final String name = "NotSync";

    @Override
    public String toString() {
        return "NotSync{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Sync{
    private final String name = "Sync";

    @Override
    public synchronized String toString() {
        return "Sync{" +
                "name='" + name + '\'' +
                '}';
    }
}
