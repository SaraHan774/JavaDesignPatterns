package observer;

import java.util.Observable;
import java.util.Observer;

class CommentaryObjectObservable extends Observable implements Commentary{

    private String desc;
    private final String subjectDetails;

    public CommentaryObjectObservable(String subjectDetails){
        this.subjectDetails = subjectDetails;
    }

    @Override
    public void setDescription(String desc) {
        this.desc = desc;
        setChanged();
        notifyObservers(desc);
    }

    public String getSubjectDetails(){
        return subjectDetails;
    }
}

class SMSUsersObserver implements Observer{

    private String desc;
    private final String userInfo;
    private final Observable observable;

    public SMSUsersObserver(Observable observable, String userInfo){
        this.observable = observable;
        this.userInfo = userInfo;
    }

    @Override
    public void update(Observable o, Object arg) {
        desc = (String) arg;
        display();
    }

    private void display(){
        System.out.println("[ " + userInfo + "] : " + desc);
    }

    public void subscribe(){
        System.out.println("Subscribing " + userInfo + " to " +
                ((CommentaryObjectObservable)(observable)).getSubjectDetails()
        );
        this.observable.addObserver(this);
        System.out.println("Subscribed Successfully");
    }

    public void unSubscribe(){
        System.out.println("Unsubscribing " + userInfo + " to " +
                ((CommentaryObjectObservable)(observable)).getSubjectDetails()
        );
        this.observable.deleteObserver(this);
    }
}

public class BuiltInObserverEx {

    public static void main(String[] args) {
        CommentaryObjectObservable observable = new CommentaryObjectObservable("Soccer Match [2019AUG04]");
        SMSUsersObserver observer = new SMSUsersObserver(observable, "Adam Warner [New York]");
        SMSUsersObserver observer2 = new SMSUsersObserver(observable, "Tim Ronney [London]");

        observer.subscribe();
        observer2.subscribe();
        observable.setDescription("Welcome to live soccer match");
        observable.setDescription("Current Score 0-0");

        observer.unSubscribe();
        observable.setDescription("It's a goal !!!");
        observable.setDescription("Current score 1-0");

    }
}
