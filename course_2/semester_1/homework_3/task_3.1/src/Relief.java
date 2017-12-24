import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Drawing some relief on background
 */
public class Relief {
    private final Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Mountain> mountains;

    public Relief(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.mountains = generateMountains();
    }

    public void draw() {
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(0, 0 , canvas.getWidth(), canvas.getHeight()); //костыльно

        graphicsContext.setFill(Color.GREEN);
        for (int i = 0; i < mountains.size(); i++) {
            graphicsContext.fillPolygon(mountains.get(i).getX(), mountains.get(i).getY(), mountains.get(i).getSize());
        }
        double outsole = canvas.getHeight() * 0.9;
        graphicsContext.fillRect(0, outsole, canvas.getWidth(), canvas.getHeight() - outsole);

    }

    private ArrayList<Mountain> generateMountains() {
        ArrayList<Mountain> mountains = new ArrayList<Mountain>();
        //магическое создание гор
        mountains.add(new Mountain(200, 200, 0, 400, canvas.getHeight()));
        mountains.add(new Mountain(500, 200, 400, 600, canvas.getHeight()));
        return mountains;
    }

    private int numberOfMountain(double x) {
        int i = 0;
        boolean stopCondition = false;
        while (i < mountains.size() && !stopCondition) {
            if (x > mountains.get(i).getLeftEdge() && x < mountains.get(i).getRightEdge()) {
                stopCondition = true;
            }
            i++;
        }
        return i - 1;
    }

    public double distanceToVertex(double x) {
        Mountain mountain = mountains.get(numberOfMountain(x));
        double toPeak = Math.abs(x - mountain.getPeak());
        double toLeft = Math.abs(x - mountain.getLeftEdge());
        double toRight = Math.abs(x - mountain.getRightEdge());
        return Math.min(Math.min(toLeft, toPeak), toRight);
    }

    public double inclinationAngle(double x, boolean rightDirection) {
        Mountain mountain = mountains.get(numberOfMountain(x));
        double angle = 0;
        if (x == mountain.getPeak()) {
            angle = (rightDirection) ? mountain.getRightEdgeAngle() : mountain.getLeftEdgeAngle();
        }
        if (x > mountain.getPeak()) {
            angle = mountain.getRightEdgeAngle();
        }
        if (x < mountain.getPeak()) {
            angle = mountain.getLeftEdgeAngle();
        }
        return angle;
    }
}
