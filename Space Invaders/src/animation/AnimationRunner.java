package animation;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 *
 * @author hadar
 *
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * constructor.
     *
     * @param gui the given gui
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.gui = gui;
    }

    /**
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     *
     * @param animation the given animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, (double) 1 / this.framesPerSecond);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
 }