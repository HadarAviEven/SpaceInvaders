package levels;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import sprites.BlockAlien;

/**
 *
 * @author hadar
 *
 */
public class MyBlockCreator implements BlockCreator {
    private int width;
    private int height;
    private String hitPoints;
    private Color color;
    private Image img;
    private boolean isImage;
    private Color stroke;
    private boolean thereIsStroke;
    private List<Fill> fill;

    /**
     * constructor.
     *
     * @param width the given width
     * @param height the given height
     * @param hitPoints the given hitPoints
     * @param color the given color
     * @param fill the given fill
     */
    public MyBlockCreator(int width, int height, String hitPoints, Color color, List<Fill> fill) {
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.color = color;
        this.isImage = false;
        this.thereIsStroke = false;
        this.fill = fill;
    }

    /**
     * constructor with stroke.
     *
     * @param width the given width
     * @param height the given height
     * @param hitPoints the given hitPoints
     * @param color the given color
     * @param stroke the given stroke
     * @param fill the given fill
     */
    public MyBlockCreator(int width, int height, String hitPoints, Color color, Color stroke, List<Fill> fill) {
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.color = color;
        this.stroke = stroke;
        this.isImage = false;
        this.thereIsStroke = true;
        this.fill = fill;
    }

    /**
     * constructor with image.
     *
     * @param width the given width
     * @param height the given height
     * @param hitPoints the given hitPoints
     * @param img the given img
     * @param fill the given fill
     */
    public MyBlockCreator(int width, int height, String hitPoints, Image img, List<Fill> fill) {
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.img = img;
        this.isImage = true;
        this.thereIsStroke = false;
        this.fill = fill;
    }

    /**
     * @param xpos the value of x
     * @param ypos the value of y
     * @param s the string
     * @return a new block
     */
    @Override
    public Block create(int xpos, int ypos, String s) {
        Point upperLeftPoint = new Point(xpos, ypos);
        Rectangle rect = new Rectangle(upperLeftPoint , this.width, this.height);
        if (s.equals("e")) {
            if (this.isImage) {
                return new BlockAlien(rect, this.img, this.hitPoints, this.fill);
            } else if (this.thereIsStroke) {
                return new BlockAlien(rect, this.color, this.stroke, this.hitPoints, this.fill);
            } else {
                return new BlockAlien(rect, this.color, this.hitPoints, this.fill);
            }
        } else {
            if (this.isImage) {
                return new Block(rect, this.img, this.hitPoints, this.fill);
            } else if (this.thereIsStroke) {
                return new Block(rect, this.color, this.stroke, this.hitPoints, this.fill);
            } else {
                return new Block(rect, this.color, this.hitPoints, this.fill);
            }
        }
    }
}
