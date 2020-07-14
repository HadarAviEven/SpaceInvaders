package levels;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public class DrawWalls {
    private DrawSurface d;
    private int randomNumber;
    private int guiHeight;
    private int guiWidth;
    private List<Fill> fill;

    /**
     *
     * @param d draws the background on the given DrawSurface
     */
    public DrawWalls(DrawSurface d) {
        this.d = d;
        this.randomNumber = 15;
        this.guiHeight = 600;
        this.guiWidth = 800;
    }

    /**
     * draw.
     */
    public void draw() {
//        this.fill = new Fill();
//        this.fill.addToFill("fill-1:color(white)");
        Block upperBlock = new Block(new Rectangle(new Point(0, 20), guiWidth, randomNumber),
                Color.gray, "X", this.fill);
        upperBlock.drawOn(d);
        Block rightBlock = new Block(new Rectangle(new Point(guiWidth - randomNumber, 20), randomNumber, guiHeight),
                Color.gray, "X", this.fill);
        rightBlock.drawOn(d);
        Block leftBlock = new Block(new Rectangle(new Point(0, 20), randomNumber, guiHeight),
                Color.gray, "X", this.fill);
        leftBlock.drawOn(d);
    }
}
