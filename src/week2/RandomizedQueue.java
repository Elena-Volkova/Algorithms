package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;
    private int size;

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (n == a.length) {
            resize(2 * a.length);
        }
        size++;
        a[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index;
        do {
            index = StdRandom.uniform(n);
        } while (a[index] == null);

        Item item = a[index];
        a[index] = null;
        size--;

        int lastItemIndex = n - 1;
        while (n > 0 && a[lastItemIndex] == null) {
            n--;
            lastItemIndex--;
        }

        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index;
        do {
            index = StdRandom.uniform(n);
        } while (a[index] == null);

        return a[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private RandomizedQueue<Item> randomizedQueue;
        private int i;

        public RandomizedIterator() {
            i = 0;
            randomizedQueue = new RandomizedQueue<>();
            for (Item item : a) {
                if (item != null) {
                    randomizedQueue.enqueue(item);
                    i++;
                }
            }
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            i--;

            return randomizedQueue.dequeue();
        }
    }

    public static void main(String[] args) {
        TestRandomizedQueue testRandomizedQueue = new TestRandomizedQueue();
        testRandomizedQueue.testAddFirstAddLast();
        testRandomizedQueue.testAddFirstAddLastRemove();
        testRandomizedQueue.test1();
        testRandomizedQueue.test2();
    }
}
