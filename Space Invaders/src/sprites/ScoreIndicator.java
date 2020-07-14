package sprites;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.CounterScore;
import game.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;

/**
 *
 * @author hadar
 *
 */
public class ScoreIndicator implements Sprite, HitNotifier {
    private CounterScore score;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param score the given score
     */
    public ScoreIndicator(CounterScore score) {
        this.score = score;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @param d draws the scoreIndicator on the given DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
      d.setColor(Color.BLACK);
      d.drawText(330, 16, "Score: " + this.score.getValue(), 16);
    }

    /**
     * notify the scoreIndicator that time has passed.
     * @param dt number of seconds.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
    * Add this scoreIndicator to the game.
    *
    * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param hl a given HitListener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl a given HitListener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
