package networkComponent;

import gameComponent.Game;
import gameComponent.GameApplication;
import gameComponent.InitialGameData;
import gameComponent.Tank;
import javafx.scene.canvas.Canvas;

public class ServerApplication extends GameApplication {
    @Override
    protected Communicable createCommunicable(Game game) {
        return new ServerComponent(game);
    }

    @Override
    protected Tank createFirstTank(Canvas canvas) {
        return new Tank(canvas, InitialGameData.startFirstX, InitialGameData.startFirstY, InitialGameData.firstColor);
    }

    @Override
    protected Tank createSecondTank(Canvas canvas) {
        return new Tank(canvas, InitialGameData.startSecondX, InitialGameData.startSecondY, InitialGameData.secondColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
