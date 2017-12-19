package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Drawing and shooting of tank
 */
public class Tank {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private double inclinationAngle = 0;
    private final double height = 10;
    private final double width = 15;
    private final double gunLength = 20;
    private final double gunDiam = 3;
    private final double littleShellDiam = 2;

    private double centerX;
    private double centerY;

    public Tank(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public void draw(double x, double y) {
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(x, y, width * 2, height * 2);
        centerX = x + width;
        centerY = y + height;
        drawGun();
    }

    private void drawGun() {
        int numberOfPoints = 4;
        double cos = Math.cos(inclinationAngle);
        double sin = Math.sin(inclinationAngle);
        double[] xPoints = new double[numberOfPoints];
        double[] yPoints = new double[numberOfPoints];
        xPoints[0] = centerX + width * cos + gunDiam * sin;
        yPoints[0] = centerY + height * sin - gunDiam * cos;
        xPoints[1] = centerX + width * cos - gunDiam * sin;
        yPoints[1] = centerY + height * sin + gunDiam * cos;
        xPoints[2] = xPoints[1] + gunLength * cos;
        yPoints[2] = yPoints[1] + gunLength * sin;
        xPoints[3] = xPoints[0] + gunLength * cos;
        yPoints[3] = yPoints[0] + gunLength * sin;
        graphicsContext.fillPolygon(xPoints, yPoints, numberOfPoints);
    }

    public void shoot() {
        //связь с углом наклона пушки
    }

    public void changeInclinationAngle(double delta) {
        inclinationAngle += delta;
        inclinationAngle = (inclinationAngle > Math.PI * 2) ? inclinationAngle - Math.PI * 2 : inclinationAngle;
        //как убрать старую пушку?
        //этим должен заниматься класс с игрой
        //не хочется перерисовывать всю пушку из-за одного ружья
    }

    /** draws shell*/
    public void drawShell(double xCenter, double yCenter) {
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillOval(xCenter, yCenter, littleShellDiam, littleShellDiam);
    }
}
