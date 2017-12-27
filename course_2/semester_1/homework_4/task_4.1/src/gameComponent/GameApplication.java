package gameComponent;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import networkComponent.Communicable;

/**
 * Creates game application
 */
abstract public class GameApplication extends Application {
    abstract protected Communicable createCommunicable(Game game);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Tanks");
        primaryStage.setScene(new Scene(root, InitialGameData.width, InitialGameData.height));
        primaryStage.setResizable(false);
        Canvas canvas = new Canvas(InitialGameData.width, InitialGameData.height);
        root.getChildren().add(canvas);
        primaryStage.sizeToScene();

        Game game = new Game(new Relief(canvas), new Tank(canvas, InitialGameData.startFirstX, InitialGameData.startFirstY));
        Communicable communicable = createCommunicable(game);
        game.setCommunicable(communicable);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                game.handleKeyPress(event);
            }
        });

        primaryStage.show();
    }
}
