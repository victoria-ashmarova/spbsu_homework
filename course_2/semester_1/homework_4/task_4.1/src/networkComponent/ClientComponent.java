package networkComponent;

import gameComponent.Action;
import gameComponent.Game;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * For sending and getting messages about other player
 */
public class ClientComponent extends CommunicableComponent{
    private int serverPort;
    private InetAddress ipAddress;

    public ClientComponent(Game game) {
        this.game = game;
        game.setCommunicable(this);

        try {
            Scanner sc = new Scanner(System.in);
            String name = getIp(sc);
            serverPort = getServerPort(sc);
            ipAddress = InetAddress.getByName(name);
            socket = new Socket(ipAddress, serverPort);

            System.out.println("Server-player found");

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getServerPort(Scanner sc) {
        System.out.print("Enter the number of server port - integer number in bounds for 1025 to 65355>");
        int toReturn = sc.nextInt();
        return toReturn;
    }

    private String getIp(Scanner sc) {
        System.out.print("Enter ip of server-player>");
        return sc.next();
    }
}
