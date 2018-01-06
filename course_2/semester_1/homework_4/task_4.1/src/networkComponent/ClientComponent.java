package networkComponent;

import gameComponent.Action;
import gameComponent.Game;

import java.net.*;
import java.io.*;

/**
 * For sending and getting messages about other player
 */
public class ClientComponent extends CommunicableComponent{
    private int serverPort = 6666;
   // private String address = "192.168.0.108";
    private InetAddress ipAddress;

    private Action currentRequest;

    public ClientComponent(Game game) {
        this.game = game;
        game.setCommunicable(this);

        try {
            ipAddress = InetAddress.getByName(null); //а как адрес то определять?
            socket = new Socket(ipAddress, serverPort);

            System.out.println("Server found");

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            //
            System.out.print("streams are ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
