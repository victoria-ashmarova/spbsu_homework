import javafx.scene.input.KeyEvent;

import javax.management.timer.Timer;
import java.util.concurrent.TimeUnit;


/**
 * Process of playing
 */
public class Game {
    private final Relief relief;
    private final Tank tank;

    private final double G = 9.8;

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

    //а еще х может за горизонт улететь
    //поточность
    private void flyingOfBall() {
        double x = tank.getGunsX();
        double y = tank.getGunsY();
        double angle = -tank.getGunsAngle();
        double speed = tank.getSpeed();

        Thread shooting = new Shooting(x, y, angle, speed);
        shooting.start();
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
                flyingOfBall();
                break;
            }
        }
    }



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

            int t = 0;
            while (y > 0) {
                tank.drawBall(x, y);
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
