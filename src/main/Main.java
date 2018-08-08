package main;

import static proxy.NullProxy.createProxy;

public class Main {

    public static void main(String[] args) {

        Dummy dummy = createProxy(Dummy.class);

        System.out.println(dummy.getString3());

        Dummy dummy1 = new Dummy();

        try {
            dummy1.getString3();
        } catch (NullPointerException e) {
            System.out.println("This throws an exception");
        }

        Dummy dummy2 = createProxy(dummy1);

        System.out.println(dummy2.getString3());

    }
}
