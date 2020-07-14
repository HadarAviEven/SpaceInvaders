package levels;
import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 *
 * @author hadar
 *
 */
public class Background1 extends Background {

    /**
     * constructor.
     * @param name the name of the level
     * @param color the given color
     */
    public Background1(String name, Color color) {
        super(name, color);
    }

    /**
     * constructor with image.
     * @param name the name of the level
     * @param img the image
     */
    public Background1(String name, Image img) {
        super(name, img);
    }

    /**
     * @param d draws the background on the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.isImage()) {
            d.drawImage(0, 20, this.img());
            d.setColor(Color.black);
            d.drawText(540, 16, "Level Name: " + this.name(), 16);
        } else {
            d.setColor(Color.black);
            d.fillRectangle((int) backgroundRect().getUpperLeft().getX(), (int) backgroundRect().getUpperLeft().getY(),
                    (int) backgroundRect().getWidth(), (int) backgroundRect().getHeight());
            d.setColor(Color.blue);
            d.drawCircle(390, 135, 100);
            d.drawCircle(390, 135, 75);
            d.drawCircle(390, 135, 50);
            d.drawLine(370, 135, 270, 135);
            d.drawLine(410, 135, 510, 135);
            d.drawLine(390, 155, 390, 255);
            d.drawLine(390, 115, 390, 35);
            d.setColor(Color.black);
            d.drawText(540, 16, "Level Name: " + this.name(), 16);
        }
    }
}
