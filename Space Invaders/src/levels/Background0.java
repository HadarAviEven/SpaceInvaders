package levels;
import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 *
 * @author hadar
 *
 */
public class Background0 extends Background {

    /**
     * constructor with color.
     *
     * @param name the name of the level
     * @param color the given color
     */
    public Background0(String name, Color color) {
        super(name, color);
    }

    /**
     * constructor with image.
     * @param name the name of the level
     * @param img the image
     */
    public Background0(String name, Image img) {
        super(name, img);
    }

    /**
     * @param d draws the background on the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.isImage()) {
            if ((this.name().equals("hello")) || (this.name().equals("Square Moon"))) {
                d.drawImage(0, 0, this.img());
            } else {
                d.drawImage(0, 20, this.img());
            }
            d.setColor(Color.black);
            if (!this.name().equals("hello")) {
                d.drawText(510, 16, "Level Name: " + this.name(), 16);
            }
        } else {
            d.setColor(this.color());
            d.fillRectangle(0, 20, 800, 600);
            if (this.name().equals("Direct Hit")) {
                d.setColor(Color.blue);
                d.drawCircle(390, 135, 100);
                d.drawCircle(390, 135, 75);
                d.drawCircle(390, 135, 50);
                d.drawLine(370, 135, 270, 135);
                d.drawLine(410, 135, 510, 135);
                d.drawLine(390, 155, 390, 255);
                d.drawLine(390, 115, 390, 35);
            } else if (this.name().equals("Wide Easy")) {
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
            } else if (this.name().equals("Green 3")) {
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
            } else if (this.name().equals("Final 4")) {
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
            }
            d.setColor(Color.black);
            d.drawText(500, 16, "Level Name: " + this.name(), 16);
        }
    }
}