package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 * @author hadar
 *
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     *
     * @param sensor the given sensor
     * @param key the given key
     * @param animation the given animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.shouldStop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d the given drawSurface
     * @param dt number of seconds.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.shouldStop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * @return if it should stop
     */
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}