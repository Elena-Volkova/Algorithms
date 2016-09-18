package week2;

import java.util.Iterator;

public class TestDeque {

    public void testAddFirstAddLast() {
        Deque<String> stringDeque = new Deque<>();
        stringDeque.addFirst("222");
        stringDeque.addFirst("111");
        stringDeque.addLast("333");

        Iterator<String> iterator = stringDeque.iterator();
        assert iterator.next().equals("111");
        assert iterator.next().equals("222");
        assert iterator.next().equals("333");
    }

    public void testAddFirstAddLastRemove() {
        Deque<String> stringDeque = new Deque<>();
        stringDeque.addFirst("222");
        stringDeque.addFirst("111");
        stringDeque.addLast("333");
        stringDeque.addLast("444");
        stringDeque.addFirst("000");

        stringDeque.removeLast();
        stringDeque.removeFirst();

        Iterator<String> iterator = stringDeque.iterator();
        assert iterator.next().equals("111");
        assert iterator.next().equals("222");
        assert iterator.next().equals("333");
    }

    public void test1() {
        Deque<String> stringDeque = new Deque<>();
        stringDeque.addFirst("222");
        stringDeque.removeLast();
        stringDeque.addFirst("111");
        stringDeque.addFirst("222");
        stringDeque.removeLast();

        Iterator<String> iterator = stringDeque.iterator();
        assert iterator.next().equals("222");
    }

    public void test2() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        deque.addLast(3);
        deque.removeFirst();
        deque.addLast(5);
        deque.removeFirst();
        deque.addLast(7);
        deque.addLast(8);
        deque.addFirst(9);
        deque.removeFirst();
        deque.addFirst(11);
        deque.removeLast();

        assert deque.size() == 2;
    }

    public void test3() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.removeFirst();
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addFirst(8);
        deque.removeFirst();
        deque.removeLast();

        assert deque.size() == 4;

        Iterator<Integer> iterator = deque.iterator();
        assert iterator.next().equals(1);
        assert iterator.next().equals(3);
        assert iterator.next().equals(5);
        assert iterator.next().equals(6);
    }
}
