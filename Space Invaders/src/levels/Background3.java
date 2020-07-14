package levels;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 *
 * @author hadar
 *
 */
public class Background3 extends Background {

    /**
     * constructor with color.
     * @param name the name of the level
     * @param color the given color
     */
    public Background3(String name, Color color) {
        super(name, color);
    }

    /**
     * constructor with image.
     * @param name the name of the level
     * @param img the image
     */
    public Background3(String name, Image img) {
        super(name, img);
    }

    /**
     * @param d draws the background on the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.isImage()) {
            d.drawImage(0, 0, this.img());
            DrawWalls drawWalls = new DrawWalls(d);
            drawWalls.draw();
            d.setColor(Color.black);
            d.drawText(540, 16, "Level Name: " + this.name(), 16);
        } else {
            d.setColor(Color.green);
            d.fillRectangle((int) backgroundRect().getUpperLeft().getX(), (int) backgroundRect().getUpperLeft().getY(),
                    (int) backgroundRect().getWidth(), (int) backgroundRect().getHeight());
            d.setColor(Color.white);
            d.fillRectangle(100, 390, 90, 220);
            d.setColor(Color.black);
            d.fillRectangle(134, 310, 30, 80);
            d.fillRectangle(146, 140, 7, 170);
            d.setColor(Color.yellow);
            d.drawCircle(145 + 5, 135, 10);
            d.fillCircle(145 + 5, 135, 10);
            d.setColor(Color.red);
            d.drawCircle(145 + 5, 135, 6);
            d.fillCircle(145 + 5, 135, 6);
            d.setColor(Color.white);
            d.drawCircle(145 + 5, 135, 2);
            d.fillCircle(145 + 5, 135, 2);
            d.setColor(Color.black);
            d.fillRectangle(100, 390, 90, 7);
            d.fillRectangle(100, 390, 7, 220);
            d.fillRectangle(118, 390, 5, 220);
            d.fillRectangle(136, 390, 5, 220);
            d.fillRectangle(154, 390, 5, 220);
            d.fillRectangle(172, 390, 5, 220);
            d.fillRectangle(100, 425, 90, 6);
            d.fillRectangle(100, 455, 90, 6);
            d.fillRectangle(100, 485, 90, 6);
            d.fillRectangle(100, 515, 90, 6);
            d.fillRectangle(100, 545, 90, 6);
            d.fillRectangle(100, 575, 90, 6);
            d.fillRectangle(190, 390, 7, 220);
            d.setColor(Color.black);
            d.drawText(540, 16, "Level Name: " + this.name(), 16);
        }
    }
}