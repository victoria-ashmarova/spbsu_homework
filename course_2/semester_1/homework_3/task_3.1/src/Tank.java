import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Keeps information about tank
 */
public class Tank {
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    private final double height = 10;
    private final double width = 15;
    private final double gunLength = 20;
    private final double gunDiam = 3;

    private final double step = 10;
    private final double angleDelta = Math.PI / 6;

    private double centerX;
    private double centerY;
    private double gunsAngle = 0;

    private final double speed = 30;
    private final double littleBallsDiam = 6;

    public Tank(Canvas canvas, double startX, double startY) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.centerX = startX;
        this.centerY = startY;
    }

    /**
     * draw tank on canvas
     */
    public void draw() {
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(centerX - width, centerY - height, width * 2, height * 2);
        drawGun();
    }

    /**
     * draws gun of tank on canvas
     */
    private void drawGun() {
        int numberOfPoints = 4;
        double cos = Math.cos(gunsAngle);
        double sin = Math.sin(gunsAngle);
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


    /**
     * change gun's angle of inclination
     * @param clockWiseDirection is true if gun must go with clockwise direction
     */
    public void gunRotate(boolean clockWiseDirection) {
        gunsAngle += (clockWiseDirection) ? -angleDelta : angleDelta;
        gunsAngle = (gunsAngle > Math.PI * 2) ? gunsAngle - Math.PI * 2 : gunsAngle;
    }

    /**
     * changes location of tank
     * @param rightDirection is true, when tank must go with right direction
     * @param angleOfInclination is angle of inclination of mountain, where tank is located
     * @param distanceToVertex is distance between tank and closes vertex of mountain
     */
    public void moveTank(boolean rightDirection, double angleOfInclination, double distanceToVertex) {
        double xStep = 0;
        double yStep = 0;

        if (distanceToVertex != 0 && step > distanceToVertex) {
            xStep = distanceToVertex;
            yStep = distanceToVertex * Math.tan(angleOfInclination);
        } else {
            xStep = step * Math.cos(angleOfInclination);
            yStep = step * Math.sin(angleOfInclination);
        }

        double newCenterX = (rightDirection) ? centerX + xStep : centerX - xStep;
        double newCenterY = (rightDirection) ? centerY + yStep : centerY - yStep;

        if (newCenterX < width || newCenterX > canvas.getWidth() - width) {
            return;
        }

        if (newCenterY > canvas.getHeight() - height) {
            centerX = newCenterX;
            return;
        }

        centerX = newCenterX;
        centerY = newCenterY;
    }

    public double getCenterX() {
        return this.centerX;
    }

    public double getGunsAngle() {
        return this.gunsAngle;
    }

    public double getGunsX() {
        return centerX + (width + gunLength) * Math.cos(gunsAngle);
    }

    public double getGunsY() {
        return canvas.getHeight() - (centerY + (height + gunLength) * Math.sin(gunsAngle));
    }

    public double getSpeed() {
        return this.speed;
    }

    /**
     *
     * @param x is x coordinate of center of ball
     * @param y is y coordinate of center of ball
     * @return true if there is ability to draw ball
     */
    public boolean drawBall(double x, double y) {
        if (x < 0 || x > canvas.getWidth() || y < 0) {
            return false;
        }

        graphicsContext.setFill(Color.PURPLE);
        graphicsContext.fillOval(x, canvas.getHeight() - y, littleBallsDiam, littleBallsDiam);
        return true;
    }
}
