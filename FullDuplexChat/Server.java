package FullDuplexChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            ServerSocket serverSocket = new ServerSocket(6780);
            System.out.println("Klar til at modtage fra klienten...");
            Socket socket = serverSocket.accept();

            // server modtager besked fra klienten her.
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

            //Brug af tråde for at gøre programmet Full Duplex
            Thread readMessage = new Thread(new Runnable() { //Smider Runnable direkte ind i tråden i stedet for at implementere den i toppen.
                @Override
                public void run() {
                    while (true){
                        try {
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            System.out.println(bufferedReader.readLine());
                        } catch (IOException e){
                            e.getMessage();
                        }
                    }

                }
            });


            //Server sender til klienten.
            Thread sendMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        String message = scanner.nextLine();

                        try {
                            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                            printWriter.println(message);
                        } catch (IOException e){
                            e.getMessage();
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
