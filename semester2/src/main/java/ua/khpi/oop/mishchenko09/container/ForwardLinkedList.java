package ua.khpi.oop.mishchenko09.container;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class ForwardLinkedList<T> implements Serializable, Iterable<T> {

    private static final long serialVersionUID = 1L;
    private Node<T> head;
    private Node<T> tail;
    private int size;


    public T getFirst() {
        final Node<T> f = head;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    public T getLast() {
        final Node<T> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    public void addFront(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addBack(T data) {
        Node<T> node = new Node<>(data);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public boolean add(T data) {
        addBack(data);
        return true;
    }

    public void addByIndex(T data, int index) {
        checkIndex(index, size + 1);
        Node<T> x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        x.item = data;
        size++;
    }

    public T deleteByIndex(int index) {
        if (head != null && checkIndex(index, size)) {
            Node<T> temp = head;
            Node<T> left = head;
            T deleted;
            for (int i = 0; i < index; i++) {
                left = temp;
                temp = temp.next;
            }
            if (temp == head) {
                deleted = head.item;
                head = temp.next;
            } else if (temp == tail) {
                deleted = tail.item;
                tail = left;
                left.next = null;
            } else {
                deleted = temp.item;
                left.next = temp.next;
            }
            size--;
            return deleted;
        }
        return null;

    }

    public boolean remove(Object o) {
        int i = 0;
        if (o == null) {
            for (Node<T> x = head; x != null; x = x.next, i++) {
                if (x.item == null) {
                    deleteByIndex(i);
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next, i++) {
                if (o.equals(x.item)) {
                    deleteByIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsElem(T elem) {
        if (isEmpty()) {
            return false;
        }

        for (Node<T> node = head; node.hasNext(); node = node.next) {
            if (Objects.equals(elem, node.item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForwardLinkedList<?> that = (ForwardLinkedList<?>) o;
        if (size != that.size) return false;
        Iterator<?> thatItr = that.iterator();
        Iterator<T> thisItr = iterator();
        while (thisItr.hasNext() && thatItr.hasNext()) {
            if (!Objects.equals(thisItr.next(), thatItr.next()))
                return false;
        }
        return !(thatItr.hasNext() || thisItr.hasNext());
    }


    @Override
    public int hashCode() {
        int hashCode = 1;
        for (T e : this)
            hashCode = 31 * hashCode + Objects.hashCode(e);
        return hashCode;
    }


    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        sb.append(head.item);
        Node<T> node = head;
        while (node.hasNext()) {
            node = node.next();
            sb.append(", ").append(node.item);
        }
        return sb.append("]").toString();

    }

    public void clear() {
        for (Node<T> x = head; x != null; ) {
            Node<T> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    @SafeVarargs
    public final ForwardLinkedList<T> of(T... elements) {
        ForwardLinkedList<T> forwardLinkedList = new ForwardLinkedList<>();
        for (T elem : elements) {
            forwardLinkedList.add(elem);
        }
        return forwardLinkedList;
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> c, T[] arr) {
        Arrays.sort(arr, c);
        ListIterator<T> i = this.listIterator();
        for (Object e : arr) {
            i.next();
            i.set((T) e);
        }
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a) {
        if (a.length < size()) {
            T[] array = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
            Node<T> temp = head;
            int currentIndex = 0;
            while (currentIndex < size) {
                a[currentIndex] = (T) temp.item;
                currentIndex++;
                temp = temp.next;
            }
        }

        if (a.length > size()) {
            a[size] = null;
        }

        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            a[i] = (T) temp.item;
            temp = temp.next;
        }

        return a;

    }

    public T get(int index) {
        if (checkIndex(index, size)) {
            return node(index).item;
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Incorrect index! Max index is " + (size - 1));
        }
        return true;
    }

    private static class Node<T> implements Serializable {
        T item;
        Node<T> next;

        Node(T element) {
            this.item = element;
        }

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }

        public T getItem() {
            return this.item;
        }

        public void link(Node<T> next) {
            this.next = next;
        }

        public void unlink() {
            this.next = null;
        }

        public Node<T> next() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }
    }

    public Node<T> node(int index) {
        checkIndex(index, size);
        Node<T> x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }


    private final class Itr implements Iterator<T> {

        private Node<T> previous;
        private Node<T> current;

        Itr() {
            this.current = new Node<>(null, ForwardLinkedList.this.head);
            this.previous = new Node<>(null, current);
        }

        @Override

        public boolean hasNext() {
            return current.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> next = current.next();
            previous = current;
            current = next;
            return next.getItem();
        }

        @Override
        public void remove() {
            Node<T> next = current.next();
            previous.link(next);
        }


    }

    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    private class ListItr implements ListIterator<T> {
        private Node<T> lastReturned;
        private Node<T> next;
        private int nextIndex;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public T previous() {
            throw new UnsupportedOperationException();
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(T e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.item = e;
        }

        public void add(T e) {
            throw new UnsupportedOperationException();
        }

    }
}
