package animation;

import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Point;
import sprites.Ball;
import sprites.Paddle;

/**
 *
 * @author hadar
 *
 */
public class CreateBall implements Animation {
    private KeyboardSensor keyboard;
    private GameEnvironment environment;
    private GameLevel game;
    private List<Ball> balls;
    private Paddle paddle;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k the given keyboardSensor
     * @param environment the given environment
     * @param game the given game
     * @param balls the given balls
     * @param paddle the given paddle
     */
    public CreateBall(KeyboardSensor k, GameEnvironment environment, GameLevel game, List<Ball> balls, Paddle paddle) {
       this.keyboard = k;
       this.environment = environment;
       this.game = game;
       this.balls = balls;
       this.paddle = paddle;
       this.stop = false;
    }

    /**
     * @param d a given drawSurface
     * @param dt number of seconds.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        Point newPoint = new Point((this.paddle.getCollisionRectangle().getWidth() / 2)
                + this.paddle.getNewPoint().getX(), this.paddle.getCollisionRectangle().getUpSide().getY1() - 10);
        Ball ball = new Ball(newPoint, 3, java.awt.Color.white, this.environment);
        ball.addToGame(this.game);
        ball.setVelocity(0.00001, 4);
        this.balls.add(ball);
        this.stop = true;
    }

    /**
     * @return if the loop should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
 }
