package levels;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 *
 * @author hadar
 *
 */
public class Background2 extends Background {

    /**
     * constructor.
     * @param name the name of the level
     * @param color the given color
     */
    public Background2(String name, Color color) {
        super(name, color);
    }

    /**
     * constructor with image.
     * @param name the name of the level
     * @param img the image
     */
    public Background2(String name, Image img) {
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
            d.setColor(Color.white);
            d.fillRectangle((int) backgroundRect().getUpperLeft().getX(), (int) backgroundRect().getUpperLeft().getY(),
                    (int) backgroundRect().getWidth(), (int) backgroundRect().getHeight());
            d.setColor(Color.yellow);
            int startNum = 15;
            int addNum = 10;
            for (int i = 0; i < 60; i++) {
              d.drawLine(115 + (i), 135, startNum + (i * addNum), 250);
            }
            d.setColor(Color.yellow);
            d.drawCircle(150, 115, 45);
            d.fillCircle(150, 115, 45);
            d.setColor(Color.orange);
            d.drawCircle(150, 115, 40);
            d.fillCircle(150, 115, 40);
            d.setColor(Color.yellow);
            d.drawCircle(150, 115, 35);
            d.fillCircle(150, 115, 35);
            d.setColor(Color.black);
            d.drawText(540, 16, "Level Name: " + this.name(), 16);
        }
    }
}