package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

/**
 *
 * @author hadar
 *
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private double leftToCount;

    /**
     * constructor.
     *
     * @param numOfSeconds total number of seconds to display the animation
     * @param countFrom the number to begin the count down
     * @param gameScreen the given sprites of the game
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.leftToCount = countFrom;
    }

    /**
     * @param d a given drawSurface
     * @param dt number of seconds.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(java.awt.Color.red);
        d.drawText(370, 370, String.valueOf(this.countFrom), 100);
        if (this.countFrom != this.leftToCount) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor((long) ((this.numOfSeconds) / (long) this.leftToCount));
        }
        this.countFrom--;
    }

    /**
     * @return if the loop should stop.
     */
    @Override
    public boolean shouldStop() {
        return (this.countFrom == -1);
    }
}
