package networkComponent;

import gameComponent.Action;

import java.io.IOException;

/**
 * For sending and getting messages about game between players
 */
public interface Connectable extends Runnable{
    /** to send request for remote player*/
    void sendRequest(Action action) throws IOException;

    /** to stop communication between players*/
    void stopCommunication();

    /** to finish work*/
    void close() throws DisableConnectionException;
}
