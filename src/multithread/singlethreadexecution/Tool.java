package multithread.singlethreadexecution;

public class Tool {

    private final String name;

    public Tool(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "name='" + name + '\'' +
                '}';
    }
}
