package networkComponent;

import gameComponent.Action;

import java.io.IOException;

/**
 * For sending and getting messages about other player
 */
public interface Communicable extends Runnable{
    void sendRequest(Action action) throws IOException;
    void stopCommunication();
    void close();
}
