package ua.khpi.oop.mishchenko15.io;

import java.io.PrintStream;
import java.util.ArrayList;


public interface View<T> {

    void printView(PrintStream target);

    void updateView(ArrayList<T> clients);
}
