package networkComponent;

import gameComponent.Game;
import gameComponent.GameApplication;
import gameComponent.GameData;
import gameComponent.Tank;
import javafx.scene.canvas.Canvas;

/** application for server player*/
public class ServerApplication extends GameApplication {
    @Override
    protected Connectable createConnectable(Game game) throws DisableConnectionException {
        return new ServerComponent(game);
    }

    @Override
    protected Tank createFirstTank(Canvas canvas) {
        return new Tank(canvas, GameData.startFirstX, GameData.startFirstY, GameData.firstColor);
    }

    @Override
    protected Tank createSecondTank(Canvas canvas) {
        return new Tank(canvas, GameData.startSecondX, GameData.startSecondY, GameData.secondColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
