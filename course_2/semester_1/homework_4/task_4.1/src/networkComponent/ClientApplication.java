package networkComponent;

import gameComponent.Game;
import gameComponent.GameApplication;

public class ClientApplication extends GameApplication implements Runnable {
    @Override
    public void run() {}

    @Override
    protected Communicable createCommunicable(Game game) {
        return new ClientComponent(game);
    }
}
