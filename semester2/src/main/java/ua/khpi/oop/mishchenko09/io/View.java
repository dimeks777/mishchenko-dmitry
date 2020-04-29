package ua.khpi.oop.mishchenko09.io;

import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;

import java.io.PrintStream;


public interface View<T> {

    void printView(PrintStream target);

    void updateView(ForwardLinkedList<T> clients);
}
