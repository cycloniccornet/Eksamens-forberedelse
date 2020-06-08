package ProducerConsumer;

import java.util.Random;

public class Consumer implements Runnable{
    private Slide slide;
    private int count = 0;
    private Random random = new Random();

    public Consumer(Slide slide){
        this.slide = slide;
    }


    @Override
    public void run() {
        while (count<10){ //sikre at alle burgere bliver taget.
                slide.takeBurger(); //Fjerner en burger, hvis der er nogle i slide og tråden tillader dette.
            try {
                Thread.sleep(random.nextInt(200));
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }
            count++;
        }
    }
}
