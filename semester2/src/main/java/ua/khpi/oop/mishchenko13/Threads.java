package ua.khpi.oop.mishchenko13;

import ua.khpi.oop.mishchenko13.controller.Loop;

public class Threads {

    public static void main(String[] args) {
        var loop = new Loop(System.in, System.out);
        loop.loop(args);

    }
}
