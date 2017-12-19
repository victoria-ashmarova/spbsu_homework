package sample;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML Canvas canvas;
    private Game game;
    private final int numberOfExtremums = 5;


    /** calls method of drawing relief and sets tank on field */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Game(new Relief(canvas), new Tank(canvas));
        game.getRelief().draw();
    }
}
