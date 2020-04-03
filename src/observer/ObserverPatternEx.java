package observer;


import java.util.ArrayList;
import java.util.List;

interface Subject{
    void subscribeObserver(Observer observer);
    void unSubscribeObserver(Observer observer);
    void notifyObservers();
    String subjectDetails();
}

interface Observer{
    void update(String desc);
    void subscribe();
    void unSubscribe();
}

interface Commentary{
    void setDescription(String desc);
}

class CommentaryObject implements Subject, Commentary{

    private final List<Observer> observerList;
    private String desc;
    private final String subjectDetails;

    public CommentaryObject(List<Observer> observerList, String subjectDetails){
        this.observerList = observerList;
        this.subjectDetails = subjectDetails;
    }

    @Override
    public void subscribeObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unSubscribeObserver(Observer observer) {
        int index = observerList.indexOf(observer);
        observerList.remove(index);
    }

    @Override
    public void notifyObservers() {
        System.out.println();
        for (Observer observer : observerList){
            observer.update(desc);
        }
    }

    @Override
    public String subjectDetails() {
        return subjectDetails;
    }

    @Override
    public void setDescription(String desc) {
        this.desc = desc;
        notifyObservers();
    }
}

class SMSUsers implements Observer{

    private final Subject subject;
    private String desc;
    private String userInfo;

    public SMSUsers(Subject subject, String userInfo){
        if(subject == null){
            throw new IllegalArgumentException("No publisher found");
        }
        this.subject = subject;
        this.userInfo = userInfo;
    }

    @Override
    public void update(String desc) {
        this.desc = desc;
        display();
    }

    private void display(){
        System.out.println("[" + userInfo + "] : " + desc);
    }

    @Override
    public void subscribe() {
        System.out.println("Subscribing " + userInfo + " to " + subject.subjectDetails());
        this.subject.subscribeObserver(this);
        System.out.println("Subscribed Successfully");
    }

    @Override
    public void unSubscribe(){
        System.out.println("UNSubscribing " + userInfo + " to " + subject.subjectDetails());
        this.subject.unSubscribeObserver(this);
        System.out.println("UNSubscribed Successfully");
    }
}

public class ObserverPatternEx {
    public static void main(String[] args) {

        Subject subject = new CommentaryObject(new ArrayList<Observer>(), "Soccer Match [2014AUG24]");
        Observer observer = new SMSUsers(subject, "Adam Warner [New York]");
        observer.subscribe();
        Observer observer2 = new SMSUsers(subject, "Tim Ronney [London]");

        observer2.subscribe();
        Commentary cObject = ((Commentary)subject);
        cObject.setDescription("Welcome to live Soccer match");
        cObject.setDescription("Current score 0-0");

        System.out.println();
        observer2.unSubscribe();
        System.out.println();
        cObject.setDescription("It’s a goal!!");
        cObject.setDescription("Current score 1-0");
        System.out.println();

        Observer observer3 = new SMSUsers(subject, "Marrie [Paris]");
        observer3.subscribe();
        System.out.println();
        cObject.setDescription("It’s another goal!!");
        cObject.setDescription("Half-time score 2-0");

    }
}
