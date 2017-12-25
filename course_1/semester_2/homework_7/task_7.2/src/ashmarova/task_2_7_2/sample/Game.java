package ashmarova.task_2_7_2.sample;

import javafx.scene.control.Button;
import java.util.ArrayList;

/**
 * Class contains states of game
 */
public class Game {
    /** String, which will be printed on next clear pressed button */
    private String currentLabel = "X";
    private final int SIZE_OF_FIELD = 3;

    /** Shows communications with number of button and label on it*/
    private String[] state = new String[SIZE_OF_FIELD * SIZE_OF_FIELD];

    Game() {
        for (int i = 0; i < SIZE_OF_FIELD * SIZE_OF_FIELD; i++) {
            this.state[i] = "";
        }
    }

    /**
     * Gets label for next clear button
     * @return string to print
     */
    public String getCurrentLabel() {
        return currentLabel;
    }

    public void rewriteCurrentLabel() {
        if (currentLabel.equals("X")) {
            this.currentLabel = "O";
            return;
        }
        if (currentLabel.equals("O")) {
            this.currentLabel = "X";
            return;
        }
    }

    /**
     * If button becomes labeled, field with index of this button becomes labeled with button label.
     * @param index is index of labeled button
     * @param toSet is string-label
     */
    public void setState(int index, String toSet) {
        this.state[index] = toSet;
    }

    /**
     * search, if there is winner in game
     * so there if vertical, horizontal or diagonal with equal not null elements
     *
     * @param number index of last labeled button
     * @return label of winner or null, if there is not winner
     */
    public String checkWin(int number) {
        String current = state[number];
        boolean isVertical = true;
        boolean isHorizontal = true;
        for (int i = 0; i < state.length; i++) {
            isHorizontal = isHorizontal && !(!current.equals(state[i]) && number / SIZE_OF_FIELD == i / SIZE_OF_FIELD);
            isVertical = isVertical && !(!current.equals(state[i]) && number % SIZE_OF_FIELD == i % SIZE_OF_FIELD);
        }
        if (foolDiagonal() || isVertical || isHorizontal) {
            return current;
        }
        else {
            return null;
        }
    }

    /**
     * checks, if there is diagonal with equal elements in game-field
     * @return true if there is
     */
    private boolean foolDiagonal() {
        String centre = state[4];
        if (centre.equals("")) {
            return false;
        }
        String leftUp = state[0];
        String rightUp = state[2];
        String leftDown = state[6];
        String rightDown = state[8];

        return (centre.equals(rightDown) && centre.equals(leftUp)) || (centre.equals(rightUp) && centre.equals(leftDown));
    }

    /**
     * makes game-field clear
     * @param buttons is list buttons of game field
     */
    public void clear(ArrayList<Button> buttons) {
        currentLabel = "X";
        for (Button button : buttons) {
            button.setText("");
        }
        for (int i = 0; i < SIZE_OF_FIELD * SIZE_OF_FIELD; i++) {
            this.state[i] = "";
        }
    }
}
