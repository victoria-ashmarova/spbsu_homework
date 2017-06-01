package ashmarova.task_2_7_2.sample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class contains tests for class with game
 */
public class GameTest {
    /**
     * checks, that one label on field doesn't create finish of game
     */
    @Test
    public void fieldWithOneElementTest() {
        Game game = new Game();
        game.setState(2, "X");
        assertTrue(game.checkWin(2) == null);
    }

    /**
     * checks correct definition of winner, if it took horizontal
     */
    @Test
    public void fieldWithHorizontalTest() {
        Game game = new Game();
        game.setState(0, "X");
        game.setState(1, "X");
        game.setState(2, "X");
        assertTrue(game.checkWin(2) == "X");
    }

    /**
     * checks correct definition of winner, if it took vertical
     */
    @Test
    public void fieldWithVerticalTest() {
        Game game = new Game();
        game.setState(1, "O");
        game.setState(4, "O");
        game.setState(7, "O");
        assertTrue(game.checkWin(1) == "O");
    }

    /**
     * checks correct definition of winner, if it took left and up diagonal
     */
    @Test
    public void fieldWithLeftUpDiagonalTest() {
        Game game = new Game();
        game.setState(0, "O");
        game.setState(4, "O");
        game.setState(8, "O");
        assertTrue(game.checkWin(4) == "O");
    }

    /**
     * checks correct definition of winner, if it took right and up diagonal
     */
    @Test
    public void fieldWithRightUpDiagonalTest() {
        Game game = new Game();
        game.setState(2, "X");
        game.setState(4, "X");
        game.setState(6, "X");
        assertTrue(game.checkWin(4) == "X");
    }
}