package networkComponent;

import gameComponent.Game;
import gameComponent.GameApplication;
import gameComponent.GameData;
import gameComponent.Tank;
import javafx.scene.canvas.Canvas;

/** application for client-player*/
public class ClientApplication extends GameApplication {
    @Override
    protected Connectable createConnectable(Game game) throws DisableConnectionException {
        return new ClientComponent(game);
    }

    @Override
    protected Tank createFirstTank(Canvas canvas) {
        return new Tank(canvas, GameData.startSecondX, GameData.startSecondY, GameData.secondColor);
    }

    @Override
    protected Tank createSecondTank(Canvas canvas) {
        return new Tank(canvas, GameData.startFirstX, GameData.startFirstY, GameData.firstColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
