package ProducerConsumer;

public class Forretning {
    public static void main(String[] args) {
        Slide slide = new Slide(); //instanciere klassen Slide for at kigge efter burgere.
        Producer producer = new Producer(slide);
        Consumer consumer = new Consumer(slide);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
