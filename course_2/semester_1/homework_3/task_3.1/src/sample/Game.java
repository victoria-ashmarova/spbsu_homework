package sample;

import javafx.scene.canvas.Canvas;

/**
 * Process of playing
 */
public class Game {
    private final Relief relief;
    private final Tank tank;

    public Game(Relief relief, Tank tank) {
        this.relief = relief;
        this.tank = tank;
    }

    public Relief getRelief() {
        return this.relief;
    }

    public Tank tank() {
        return this.tank;
    }
}
