package ua.khpi.oop.mishchenko09.io;

import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;


public interface FileSerialization {

    <T> void write(ForwardLinkedList<T> forwardLinkedList, String path);

    <T> ForwardLinkedList<T> read(String path);
}
