package animation;

import biuoop.DrawSurface;
import game.CounterScore;

/**
 *
 * @author hadar
 *
 */
public class EndScreen implements Animation {
    private CounterScore score;
    private String situation;

    /**
     * constructor.
     *
     * @param situation a given situation
     * @param score a given score
     */
    public EndScreen(String situation, CounterScore score) {
       this.score = score;
       this.situation = situation;
    }

    /**
     * @param d a given drawSurface
     * @param dt number of seconds.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.situation.equals("win")) {
            d.drawText(50 + 150, d.getHeight() / 2, "You Win! Your score is "
                    + String.valueOf(this.score.getValue()), 32);
        } else if (this.situation.equals("lose")) {
            d.drawText(50 + 150, d.getHeight() / 2, "Game Over. Your score is "
                    + String.valueOf(this.score.getValue()), 32);
        }
    }

    /**
     * @return if the loop should stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
 }
