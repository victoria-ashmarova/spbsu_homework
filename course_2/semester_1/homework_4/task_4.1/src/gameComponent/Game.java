package gameComponent;

import javafx.scene.input.KeyEvent;
import networkComponent.Connectable;
import networkComponent.DisableConnectionException;

import java.io.IOException;

/**
 * Process of playing
 */
public class Game {
    private final Relief relief;
    private final Tank localTank;
    private Connectable connectable;

    private final Tank remoteTank;
    private Tank actingTank;

    private volatile boolean stopGame = false; //остановка игры при попадаении ядром в танк

    private final double G = 9.8;

    /**
     * creates game with two tanks
     */
    public Game(Relief relief, Tank localTank, Tank remoteTank) {
        this.relief = relief;
        this.localTank = localTank;
        this.remoteTank = remoteTank;
        redraw();

        this.actingTank = localTank;
    }

    /**
     * adds component to connect with remote player
     */
    public void setConnectable(Connectable connectable) {
        this.connectable = connectable;
    }

    /**
     * initiates connection with remote player
     */
    public void initConnection() {
       Thread com = new Thread(connectable);
       com.start();
    }

    public void closeCommunication() throws DisableConnectionException {
        stopGame = true;
        connectable.close();
    }

    /**
     * sends action to do for remote player
     * @param action to handle by other player
     * @throws DisableConnectionException when there is no abitily to send request
     */
    private void sendRequest(Action action) throws DisableConnectionException {
        try {
            connectable.sendRequest(action);
        } catch (IOException e) {
            throw new DisableConnectionException("Couldn't connect with other player");
        }
    }

    /**
     * draws background and localTank
     */
    private void redraw() {
        this.relief.draw();
        this.localTank.draw();
        this.remoteTank.draw();
    }

    /**
     * moves localTank according to background
     * @param rightDirection is true, when localTank must go with right direction
     */
    private void moveTank(boolean rightDirection) {
        double angle = relief.inclinationAngle(actingTank.getCenterX(), rightDirection);
        double distanceToVertex = relief.distanceToVertex(actingTank.getCenterX());
        actingTank.moveTank(rightDirection, angle, distanceToVertex);
        redraw();
    }

    /**
     * rotates gun of localTank
     * @param clockWiseDirection is true, when gun of localTank must go with clockwise direction
     */
    private void gunRotate(boolean clockWiseDirection) {
        actingTank.gunRotate(clockWiseDirection);
        redraw();
    }

    /**
     * implements moving of ball from localTank
     */
    private void flyingOfBall() {
        double x = actingTank.getGunsX();
        double y = actingTank.getGunsY();
        double angle = -actingTank.getGunsAngle();
        double speed = actingTank.getSpeed();

        Thread shooting = new Shooting(x, y, angle, speed);
        shooting.start();
    }

    /**
     * handler for key events
     */
    public void handleKeyPress(KeyEvent e) throws DisableConnectionException {
        if (stopGame) {
            return;
        }
        actingTank = localTank;
        switch (e.getCode()) {
            case UP: {
                gunRotate(true);
                sendRequest(Action.ROTATE_RIGHT);
                break;
            }
            case DOWN: {
                gunRotate(false);
                sendRequest(Action.ROTATE_LEFT);
                break;
            }
            case RIGHT: {
                moveTank(true);
                sendRequest(Action.MOVE_RIGHT);
                break;
            }
            case LEFT: {
                moveTank(false);
                sendRequest(Action.MOVE_LEFT);
                break;
            }
            case ENTER: {
                flyingOfBall();
                sendRequest(Action.SHOOT);
                break;
            }
            case SHIFT: {
                actingTank.changeBall();
                sendRequest(Action.CHANGE_BALL);
                break;
            }
        }
    }

    /**
     * handles remote requests
     * @param action to handle
     */
    public void handleRequest(Action action) {
        actingTank = remoteTank;
        switch (action) {
            case ROTATE_RIGHT: {
                gunRotate(true);
                break;
            }
            case ROTATE_LEFT: {
                gunRotate(false);
                break;
            }
            case MOVE_RIGHT: {
                moveTank(true);
                break;
            }
            case MOVE_LEFT: {
                moveTank(false);
                break;
            }
            case SHOOT: {
                flyingOfBall();
                break;
            }
            case CHANGE_BALL: {
                actingTank.changeBall();
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

        /**
         * checking of hitting one tank by other's ball
         */
        private boolean hitting(double diam) {
            Tank targetTank = (actingTank == localTank) ? remoteTank : localTank;

            double thatX = targetTank.getCenterX();
            double thatY = targetTank.getCenterY();
            double distance = actingTank.getFunnelDiam(diam) / 2;

            return (thatX - x) * (thatX - x) + (thatY - y) * (thatY - y) <= distance * distance;
        }

        @Override
        public void run() {
            double stepX = speed * Math.cos(angle);
            double startSpeedY = speed * Math.sin(angle);
            double diam = actingTank.getCurrentDiamOfBall();
            boolean stopCondition = stopGame;
            int t = 0;
            while (!stopCondition) {
                stopCondition = !actingTank.drawBall(x, y, diam) || relief.isMountainPoint(x, y) || stopGame;
                t++;
                x += stepX;

                double stepY = startSpeedY - G * t / 2;
                y += stepY;

                try {
                    this.sleep(100);
                } catch (InterruptedException e) {}

                redraw();

                stopCondition = stopCondition || hitting(diam) || stopGame;
            }

            if (hitting(diam)) {
                stopGame = true;
                actingTank.drawFunnel(x, y, diam);
                localTank.printResult(actingTank == localTank);
                connectable.stopCommunication();
            }
        }
    }
}
