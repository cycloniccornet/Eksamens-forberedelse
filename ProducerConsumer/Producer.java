package ProducerConsumer;

import java.util.Random;

public class Producer implements Runnable{

    private Slide slide;
    private int count =0;
    private Random random = new Random();

    public Producer(Slide slide){
        this.slide = slide;
    }

    @Override
    public void run() {
        while (count < 10){ // Vi laver i alt 10 burgerer
            slide.makeBurger(); // fortæller at der skal laves flere burgere, men er stadig underlagt tråden i Slide.
            try {
                Thread.sleep(random.nextInt(200));
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }
            count++;
        }
    }
}
