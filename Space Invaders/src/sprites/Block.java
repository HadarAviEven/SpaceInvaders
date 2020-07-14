package sprites;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import levels.Fill;
import listeners.HitListener;
import listeners.HitNotifier;

/**
 *
 * @author hadar
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private java.awt.Color color;
    private String numberOfHits;
    private List<HitListener> hitListeners;
    private boolean beingHitBefore;
    private Image img;
    private boolean isImage;
    private java.awt.Color stroke;
    private boolean thereIsStroke;
    private List<Fill> listOfFills;

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
    public Block(Rectangle rect, java.awt.Color color, String numberOfHits, List<Fill> listOfFills) {
        this.rect = rect;
        this.color = color;
        this.numberOfHits = numberOfHits;
        this.hitListeners = new ArrayList<HitListener>();
        this.beingHitBefore = false;
        this.isImage = false;
        this.thereIsStroke = false;
        this.listOfFills = listOfFills;
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
    public Block(Rectangle rect, java.awt.Color color, java.awt.Color stroke, String numberOfHits,
            List<Fill> listOfFills) {
        this.rect = rect;
        this.color = color;
        this.numberOfHits = numberOfHits;
        this.hitListeners = new ArrayList<HitListener>();
        this.beingHitBefore = false;
        this.stroke = stroke;
        this.isImage = false;
        this.thereIsStroke = true;
        this.listOfFills = listOfFills;
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
    public Block(Rectangle rect, Image img, String numberOfHits, List<Fill> listOfFills) {
        this.rect = rect;
        this.img = img;
        this.numberOfHits = numberOfHits;
        this.hitListeners = new ArrayList<HitListener>();
        this.beingHitBefore = false;
        this.isImage = true;
        this.thereIsStroke = false;
        this.listOfFills = listOfFills;
    }

    /**
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     *
     * @param hitter a given ball
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }
     }

    /**
     *
     * @param hitter
     *            the given ball
     * @param collisionPoint
     *            the given collision point
     * @param currentVelocity
     *            the given current velocity
     * @return the new velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line upSide = this.rect.getUpSide();
        Line downSide = this.rect.getDownSide();
        Line leftSide = this.rect.getLeftSide();
        Line rightSide = this.rect.getRightSide();

        double tempDx = (currentVelocity.getdX() / Math.abs(currentVelocity.getdX())) * (-0.001);
        double tempDy = (currentVelocity.getdY() / Math.abs(currentVelocity.getdY())) * (-0.001);

        if (upSide.isInBetween(upSide, collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getdX() + tempDx, -currentVelocity.getdY() + tempDy);
        }
        if (downSide.isInBetween(downSide, collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getdX() + tempDx, -currentVelocity.getdY() + tempDy);
        }
        if (leftSide.isInBetween(leftSide, collisionPoint)) {
            currentVelocity = new Velocity(-currentVelocity.getdX() + tempDx, currentVelocity.getdY() + tempDy);
        }
        if (rightSide.isInBetween(rightSide, collisionPoint)) {
            currentVelocity = new Velocity(-currentVelocity.getdX() + tempDx, currentVelocity.getdY() + tempDy);
        }
        switch (this.numberOfHits) {
        case "3":
            this.numberOfHits = "2";
            this.beingHitBefore = true;
            this.notifyHit(hitter);
            break;
        case "2":
            this.numberOfHits = "1";
            this.beingHitBefore = true;
            this.notifyHit(hitter);
            break;
        case "1":
            this.numberOfHits = "X";
            this.notifyHit(hitter);
            break;
        default:
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    /**
     *
     * @param surface
     *            draws the block on the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        if (this.listOfFills != null) {
            int size = this.listOfFills.get(0).getFillList().size();
            if (this.numberOfHits == "1") {
              String currentFill = this.listOfFills.get(0).getFillList().get(size - 1);
              this.listOfFills.get(0).setFill(currentFill);
              if (this.listOfFills.get(0).isImage()) {
                  this.isImage = true;
                  this.img = this.listOfFills.get(0).getImage();
              } else {
                  this.color = this.listOfFills.get(0).getColor();
              }
            } else if (this.numberOfHits == "2") {
                String currentFill = this.listOfFills.get(0).getFillList().get(1);
                this.listOfFills.get(0).setFill(currentFill);
                if (this.listOfFills.get(0).isImage()) {
                    this.isImage = true;
                } else {
                    this.color = this.listOfFills.get(0).getColor();
                }
            }
        }
        if (this.isImage) {
            surface.drawImage((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), this.img);
            surface.setColor(Color.black);
        } else {
            surface.setColor(this.color);
            surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
            if (this.thereIsStroke) {
                surface.setColor(this.stroke);
                surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                        (int) rect.getWidth(), (int) rect.getHeight());
            }
            surface.setColor(Color.WHITE);
        }
    }

    /**
     * notify the block that time has passed.
     * @param dt number of seconds.
     */
    @Override
    public void timePassed(double dt) {
    }

    /**
     * add the block to the game.
     *
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     *
     * @param game a given game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
        game.removeFromBallList(this);
    }

    /**
     * @param hl a given HitListener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl a given HitListener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     *
     * @return the number of the hits
     */
    public String getHitPoints() {
        return this.numberOfHits;
    }

    /**
     *
     * @return if the block had hit before.
     */
    public Boolean beingHitBefore() {
        return this.beingHitBefore;
    }

    /**
     *
     * @return the rect.
     */
    public Rectangle rect() {
        return this.rect;
    }

    /**
     *
     * @param r the given rect
     */
    public void setRect(Rectangle r) {
        this.rect = r;
    }
//
//    /**
//     * @param fill the given fill
//     */
//    public void addToListOfFills(List<String> fill) {
//        this.listOfFills.add(fill);
//    }
}
