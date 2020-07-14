package sprites;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

/**
 *
 * @author hadar
 *
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
   private biuoop.KeyboardSensor keyboard;
   private Rectangle rect;
   private Point newPoint;
   private List<HitListener> hitListeners;
   private boolean beingHit;

   /**
    * constructor.
    *
    * @param keyboard the given keyboardSensor
    * @param rect the given rectangle
    */
   public Paddle(KeyboardSensor keyboard, Rectangle rect) {
       this.keyboard = keyboard;
       this.rect = rect;
       this.newPoint = this.rect.getUpperLeft();
       this.hitListeners = new ArrayList<HitListener>();
       this.beingHit = false;
   }

   /**
    * moves the paddle one step to the left.
    * @param dt number of seconds.
    */
   public void moveLeft(double dt) {
       if (this.rect.getUpperLeft().getX() - 10 >= 0) {
           Point newUpperLeft = new Point(this.rect.getUpperLeft().getX() - 10, this.rect.getUpperLeft().getY());
           this.rect = new Rectangle(newUpperLeft, this.rect.getWidth(), this.rect.getHeight());
           this.newPoint = newUpperLeft;
       }
   }

   /**
    * moves the paddle one step to the right.
    * @param dt number of seconds.
    */
   public void moveRight(double dt) {
       if (this.rect.getUpperRight().getX() + 10 <= 800) {
           Point newUpperLeft = new Point(this.rect.getUpperLeft().getX() + 10, this.rect.getUpperLeft().getY());
           this.rect = new Rectangle(newUpperLeft, this.rect.getWidth(), this.rect.getHeight());
           this.newPoint = newUpperLeft;
       }
   }

   /**
    * notify the paddle that time has passed.
    * @param dt number of seconds.
    */
   @Override
   public void timePassed(double dt) {
       if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
           this.moveLeft(dt);
       } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
           this.moveRight(dt);
       }
   }

   /**
    * @param d draws the paddle on the given DrawSurface.
    */
   @Override
   public void drawOn(DrawSurface d) {
       d.setColor(Color.YELLOW);
       d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
               (int) rect.getWidth(), (int) rect.getHeight());
       d.setColor(Color.BLACK);
       d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
               (int) rect.getWidth(), (int) rect.getHeight());
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
   * @param hitter the given ball
   * @param collisionPoint the given collision point
   * @param currentVelocity the given current velocity
   * @return the new velocity expected after the hit
   */
   @Override
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
       Line upSide = this.rect.getUpSide();
       Line downSide = this.rect.getDownSide();
       Line leftSide = this.rect.getLeftSide();
       Line rightSide = this.rect.getRightSide();

       double xValue = upSide.start().getX();
       double yValue = upSide.start().getY();

       double eachPartOfupSide = upSide.length() / 5;

       Line upSidePart1 = new Line(xValue + 0 * eachPartOfupSide, yValue, xValue + 1 * eachPartOfupSide, yValue);
       Line upSidePart2 = new Line(xValue + 1 * eachPartOfupSide, yValue, xValue + 2 * eachPartOfupSide, yValue);
       Line upSidePart3 = new Line(xValue + 2 * eachPartOfupSide, yValue, xValue + 3 * eachPartOfupSide, yValue);
       Line upSidePart4 = new Line(xValue + 3 * eachPartOfupSide, yValue, xValue + 4 * eachPartOfupSide, yValue);
       Line upSidePart5 = new Line(xValue + 4 * eachPartOfupSide, yValue, xValue + 5 * eachPartOfupSide, yValue);

       if (upSide.isInBetween(upSide, collisionPoint)) {
           if (upSidePart1.isInBetween(upSidePart1, collisionPoint)) {
               currentVelocity = Velocity.fromAngleAndSpeed(-60, 6);
           }
           if (upSidePart2.isInBetween(upSidePart2, collisionPoint)) {
               currentVelocity = Velocity.fromAngleAndSpeed(-30, 6);
           }
           if (upSidePart3.isInBetween(upSidePart3, collisionPoint)) {
               currentVelocity = new Velocity(currentVelocity.getdX(), -currentVelocity.getdY());
           }
           if (upSidePart4.isInBetween(upSidePart4, collisionPoint)) {
               currentVelocity = Velocity.fromAngleAndSpeed(30, 6);
           }
           if (upSidePart5.isInBetween(upSidePart5, collisionPoint)) {
               currentVelocity = Velocity.fromAngleAndSpeed(60, 6);
           }
       }
       if (downSide.isInBetween(downSide, collisionPoint)) {
           currentVelocity = new Velocity(currentVelocity.getdX(), -currentVelocity.getdY());
       }
       if (leftSide.isInBetween(leftSide, collisionPoint)) {
           currentVelocity = new Velocity(-currentVelocity.getdX(), currentVelocity.getdY());
       }
       if (rightSide.isInBetween(rightSide, collisionPoint)) {
           currentVelocity = new Velocity(-currentVelocity.getdX(), currentVelocity.getdY());
       }
       if (hitter.getColor().equals(Color.red)) {
           this.beingHit = true;
       }
       return currentVelocity;
   }

   /**
    * @return if the paddle being hit.
    */
   public boolean paddleBeingHit() {
       return this.beingHit;
   }

   /**
    * Add this paddle to the game.
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
   }

   /**
    * @return the new point of the paddle.
    */
   public Point getNewPoint() {
       return this.newPoint;
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
}