package proxy.demo;

import proxy.ProxyClasses.Proxy;

public class ProxyPatternEx {

    public static void main(String[] args) {

        System.out.println("*** Proxy Pattern Demo *** ");
        Proxy proxy = new Proxy();
        proxy.doSomeWork();
    }
}
