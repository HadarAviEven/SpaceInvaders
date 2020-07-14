package levels;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

/**
 *
 * @author hadar
 *
 */
public abstract class Background implements Sprite {
    private Rectangle backgroundRect;
    private String name;
    private Image img;
    private boolean isImage;
    private Color color;

    /**
     * constructor with color.
     * @param name the name of the level
     * @param color the given color
     */
    public Background(String name, Color color) {
        this.backgroundRect = new Rectangle(new Point(16, 36), 769, 565);
        this.name = name;
        this.isImage = false;
        this.color = color;
    }

    /**
     * constructor with image.
     * @param name the name of the level
     * @param img the image
     */
    public Background(String name, Image img) {
        this.name = name;
        this.img = img;
        this.isImage = true;
    }

    /**
     * @param d draws the background on the given DrawSurface
     */
    @Override
    public abstract void drawOn(DrawSurface d);

    /**
     * notify the background that time has passed.
     * @param dt number of seconds.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * add the background to the game.
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @return if there is an img.
     */
    public boolean isImage() {
        return this.isImage;
    }

    /**
     * @return the backgroundRect.
     */
    public Rectangle backgroundRect() {
        return this.backgroundRect;
    }

    /**
     * @return the name.
     */
    public String name() {
        return this.name;
    }

    /**
     * @return the img.
     */
    public Image img() {
        return this.img;
    }

    /**
     * @return the color.
     */
    public Color color() {
        return this.color;
    }
}
