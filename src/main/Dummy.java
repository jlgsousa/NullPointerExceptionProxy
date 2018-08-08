package main;

public class Dummy {

    public String getString1() {
        return "coco";
    }

    public String getString2() {
        return null;
    }

    public String getString3() {
        return getString2().substring(1);
    }
}
