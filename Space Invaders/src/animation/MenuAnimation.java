package animation;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levels.Background0;
import sprites.Sprite;

/**
 *
 * @author hadar
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<T> returnValue;
    private int place = -1;
    private Map<String, String> selections;
    private T val;
    private String title;
    private KeyboardSensor sensor;
    private Boolean shouldStop;
    private Sprite background;
    private Image img;
    private Menu<T> sub;
    private Boolean stop;

    /**
     * constructor.
     *
     * @param title the given title
     * @param sensor the given keyboardSensor
     * @throws IOException an exception.
     */
    public MenuAnimation(String title, KeyboardSensor sensor) throws IOException {
        this.returnValue = new ArrayList<T>();
        this.selections = new HashMap<String, String>();
        this.title = title;
        this.sensor = sensor;
        this.shouldStop = false;
        this.img = ImageIO.read(new File("resources/background_images/pink.jpg"));
        this.background = new Background0("hello", img);
        this.val = null;
        this.stop = false;
    }

    /**
     * @param d a given drawSurface
     * @param dt number of seconds.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        int i = 1;
        boolean getOut = false;
        this.background.drawOn(d);
        d.setColor(Color.black);
        d.drawText(195, 140, this.title, 100);
        int j = 0;
        while (!getOut) {
            for (String key: this.selections.keySet()) {
                if ((i == 1) && (this.selections.get(key).contains("Start Game"))) {
                    d.drawText(300, 230 + j, this.selections.get(key), 25);
                    j += 50;
                    i++;
                } else if ((i == 2) && (this.selections.get(key).contains("High Scores"))) {
                    d.drawText(300, 230 + j, this.selections.get(key), 25);
                    j += 50;
                    i++;
                } else if ((i == 3) && (this.selections.get(key).contains("Quit"))) {
                    d.drawText(300, 230 + j, this.selections.get(key), 25);
                    j += 50;
                    i++;
                    getOut = true;
                }
            }
        }
        if (this.sensor.isPressed("s")) {
            this.val = this.returnValue.get(0);
            this.shouldStop = true;
        } else if (this.sensor.isPressed("h")) {
            this.val = this.returnValue.get(1);
            this.shouldStop = true;
        } else if (this.sensor.isPressed("q")) {
            this.val = this.returnValue.get(2);
            this.shouldStop = true;
        }
    }

    /**
     * @return if the loop should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    /**
     * @return if the loop should stop.
     */
    public boolean stop() {
        return this.stop;
    }

    /**
     * @param key the given key
     * @param message the given message
     * @param returnVal the given return value
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.place++;
        this.selections.put(key, message);
        this.returnValue.add(this.place, returnVal);
    }

    /**
     * @return the status.
     */
    @Override
    public T getStatus() {
        return this.val;
    }

    /**
     * @return the background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @param key the given key to stop
     * @param message the given message
     * @param subMenu the given subMenu
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.sub = subMenu;
        this.sub.addSelection(key, message, val);
    }
}
