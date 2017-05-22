import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import sample.Main;

/**
 * Class contains tests for calculator.
 */
public class CalculatorTest extends Application{
    private static Stage primaryStage;
    private static AssertionError assertion = null;

    /**
     * starts the JavaFX application and waits for assertion fails.
     */
    @Test
    public void test() {
        assertion = null;
        CalculatorTest.launch(CalculatorTest.class);
        if (assertion != null)
            throw assertion;
    }

    /**
     * finds node on scene.
     * @param selector is fx:id of the node
     * @param <T> is type of the node
     * @return node
     */
    public static <T> T findNode(String selector) {
        return (T) primaryStage.getScene().lookup(selector);
    }

    /**
     *  performs some simple actions with controls and checks if results are correct.
     */
    public void calculationTest() {
        Spinner<Double> firstSpinner = findNode("#firstSpinner");
        Spinner<Double> secondSpinner = findNode("#secondSpinner");
        ComboBox actionBox = findNode("#actionBox");
        TextField result = findNode("#resultField");

        firstSpinner.getValueFactory().setValue(15.0);
        secondSpinner.getValueFactory().setValue(3.0);

        Assert.assertTrue(result.getText().equals("18.0"));

        actionBox.getSelectionModel().select("-");
        Assert.assertTrue(result.getText().equals("12.0"));

        actionBox.getSelectionModel().select("*");
        Assert.assertTrue(result.getText().equals("45.0"));

        actionBox.getSelectionModel().select("/");
        Assert.assertTrue(result.getText().equals("5.0"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(Main.class.getResource("Calculator.fxml"));
        primaryStage.setTitle("Simple calculator");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        try {
            calculationTest();
        } catch (AssertionError e) {
            assertion = e;
            primaryStage.close();
        }

        primaryStage.close();
    }
}
