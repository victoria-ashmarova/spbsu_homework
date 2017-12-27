package networkComponent;

import gameComponent.Action;

/**
 * For sending and getting messages about other player
 */
public interface Communicable {
    void findCommunicable();
    void sendRequest(Action action);
    Action handleRequest();
}
