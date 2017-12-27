package networkComponent;

import gameComponent.Game;
import gameComponent.GameApplication;

public class ServerApplication extends GameApplication implements Runnable{
    @Override
    public void run() {}

    @Override
    protected Communicable createCommunicable(Game game) {
        return new ServerComponent(game);
    }
}
