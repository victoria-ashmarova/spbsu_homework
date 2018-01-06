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
            //
            System.out.println("Waiting for a client...");
            socket = ss.accept();
            //
            System.out.println("Got a client.");

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            //
            System.out.print("streams are ready");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}