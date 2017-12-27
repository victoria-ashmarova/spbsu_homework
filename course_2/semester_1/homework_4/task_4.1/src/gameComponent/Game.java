package gameComponent;

import javafx.scene.input.KeyEvent;
import networkComponent.Communicable;

/**
 * Process of playing
 */
public class Game {
    private final Relief relief;
    private final Tank tank;
    private Communicable communicable;

    //todo второй танк
    private Tank secondTank;

    private final double G = 9.8;

    public Game(Relief relief, Tank tank) {
        this.relief = relief;
        this.tank = tank;
        redraw();
    }

    public void setCommunicable(Communicable communicable) {
        this.communicable = communicable;
    }

    public void handleAction(Action action) {
        //todo
    }

    /**
     * draws background and tank
     */
    private void redraw() {
        this.relief.draw();
        this.tank.draw();
    }

    /**
     * moves tank according to background
     * @param rightDirection is true, when tank must go with right direction
     */
    private void moveTank(boolean rightDirection) {
        double angle = relief.inclinationAngle(tank.getCenterX(), rightDirection);
        double distanceToVertex = relief.distanceToVertex(tank.getCenterX());
        tank.moveTank(rightDirection, angle, distanceToVertex);
        redraw();
    }

    /**
     * rotates gun of tank
     * @param clockWiseDirection is true, when gun of tank must go with clockwise direction
     */
    private void gunRotate(boolean clockWiseDirection) {
        tank.gunRotate(clockWiseDirection);
        redraw();
    }

    /**
     * implements moving of ball from tank
     */
    private void flyingOfBall() {
        double x = tank.getGunsX();
        double y = tank.getGunsY();
        double angle = -tank.getGunsAngle();
        double speed = tank.getSpeed();

        Thread shooting = new Shooting(x, y, angle, speed);
        shooting.start();
    }

    /**
     * handler for key events
     */
    public void handleKeyPress(KeyEvent e) {  //todo добавить обработку для коммуникабельного
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
                flyingOfBall();
                break;
            }
        }
    }

    /**
     * implementation of shooting
     */
    private class Shooting extends Thread{
        private double x;
        private double y;
        private double angle;
        private double speed;

        public Shooting(double x, double y, double angle, double speed) {
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.speed = speed;
        }

        @Override
        public void run() {
            double stepX = speed * Math.cos(angle);
            double startSpeedY = speed * Math.sin(angle);
            boolean stopCondition = false;
            int t = 0;
            while (!stopCondition) {
                stopCondition = !tank.drawBall(x, y) || relief.isMountainPoint(x, y);
                t++;
                x += stepX;

                double stepY = startSpeedY - G * t / 2;
                y += stepY;

                try {
                    this.sleep(100);
                } catch (InterruptedException e) {}

                redraw();
            }
        }
    }
}
