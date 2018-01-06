package networkComponent;

import gameComponent.Action;
import gameComponent.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The same part of client's and server's components
 */
abstract public class CommunicableComponent implements Communicable{
    protected Game game;

    protected Socket socket;
    protected DataInputStream in;
    protected DataOutputStream out;

    private boolean stopCondition = false;

    @Override
    public void sendRequest(Action action) throws IOException {
        Action currentRequest = action;
        out.writeUTF(action.name());
        out.flush();
    }

    @Override
    public void run() {
        try {
            stopCondition = false;
            while (!stopCondition) {
                Action toHandle = Action.valueOf(in.readUTF());
                game.handleRequest(toHandle);

                if (socket.isClosed()) {
                    stopCondition = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopCommunication() {
        stopCondition = true;
    }

    @Override
    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
