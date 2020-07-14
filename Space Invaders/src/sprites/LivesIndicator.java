package sprites;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.CounterLives;
import game.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;

/**
 *
 * @author hadar
 *
 */
public class LivesIndicator implements Sprite, HitNotifier {
    private CounterLives lives;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param lives the given lives
     */
    public LivesIndicator(CounterLives lives) {
        this.lives = lives;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @param d draws the scoreIndicator on the given DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
      d.setColor(Color.BLACK);
      d.drawText(140, 16, "Lives: " + this.lives.getValue(), 16);
    }

    /**
     * notify that time has passed.
     * @param dt number of seconds.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
    * Add this LivesIndicator to the game.
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
