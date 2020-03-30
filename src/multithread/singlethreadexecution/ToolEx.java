package multithread.singlethreadexecution;

public class ToolEx {

    public static void main(String[] args) {

        System.out.println("*** Testing Eater Thread ***");

        Tool spoon = new Tool("spoon");
        Tool fork = new Tool("fork");

        new EaterThread("Alice", spoon, fork).start();
        new EaterThread("Bobby", fork, spoon).start();
    }
}
