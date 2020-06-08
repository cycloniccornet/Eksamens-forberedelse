package DiningPhilosophers;

import java.util.Random;

public class Philosopher implements Runnable {

    Random random = new Random();
    public Object leftFork;
    public Object rightFork;

    public Philosopher(Object leftFork, Object rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
         while (true){

             //Thinking
             doAction(": Thinking");
             synchronized (rightFork){
                 doAction(": Picking up right fork");
                 synchronized (leftFork){
                     //Eating here
                     doAction(": Picking up left fork and eating");

                     doAction(": putting down right fork");
                 }

                 //Begin thinking again
                 doAction(": putting down left fork to think");
             }
         }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

    }
}
