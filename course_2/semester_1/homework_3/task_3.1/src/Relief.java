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
        mountains.add(new Mountain(150, 200, 0, 300, canvas.getHeight()));
        mountains.add(new Mountain(500, 200, 400, 600, canvas.getHeight()));
        return mountains;
    }

    private int numberOfMountain(double x) {
        int i = 0;
        boolean mountainFound = false;
        boolean mountainNotFound = false;
        if (x < mountains.get(i).getLeftEdge()) {
            return -1;
        }
        while (i < mountains.size() && !mountainFound && !mountainNotFound) {
            if (x >= mountains.get(i).getLeftEdge() && x <= mountains.get(i).getRightEdge()) {
                mountainFound = true;
            }
            if (x > mountains.get(i).getRightEdge()) {
                if (i == mountains.size() - 1) {
                    mountainNotFound = true;
                } else {
                    if (x < mountains.get(i + 1).getLeftEdge()) {
                        mountainNotFound = true;
                    }
                }
            }
            i++;
        }
        return (mountainNotFound) ? -1 : i - 1;
    }

    public double inclinationAngle(double x, boolean rightDirection) {
        int number = numberOfMountain(x);
        if (number == -1) {
            return 0;
        }
        Mountain mountain = mountains.get(number);
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

    public double distanceToVertex(double x) {
        double min = canvas.getWidth();
        for (Mountain mountain : mountains) {
            double distToLeft = Math.abs(x - mountain.getLeftEdge());
            double distToRight = Math.abs(x - mountain.getRightEdge());
            double distToPeak = Math.abs(x - mountain.getPeak());
            min = Math.min(Math.min(min, distToPeak), Math.min(distToLeft, distToRight));
        }
        return min;
    }
}
