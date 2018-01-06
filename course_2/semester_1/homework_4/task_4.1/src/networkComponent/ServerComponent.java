package networkComponent;

import gameComponent.*;

import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;

/**
 * For sending and getting messages about other player
 */
public class ServerComponent extends CommunicableComponent {
    private int port = 6666;
    private ServerSocket ss;

    public ServerComponent(Game game) {
        this.game = game;
        game.setCommunicable(this);

        try {
            ss = new ServerSocket(port);

            String address = InetAddress.getLocalHost().getCanonicalHostName();
            System.out.println("You are server-player with IP address " + address);
            System.out.println("Waiting for a client-player...");
            socket = ss.accept();

            System.out.println("Got a client-player.");

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}