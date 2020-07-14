package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 * @author hadar
 *
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k a given keyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
       this.keyboard = k;
       this.stop = false;
    }

    /**
     * @param d a given drawSurface
     * @param dt number of seconds.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
       d.drawText(10 + 150, d.getHeight() / 2, "paused -- press space to continue", 32);
       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
           this.stop = true;
       }
    }

    /**
     * @return if the loop should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
 }