package multithread.immutable;

public class MutableToImmutable {

    public static void main(String[] args) {

    }
}

final class MutablePerson{
    private String name;
    private String address;

    public MutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public MutablePerson(ImmutablePerson person){
        this.name = person.getName();
        this.address = person.getAddress();
    }

    public synchronized void setPerson(String newName, String newAddress){
        this.name = newName;
        this.address = newAddress;
    }

    public synchronized ImmutablePerson getImmutablePerson(){
        return new ImmutablePerson(this);
    }

    public String getName() {
        //called only by immutable person
        return name;
    }

    public String getAddress() {
        //called only by immutable person
        return address;
    }

    @Override
    public synchronized String toString() {
        return "MutablePerson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

final class ImmutablePerson{
    private final String name;
    private final String address;

    public ImmutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public ImmutablePerson(MutablePerson person){
        //여러개의 스레드를 만들어서 각 스레드에서 MutablePerson 객체를 통해 Immutable Person 객체를
        //만들기를 시도하는 경우 person 인자값에 다른 스레드가 접근할 수 있게 되어서 getter 의
        //결과가 안전하지 않게 된다.
        this.name = person.getName();
        this.address = person.getAddress();
    }

    public MutablePerson getMutablePerson(){
        return new MutablePerson(this);
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}