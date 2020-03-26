package proxy.ProxyClasses;

import proxy.OriginalClasses.ConcreteSubject;
import proxy.OriginalClasses.Subject;

public class Proxy extends Subject {

    ConcreteSubject cs;

    @Override
    public void doSomeWork() {
        System.out.println("Proxy call happening now");

        //lazy initialization
        if(cs == null){
            cs = new ConcreteSubject();
        }
        cs.doSomeWork();
    }
}
