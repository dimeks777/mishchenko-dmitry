package ua.khpi.oop.mishchenko14;

import ua.khpi.oop.mishchenko14.controller.Loop;

public class ModifiedThreads {

    public static void main(String[] args) {
        var loop = new Loop(System.in, System.out);
        loop.loop(args);

    }
}
