import java.util.Scanner;

public class UserInput implements Runnable {
    private Client client;

    public UserInput(Client client) {
        this.client = client;

    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String message = scan.nextLine();
            client.send(message);
            if (message.equalsIgnoreCase("quit")) {
                System.out.println("Du er logget af.");
                break;
            }
        }
    }
}

