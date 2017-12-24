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

    public Relief getRelief() {
        return this.relief;
    }

    public Tank tank() {
        return this.tank;
    }

    private void redraw() {
        this.relief.draw();
        this.tank.draw();
    }

    public void moveTank(boolean rightDirection) {
        double angle = 0;
        tank.moveTank(rightDirection, angle);
        redraw();
    }

    public void changeInclinationAngle(boolean clockWiseDirection) {
        tank.gunRotate(clockWiseDirection);
        redraw();
    }

    public void shoot() {
        //аааааааааааа
    }


    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP: {
                changeInclinationAngle(true);
                break;
            }
            case DOWN: {
                changeInclinationAngle(false);
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
