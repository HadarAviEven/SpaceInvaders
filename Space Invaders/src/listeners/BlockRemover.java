package listeners;

import game.CounterBlocks;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public class BlockRemover implements HitListener {
   private GameLevel game;
   private CounterBlocks remainingBlocks;

   /**
    * constructor.
    *
    * @param game the given game
    * @param remainingBlocks current number of blocks
    */
   public BlockRemover(GameLevel game, CounterBlocks remainingBlocks) {
       this.game = game;
       this.remainingBlocks = remainingBlocks;
   }

   /**
    * @param beingHit a given block
    * @param hitter a given ball
    */
   @Override
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() == "X") {
           beingHit.removeFromGame(game);
           beingHit.removeHitListener(this);
           remainingBlocks.decrease(1);
       }
   }
}