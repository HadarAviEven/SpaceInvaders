package levels;

import java.util.List;

import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 *
 * @author hadar
 *
 */
public interface LevelInformation {

    /**
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     *
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     *
     * @return the blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     *
     * @return the number of levels that should be removed.
     */
    int numberOfBlocksToRemove();
 }
