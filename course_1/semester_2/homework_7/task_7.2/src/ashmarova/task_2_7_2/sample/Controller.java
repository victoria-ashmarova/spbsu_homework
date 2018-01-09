package ashmarova.task_2_7_2.sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import java.util.ArrayList;

public class Controller {
    @FXML Label message;
    @FXML Button button_0;
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML Button button_4;
    @FXML Button button_5;
    @FXML Button button_6;
    @FXML Button button_7;
    @FXML Button button_8;

    ArrayList<Button> buttons = new ArrayList<>();
    Game game = new Game();

    private final String MOVE_X = "Move X";
    private final String MOVE_O = "Move O";
    private int numberOfLabeledButtons = 0;

    /**
     * Change messages about queue of move
     */
    private void changeMover() {
        if (message.getText().equals(MOVE_X)) {
            message.setText(MOVE_O);
            return;
        }
        if (message.getText().equals(MOVE_O)) {
            message.setText(MOVE_X);
            return;
        }
    }

    /**
     * Prints message, if somebody win
     * Blocks buttons on game-field
     * @param winnerIdentifier is label of buttons, which created line
     */
    private void afterWin(String winnerIdentifier) {
        message.setText(winnerIdentifier + " is won! Press Restart to play again.");
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    /**
     * Prints message, if there is draw
     * Blocks buttons on game-field
     */
    private void draw() {
        message.setText("It is draw. Press restart to play again. ");
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    /**
     * adds buttons to list and blocks it
     */
    @FXML
    public void initialize() {
        buttons.add(button_0);
        buttons.add(button_1);
        buttons.add(button_2);
        buttons.add(button_3);
        buttons.add(button_4);
        buttons.add(button_5);
        buttons.add(button_6);
        buttons.add(button_7);
        buttons.add(button_8);
        message.setAlignment(Pos.CENTER);
        message.setText("to begin press Restart");

        for (Button button : buttons) {
            button.setOnAction(event -> move(button));
            button.setDisable(true);
        }
    }

    /**
     * clears game-field
     * @param actionEvent is action with restart button
     */
    @FXML
    public void onRestart(ActionEvent actionEvent) {
        for (Button button : buttons) {
            button.setDisable(false);
        }
        game.clear(buttons);
        message.setText(MOVE_X);
        this.numberOfLabeledButtons = 0;
    }

    /**
     * realizes somebodies move
     * @param button is button, which was pressed
     */
    @FXML
    public void move(Button button) {
        if (button.getText().equals("")) {
            button.setText(game.getCurrentLabel());
            game.setState(buttons.indexOf(button), button.getText());
            if (game.checkWin(buttons.indexOf(button)) == null) {
                changeMover();
                game.rewriteCurrentLabel();
                numberOfLabeledButtons++;
                if (numberOfLabeledButtons == 9) {
                    draw();
                }
            } else {
                afterWin(game.checkWin(buttons.indexOf(button)));
            }
        }
    }
}
