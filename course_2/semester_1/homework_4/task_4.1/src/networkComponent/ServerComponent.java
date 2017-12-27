package networkComponent;

import gameComponent.*;

import java.net.*;
import java.io.*;

/**
 * For sending and getting messages about other player
 */
public class ServerComponent implements Communicable {
    private Game game;

    private int port = 6666;
    private ServerSocket ss;
    private DataInputStream in;
    private DataOutputStream out;

    public ServerComponent(Game game) {
        this.game = game;
    }

    @Override
    public void findCommunicable() {
        try {
            ss = new ServerSocket(port);
            Socket socket = ss.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            game.setCommunicable(this);
        } catch(Exception e) { e.printStackTrace(); }
    }

    @Override
    public void sendRequest(Action action) {
        //todo
    }

    @Override
    public Action handleRequest() {
        return null;
    }
}