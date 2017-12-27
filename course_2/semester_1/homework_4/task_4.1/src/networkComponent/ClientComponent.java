package networkComponent;

import gameComponent.Action;
import gameComponent.Game;

import java.net.*;
import java.io.*;

/**
 * For sending and getting messages about other player
 */
public class ClientComponent implements Communicable{
    private Game game;

    private int serverPort = 6666;
    private String address = "192.168.0.108";
    private InetAddress ipAddress;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientComponent(Game game) {
        this.game = game;
    }

    @Override
    public void findCommunicable() {
        try {
            ipAddress = InetAddress.getByName(address);
            socket = new Socket(ipAddress, serverPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            game.setCommunicable(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRequest(Action action) {

    }

    @Override
    public Action handleRequest() {
        return null;
    }
}
