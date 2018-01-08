package gameComponent;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import networkComponent.Connectable;
import networkComponent.DisableConnectionException;

/**
 * Creates game application
 */
abstract public class GameApplication extends Application {
    private Game game;

    /** creates component for connection: server or client*/
    abstract protected Connectable createConnectable(Game game) throws DisableConnectionException;

    /** constructs tank for local player*/
    abstract protected Tank createFirstTank(Canvas canvas);

    /** constructs tank for remote player*/
    abstract protected Tank createSecondTank(Canvas canvas);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Tanks");
        primaryStage.setScene(new Scene(root, GameData.width, GameData.height));
        primaryStage.setResizable(false);
        Canvas canvas = new Canvas(GameData.width, GameData.height);
        root.getChildren().add(canvas);
        primaryStage.sizeToScene();

        game = new Game(new Relief(canvas), createFirstTank(canvas), createSecondTank(canvas));
        try {
            Connectable connectable = createConnectable(game);
        } catch (DisableConnectionException e) {
            e.message();
            System.exit(-1);
        }

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    game.handleKeyPress(event);
                } catch (DisableConnectionException e) {
                    e.message();
                    System.exit(-1);
                }
            }
        });

        primaryStage.show();

        game.initConnection();
    }

    @Override
    public void stop() {
        try {
            this.game.closeCommunication();
        } catch (DisableConnectionException e) {
            e.message();
        }
    }
}
