package sprites;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

/**
 *
 * @author hadar
 *
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param center the center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param gameEnvironment the gameEnvironment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     *
     * @param i the x coordinate
     * @param j the y coordinate
     * @param k the radius of the ball
     * @param color the color
     * @param gameEnvironment the gameEnvironment of the ball
     */
    public Ball(int i, int j, int k, java.awt.Color color, GameEnvironment gameEnvironment) {
        this(new Point(i, j), k, color, gameEnvironment);
    }

    /**
     *
     * @return the x coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     *
     * @return the y coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     *
     * @return the size of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     *
     * @param surface draws the ball on the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), r);
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), r);
    }

    /**
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     *
     * @param dx the change of x values
     * @param dy the change of y values
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * notify the ball that time has passed.
     * @param dt number of seconds.
     */
    @Override
    public void timePassed(double dt) {
      Point trajectoryStartPoint = new Point(center.getX() + r, center.getY() + r);
      Point trajectoryEndPoint = new Point(center.getX() + velocity.getdX() + r, center.getY() + velocity.getdY() + r);
      Line trajectory = new Line(trajectoryStartPoint, trajectoryEndPoint);
      CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
      if (collisionInfo.collisionPoint() == null) {
          center = this.getVelocity().applyToPoint(center, dt);
      } else {
          Point newCollisionPoint = new Point(collisionInfo.collisionPoint().getX(),
                  collisionInfo.collisionPoint().getY());
          Velocity newVelocity = collisionInfo.collisionObject().hit(this, newCollisionPoint, velocity);
          velocity = newVelocity;
      }
    }

    /**
     * add the ball to the game.
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     *
     * @param game a given game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
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