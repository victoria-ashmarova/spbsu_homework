package ashmarova.task_2_7_2.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("X&O game");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(330);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
