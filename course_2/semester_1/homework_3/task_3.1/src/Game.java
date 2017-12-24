import javafx.scene.input.KeyEvent;

/**
 * Process of playing
 */
public class Game {
    private final Relief relief;
    private final Tank tank;

    public Game(Relief relief, Tank tank) {
        this.relief = relief;
        this.tank = tank;
        redraw();
    }

    private void redraw() {
        this.relief.draw();
        this.tank.draw();
    }

    private void moveTank(boolean rightDirection) {
        double angle = relief.inclinationAngle(tank.getCenterX(), rightDirection);
        double distanceToVertex = relief.distanceToVertex(tank.getCenterX());
        tank.moveTank(rightDirection, angle, distanceToVertex);
        redraw();
    }

    private void gunRotate(boolean clockWiseDirection) {
        tank.gunRotate(clockWiseDirection);
        redraw();
    }

    private void shoot() {
        //аааааааааааа
    }


    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP: {
                gunRotate(true);
                break;
            }
            case DOWN: {
                gunRotate(false);
                break;
            }
            case RIGHT: {
                moveTank(true);
                break;
            }
            case LEFT: {
                moveTank(false);
                break;
            }
            case ENTER: {
                shoot();
                break;
            }
        }
    }
}
