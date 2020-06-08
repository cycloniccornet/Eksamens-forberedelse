package FullDuplexChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean abort = true;
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 6780);
            System.out.println("Har oprettet forbindelse til chat server...");

            //Modtager data fra server
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

            //Skriver til server
            Thread sendMessage = new Thread(new Runnable() { // Sætter Runnable ind i metoden
                @Override
                public void run() {
                    while (abort){
                        String message = scanner.nextLine();

                        try {
                            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                            printWriter.println("Client: " + message);
                        } catch (Exception exception) {
                            System.out.println("Error: " + exception.getMessage());
                        }
                    }
                }
            });

            Thread readMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (abort){
                        try {
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            System.out.println("Server: " + bufferedReader.readLine());

                        } catch (Exception exception) {
                            System.out.println("Error: " + exception.getMessage());
                        }
                    }
                }
            });

            readMessage.start();
            sendMessage.start();


        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

}
