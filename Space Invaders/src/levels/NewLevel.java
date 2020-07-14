package levels;

import java.util.ArrayList;
import java.util.List;

import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 *
 * @author hadar
 *
 */
public class NewLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * constructor.
     *
     * @param initialBallVelocities the given initialBallVelocities
     * @param paddleSpeed the given paddleSpeed
     * @param paddleWidth the given paddleWidth
     * @param levelName the given levelName
     * @param background the given background
     * @param blocks the given blocks
     * @param numberOfBlocksToRemove the given numberOfBlocksToRemove
     */
    public NewLevel(List<Velocity> initialBallVelocities, int paddleSpeed, int paddleWidth,
            String levelName, Sprite background, List<Block> blocks, int numberOfBlocksToRemove) {
        this.initialBallVelocities = initialBallVelocities;
//        this.numberOfBalls = this.initialBallVelocities.size();
        this.numberOfBalls = 1;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.blocks = blocks;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
    }

    /**
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * @return the initial velocity of each ball.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        this.initialBallVelocities = new ArrayList<Velocity>();
        initialBallVelocities.add(new Velocity(0.000001, 7));
        return this.initialBallVelocities;
    }

    /**
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return the blocks that make up this level, each block contains
     * its size, color and location.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return the number of levels that should be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
