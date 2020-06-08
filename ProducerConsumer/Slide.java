package ProducerConsumer;

public class Slide {
    private int antalBurger = 0; //Dette er det antal burgere der er i sliden

    public synchronized void makeBurger() { //Syncronized for at sikre at kun en tråd har mulighed for at få adgang ad gangen til dette objekt
        try {
            while (antalBurger >= 8) {
                wait(); //Wait lader tråden vente på consumer hvis der er 8 burgere eller flere.
            }
            antalBurger++;
            System.out.println("Tilføjer ny burger, der er nu: " + antalBurger + " stk.");
            notify();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public synchronized void takeBurger() {
        try {
            while (antalBurger <=0){
                wait(); //venter på at produceren siger notify.
            }
            antalBurger--;
            System.out.println("Fjerner en burger, der er nu: " + antalBurger + " stk.");
            notify();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }


}
