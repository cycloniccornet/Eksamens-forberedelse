import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Server {
    // static HashMap<InetAddress, Integer> clients = new HashMap<>();
    List<ClientData> connectedClients = new ArrayList<>();
    String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[0m"};
    int colorCounter = 0;

    public Server() {
        startServer();
    }

    public void startServer() {
        try {

            DatagramSocket datagramSocket = new DatagramSocket(6780);
            System.out.println("Socket oprettet. Lytter til klient.");
            byte[] receiveArray = new byte[1000];
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveArray, receiveArray.length);
                datagramSocket.receive(receivePacket); // Modtager data fra klienten - receive() blokere.
                // clients.put(receivePacket.getAddress(), receivePacket.getPort());
                // Tilføj user til klient listen.
                if (addUserToList(receivePacket.getAddress(), receivePacket.getPort())) {
                    String username = new String(receiveArray, 0, receivePacket.getLength());
                    connectedClients.get(connectedClients.size()-1).setNickname(colors[colorCounter]+username+colors[6]);
                    if (colorCounter >= 5) {
                        colorCounter = 0;
                    } else {
                        colorCounter++;
                    }
                    System.out.println("Klient forbundet med navn: "+username);
                    ClientData current = new ClientData(receivePacket.getAddress(), receivePacket.getPort(), connectedClients.get(connectedClients.size()-1).getNickname());
                    sendTo(datagramSocket, current.getAddress(), current.getPort(), "Velkommen til chatten "+current.getNickname());
                    for (ClientData client: connectedClients) {
                        sendTo(datagramSocket, client.getAddress(), client.getPort(), current.getNickname()+" har joinet kanalen.");
                        System.out.println("Sendt data til IP: "+client.getAddress()+", Portnummer: "+client.getPort());
                    }

                } else {
                    String inMsg = getCurrentUser(receivePacket.getAddress(),receivePacket.getPort())+" skriver -> "+ new String(receiveArray, 0, receivePacket.getLength());
                    System.out.println("Besked modtaget: "+inMsg);
                    // Sende svar tilbage til alle klienter:

                    for (ClientData client: connectedClients) {
                        sendTo(datagramSocket, client.getAddress(), client.getPort(), inMsg);
                        System.out.println("Sendt data til IP: "+client.getAddress()+", Portnummer: "+client.getPort());
                    }

                    if (inMsg.endsWith("quit")) {
                        System.out.println("Klienten har lukket forbindelsen.");
                        String name = removeUser(receivePacket.getAddress(),receivePacket.getPort());
                        for (ClientData client: connectedClients) {
                            sendTo(datagramSocket, client.getAddress(), client.getPort(), name+" er logget af.");
                            System.out.println("Sendt data til IP: "+client.getAddress()+", Portnummer: "+client.getPort());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private void sendTo(DatagramSocket socket, InetAddress address, int port, String message) throws IOException {

        byte[] sendArray = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendArray, sendArray.length,
                address, port);
        socket.send(sendPacket);
    }

    private boolean addUserToList(InetAddress inetAddress, int port) {
        boolean exist = false;
        for (ClientData client: connectedClients) {
            if (client.getAddress().equals(inetAddress) && port == client.getPort()) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            connectedClients.add(new ClientData(inetAddress, port));
            return true;
        }
        return false;
    }

    private String getCurrentUser(InetAddress inetAddress, int port) {
        for (ClientData client: connectedClients) {
            if (client.getAddress().equals(inetAddress) && port == client.getPort()) {
                return client.getNickname();
            }
        }
        return null;
    }

    private String removeUser(InetAddress inetAddress, int port) {
        String username = null;
        for (ClientData client: connectedClients) {
            if (client.getAddress().equals(inetAddress) && port == client.getPort()) {
                username = client.getNickname();
                connectedClients.remove(client);
                break;
            }
        }
        return username;
    }
}
