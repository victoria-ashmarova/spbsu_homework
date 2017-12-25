import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Tanks");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        Canvas canvas = new Canvas(600, 400);
        root.getChildren().add(canvas);
        primaryStage.sizeToScene();

        Game game = new Game(new Relief(canvas), new Tank(canvas, 150, 200)); //точка старта?

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                game.handleKeyPress(event);
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
