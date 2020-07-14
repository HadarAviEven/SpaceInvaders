package listeners;

import game.CounterBalls;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private CounterBalls remainingBalls;

    /**
     * constructor.
     *
     * @param game the given game
     * @param remainingBalls current number of balls
     */
    public BallRemover(GameLevel game, CounterBalls remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * @param beingHit a given block
     * @param hitter a given ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        hitter.removeHitListener(this);
        remainingBalls.decrease(1);
    }
}
