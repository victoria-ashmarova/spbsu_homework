package sample;

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
    ArrayList<Mountain> mountains;

    public Relief(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.mountains = generateMountains();
    }

    public ArrayList<Mountain> getMountains() {
        return this.mountains;
    }

    public void draw() {
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(0, 0 , canvas.getWidth(), canvas.getHeight()); //костыльно
        graphicsContext.setFill(Color.GREEN);
        for (int i = 0; i < mountains.size(); i++) {
            graphicsContext.fillPolygon(mountains.get(i).getX(), mountains.get(i).getY(), mountains.get(i).getSize());
        }
    }

    //здесь их производство и захардкордим
    private ArrayList<Mountain> generateMountains() {
        ArrayList<Mountain> mountains = new ArrayList<Mountain>();
        //магическое создание гор
        double height = canvas.getHeight();
        mountains.add(new Mountain(100, 300, 0, 300, height));
        mountains.add(new Mountain(400, 200, 250, 500, height));
        mountains.add(new Mountain(500, 100, 400, 600, height));
        return mountains;
    }
}
