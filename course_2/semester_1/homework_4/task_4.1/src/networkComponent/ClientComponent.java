package networkComponent;

import gameComponent.Game;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * For sending and getting messages about other player
 */
public class ClientComponent extends ConnectableComponent {
    private int serverPort = 6666;
    private InetAddress ipAddress;

    public ClientComponent(Game game) throws DisableConnectionException{
        this.game = game;
        game.setConnectable(this);

        try {
            Scanner sc = new Scanner(System.in);
            String name = getIp(sc);
            ipAddress = InetAddress.getByName(name);
            socket = new Socket(ipAddress, serverPort);

            System.out.println("Server-player found");

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new DisableConnectionException("Couldn't connect with server-player");
        }
    }

    private String getIp(Scanner sc) {
        System.out.print("Enter ip of server-player>");
        return sc.next();
    }
}
