package observer;


import java.util.Date;
import java.util.Observable;
import java.util.Observer;


class MovieInfo{

    private String title;
    private Date date;

    public MovieInfo(String title, Date date){
        this.title = title;
        this.date = date;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}

class MovieObservable extends Observable{
    private MovieInfo movieInfo;
    private String subscriptionDetails;

    public MovieObservable(String desc){
        this.subscriptionDetails = desc;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
        setChanged();
        notifyObservers(movieInfo.toString());
    }

    public String getSubscriptionDetails() {
        return subscriptionDetails;
    }

}

class Customers implements Observer {

    private String desc;
    private final String customerInfo;
    private final Observable observable;

    public Customers(String customerInfo, Observable observable){
        this.customerInfo = customerInfo;
        this.observable = observable;
    }

    @Override
    public void update(Observable o, Object arg) {
        desc = (String)arg;
        display();
    }

    private void display(){
        System.out.println(customerInfo + " : " + desc);
    }

    public void subscribe(){
        System.out.println("Subscribing " + customerInfo + " to " +
                ((MovieObservable)observable).getSubscriptionDetails());
        this.observable.addObserver(this);
        System.out.println("Subscribed !");
    }

    public void unSubscribe(){
        System.out.println("UnSubscribing " + customerInfo + " to " +
                ((MovieObservable)observable).getSubscriptionDetails());
        this.observable.deleteObserver(this);
        System.out.println("UnSubscribed !");
    }
}

public class ObserverPatternPractice {

    public static void main(String[] args) {
        MovieObservable movieObservable = new MovieObservable("Trending Movies");

        Customers customer1 = new Customers("James", movieObservable);
        Customers customer2 = new Customers("Dave", movieObservable);

        customer1.subscribe();
        customer2.subscribe();

        movieObservable.setMovieInfo(new MovieInfo("The Great Gatsby", new Date()));

        customer1.unSubscribe();

        movieObservable.setMovieInfo(new MovieInfo("The Parasite", new Date()));
    }
}
