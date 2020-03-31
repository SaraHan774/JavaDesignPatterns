package multithread.immutable;

final class Person{
    private final String name;
    private final String address;

    public Person(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class PrintPersonThread extends Thread{

    private Person person;

    public PrintPersonThread(Person person){
        this.person = person;
    }

    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }
    }
}

public class ImmutableEx {

    public static void main(String[] args) {
        Person alice = new Person("Alice", "Alaska");

        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();

    }
}
