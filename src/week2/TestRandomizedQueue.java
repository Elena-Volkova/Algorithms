package week2;

public class TestRandomizedQueue {

    public void testAddFirstAddLast() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("111");
        randomizedQueue.enqueue("222");
        randomizedQueue.enqueue("333");

        for (String value : randomizedQueue) {
            System.out.println(value);
        }
    }

    public void testAddFirstAddLastRemove() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("111");
        randomizedQueue.enqueue("222");
        randomizedQueue.enqueue("333");
        randomizedQueue.enqueue("444");
        randomizedQueue.enqueue("555");

        System.out.println("Sample: " + randomizedQueue.sample());
        System.out.println("Sample: " + randomizedQueue.sample());
        System.out.println("Removed: " + randomizedQueue.dequeue());
        System.out.println("Removed: " + randomizedQueue.dequeue());

        for (String value : randomizedQueue) {
            System.out.println(value);
        }
    }

    public void test1() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("111");
        randomizedQueue.dequeue();

        for (String value : randomizedQueue) {
            System.out.println(value);
        }
    }
}
