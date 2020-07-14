package sprites;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import levels.Fill;

/**
 *
 * @author hadar
 *
 */
public class BlockAlien extends Block {
    private Velocity mVelocity = new Velocity(1, 0);
    private boolean getToShield = false;

    /**
     * constructor with fill.
     *
     * @param rect
     *            a given rectangle
     * @param color
     *            the given color
     * @param numberOfHits
     *            the given number
     * @param listOfFills the given listOfFills
     */
    public BlockAlien(Rectangle rect, Color color, String numberOfHits, List<Fill> listOfFills) {
        super(rect, color, numberOfHits, listOfFills);
    }

    /**
     * constructor with stroke and fill.
     *
     * @param rect
     *            a given rectangle
     * @param color
     *            the given color
     * @param stroke
     *            the given color of the stroke
     * @param numberOfHits
     *            the given number
     * @param listOfFills the given listOfFills
     */
    public BlockAlien(Rectangle rect, Color color, Color stroke, String numberOfHits, List<Fill> listOfFills) {
        super(rect, color, stroke, numberOfHits, listOfFills);
    }

    /**
     * constructor with image and fill.
     *
     * @param rect
     *            a given rectangle
     * @param img
     *            the given img
     * @param numberOfHits
     *            the given number
     * @param listOfFills the given listOfFills
     */
    public BlockAlien(Rectangle rect, Image img, String numberOfHits, List<Fill> listOfFills) {
        super(rect, img, numberOfHits, listOfFills);
    }

    /**
     * moves the alien one step.
     * @param maxHeight the given max height
     */
    public void move(double maxHeight) {
        Point newUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX()
                + mVelocity.getdX(), getCollisionRectangle().getUpperLeft().getY());
        Rectangle r = new Rectangle(newUpperLeft, getCollisionRectangle().getWidth(),
                getCollisionRectangle().getHeight());
        this.setRect(r);
        if (r.getBottomLeft().getY() >= maxHeight) {
            this.getToShield = true;
        }
    }

    /**
     * organize the aliens.
     *
     * @param distanceX the given distanceX
     * @param distanceY the given distanceY
     */
    public void organize(double distanceX, double distanceY) {
        mVelocity = new Velocity(1, 0);
        Point newUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX()
                - distanceX + mVelocity.getdX(), getCollisionRectangle().getUpperLeft().getY() - distanceY);
        Rectangle r = new Rectangle(newUpperLeft, getCollisionRectangle().getWidth(),
                getCollisionRectangle().getHeight());
        this.setRect(r);
    }

    /**
     * turn velocity to right.
     */
    public void turnVelocityRight() {
        mVelocity = new Velocity(Math.abs(mVelocity.getdX() + (0.1 * mVelocity.getdX())) , mVelocity.getdY());
    }

    /**
     * turn velocity to left.
     */
    public void turnVelocityLeft() {
        mVelocity = new Velocity(-Math.abs(mVelocity.getdX() + (0.1 * mVelocity.getdX())), mVelocity.getdY());
    }

    /**
     *
     * @param pixels the given number of pixels
     */
    public void getDownPixels(int pixels) {
        Point newUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX(),
                getCollisionRectangle().getUpperLeft().getY() - pixels);
        Rectangle r = new Rectangle(newUpperLeft, getCollisionRectangle().getWidth(),
                getCollisionRectangle().getHeight());
        this.setRect(r);
    }

    /**
     *
     * @return if the aliens get to the shields.
     */
    public boolean getToTheShield() {
        return this.getToShield;
    }

    /**
     *
     *
     */
    public void setGetToTheShield() {
        this.getToShield = false;
    }
}
