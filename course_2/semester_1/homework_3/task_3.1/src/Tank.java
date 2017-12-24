import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Drawing and shooting of tank
 */
public class Tank {
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    private final double height = 10;
    private final double width = 15;
    private final double gunLength = 20;
    private final double gunDiam = 3;
    private final double littleShellDiam = 2;

    private final double step = 10;
    private final double angleDelta = Math.PI / 4;

    private double centerX;
    private double centerY;
    private double inclinationAngle = 0;

    public Tank(Canvas canvas, double startX, double startY) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.centerX = startX;
        this.centerY = startY;
    }

    public void draw() {
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(centerX - width, centerY - height, width * 2, height * 2);
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

    public void gunRotate(boolean clockWiseDirection) {
        inclinationAngle += (clockWiseDirection) ? -angleDelta : angleDelta;
        inclinationAngle = (inclinationAngle > Math.PI * 2) ? inclinationAngle - Math.PI * 2 : inclinationAngle;
    }

    public void moveTank(boolean rightDirection, double angleOfInclination) {
        double xStep = step * Math.cos(angleOfInclination);
        double yStep = step * Math.sin(angleOfInclination);
        centerX += (rightDirection) ? xStep : -xStep;
        centerY += (rightDirection) ? yStep : -yStep;
    }

    /** draws shell*/
    public void drawShell(double xCenter, double yCenter) {
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillOval(xCenter, yCenter, littleShellDiam, littleShellDiam);
    }
}
