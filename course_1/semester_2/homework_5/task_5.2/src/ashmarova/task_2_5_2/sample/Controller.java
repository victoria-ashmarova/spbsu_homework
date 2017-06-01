package ashmarova.task_2_5_2.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {
    private Calculator calc = new Calculator();
    private boolean lastPressedWasWithAction = false;

    @FXML Label expression;

    @FXML Label currentValue;

    @FXML Button sum;

    @FXML Button difference;

    @FXML Button multiplication;

    @FXML Button quotient;

    @FXML Button clear;

    @FXML Button digit_0;

    @FXML Button digit_1;

    @FXML Button digit_2;

    @FXML Button digit_3;

    @FXML Button digit_4;

    @FXML Button digit_5;

    @FXML Button digit_6;

    @FXML Button digit_7;

    @FXML Button digit_8;

    @FXML Button digit_9;

    private ArrayList<Button> digits = new ArrayList<>();
    private ArrayList<Button> actions = new ArrayList<>();

    /**
     * creates lists with buttons of digits and actions
     */
    @FXML
    public void initialize() {
        digits.add(digit_0);
        digits.add(digit_1);
        digits.add(digit_2);
        digits.add(digit_3);
        digits.add(digit_4);
        digits.add(digit_5);
        digits.add(digit_6);
        digits.add(digit_7);
        digits.add(digit_8);
        digits.add(digit_9);

        actions.add(sum);
        actions.add(difference);
        actions.add(multiplication);
        actions.add(quotient);
    }

    /**
     * makes fields of calculation clean
     * all buttons are available
     *
     * @param actionEvent is action with clear button
     */
    @FXML
    public void clear(ActionEvent actionEvent) {
        makeButtonsNotDisable(actions);
        makeButtonsNotDisable(digits);
        expression.setText("");
        currentValue.setText("");
        calc.clear();
        lastPressedWasWithAction = false;
    }

    /**
     * realizes actions after press button with digit
     * prints digits to labels with expression and current value
     * @param actionEvent is action with digit button
     */
    @FXML
    public void pressDigit(ActionEvent actionEvent) {
        if (lastPressedWasWithAction) {
            currentValue.setText("");
        }
        expression.setText(expression.getText() + getPartToPrint(actionEvent));
        currentValue.setText(currentValue.getText() + getPartToPrint(actionEvent));
        lastPressedWasWithAction = false;
        makeButtonsNotDisable(actions);
    }

    /**
     * returns symbols from pressed bytton
     * @param actionEvent is action with button
     * @return symbol from button
     */
    private String getPartToPrint(ActionEvent actionEvent) {
        String id = actionEvent.getSource().toString();
        return Character.toString(id.charAt(id.length() - 2));
    }

    /**
     * realizes actions after press button with action
     * prints action identifier to field with expression
     * sets action to calculator
     * sets value with current field current value of calculator
     *
     * @param actionEvent is action with action button
     */
    @FXML
    public void pressAction(ActionEvent actionEvent) {
        try {
            Integer value = calc.calculate(currentValue.getText());
            calc.setCurrentAction(getPartToPrint(actionEvent).charAt(0));
            currentValue.setText(value.toString());
            expression.setText(expression.getText() + " " + getPartToPrint(actionEvent) + " ");
            lastPressedWasWithAction = true;
            makeButtonsDisable(actions);
            makeButtonsNotDisable(digits);
        } catch (ArithmeticException e) {
            currentValue.setText("error");
            makeButtonsDisable(actions);
            makeButtonsDisable(digits);
        }
    }

    /**
     * realizes actions after press button '='
     * all buttons without clear are disable
     * prints in field with current value value of expression
     *
     * @param actionEvent if action with '=' button
     */
    @FXML
    public void pressCalculate(ActionEvent actionEvent) {
        makeButtonsDisable(digits);
        makeButtonsDisable(actions);
        try {
            Integer value = calc.calculate(currentValue.getText());
            currentValue.setText(value.toString());
            expression.setText(expression.getText() + " " + getPartToPrint(actionEvent) + " ");
        } catch (ArithmeticException e) {
            currentValue.setText("error");
        }
    }

    /**
     * blocks some buttons
     * @param toBlock are buttons to block
     */
    private void makeButtonsDisable(ArrayList<Button> toBlock) {
        for (Button button : toBlock) {
            button.setDisable(true);
        }
    }

    /**
     * makes buttons available
     * @param toBlock are buttons to make available
     */
    private void makeButtonsNotDisable(ArrayList<Button> toBlock) {
        for (Button button : toBlock) {
            button.setDisable(false);
        }
    }

    /**
     * realizes actions after press button '0'
     * if '0' is first digit in current value, there is no ability to print any digits before action
     *
     * @param actionEvent action with zero button
     */
    @FXML
    public void pressZero(ActionEvent actionEvent) {
        if (lastPressedWasWithAction) {
            currentValue.setText("");
        }
        expression.setText(expression.getText() + getPartToPrint(actionEvent));
        currentValue.setText(currentValue.getText() + getPartToPrint(actionEvent));
        lastPressedWasWithAction = false;
        makeButtonsNotDisable(actions);
        makeButtonsDisable(digits);
    }
}
