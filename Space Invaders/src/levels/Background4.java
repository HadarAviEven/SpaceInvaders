package levels;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 *
 * @author hadar
 *
 */
public class Background4 extends Background {

    /**
     * constructor with color.
     * @param name the name of the level
     * @param color the given color
     */
    public Background4(String name, Color color) {
        super(name, color);
    }

    /**
     * constructor with image.
     * @param name the name of the level
     * @param img the image
     */
    public Background4(String name, Image img) {
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
            d.setColor(Color.blue);
            d.fillRectangle((int) backgroundRect().getUpperLeft().getX(), (int) backgroundRect().getUpperLeft().getY(),
                    (int) backgroundRect().getWidth(), (int) backgroundRect().getHeight());

            d.setColor(Color.gray);
            d.drawCircle(160, 360, 25);
            d.fillCircle(160, 360, 25);

            d.drawCircle(125, 350, 21);
            d.fillCircle(125, 350, 21);

            d.drawCircle(97, 360, 18);
            d.fillCircle(97, 360, 18);

            d.drawCircle(145, 380, 18);
            d.fillCircle(145, 380, 18);

            d.drawCircle(115, 380, 19);
            d.fillCircle(115, 380, 19);

            d.drawLine(115 - 15, 380, 95 - 15, 600);
            d.drawLine(123 - 15, 380, 103 - 15, 600);
            d.drawLine(131 - 15, 380, 111 - 15, 600);
            d.drawLine(139 - 15, 380, 119 - 15, 600);
            d.drawLine(147 - 15, 380, 127 - 15, 600);
            d.drawLine(155 - 15, 380, 135 - 15, 600);
            d.drawLine(163 - 15, 380, 143 - 15, 600);
            d.drawLine(171 - 15, 380, 151 - 15, 600);
            d.drawLine(179 - 15, 380, 159 - 15, 600);
            d.drawLine(187 - 15, 380, 167 - 15, 600);

            d.drawCircle(660, 450, 25);
            d.fillCircle(660, 450, 25);

            d.drawCircle(625, 440, 21);
            d.fillCircle(625, 440, 21);

            d.drawCircle(597, 450, 18);
            d.fillCircle(597, 450, 18);

            d.drawCircle(645, 470, 18);
            d.fillCircle(645, 470, 18);

            d.drawCircle(615, 470, 19);
            d.fillCircle(615, 470, 19);

            d.drawLine(600, 500 - 30, 560 + 10, 600);
            d.drawLine(608, 500 - 30, 568 + 10, 600);
            d.drawLine(616, 500 - 30, 576 + 10, 600);
            d.drawLine(624, 500 - 30, 584 + 10, 600);
            d.drawLine(632, 500 - 30, 592 + 10, 600);
            d.drawLine(640, 500 - 30, 600 + 10, 600);
            d.drawLine(648, 500 - 30, 608 + 10, 600);
            d.drawLine(656, 500 - 30, 616 + 10, 600);
            d.drawLine(664, 500 - 30, 624 + 10, 600);
            d.drawLine(672, 500 - 30, 632 + 10, 600);

            d.setColor(Color.black);
            d.drawText(540, 16, "Level Name: " + this.name(), 16);
        }
    }
}