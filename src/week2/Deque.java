package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;

        if (oldFirst == null) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }

        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;

        if (oldLast == null) {
            first = last;
        } else {
            last.prev = oldLast;
            oldLast.next = last;
        }

        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        n--;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        n--;

        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        TestDeque testDeque = new TestDeque();
        testDeque.testAddFirstAddLast();
        testDeque.testAddFirstAddLastRemove();
        testDeque.test1();
        testDeque.test2();
        testDeque.test3();
    }
}
