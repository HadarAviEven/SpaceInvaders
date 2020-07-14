package game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.Fill;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.BlockAlien;
import sprites.Collidable;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 *
 * @author hadar
 *
 */
public class GameLevel implements Animation {
   private AnimationRunner runner;
   private boolean running;
   private SpriteCollection sprites;
   private GameEnvironment environment;
   private CounterBlocks counterBlocks;
   private CounterBalls counterBalls;
   private CounterScore score;
   private CounterLives numberOfLives;
   private BlockRemover blockRemover;
   private BallRemover ballRemover;
   private ScoreTrackingListener scoreTrackingListener;
   private Paddle paddle;
   private KeyboardSensor keyboard;
   private LevelInformation level;
   private int numberOfBlocks;
   private List<Ball> balls;
   private List<Block> blocks;
   private List<Fill> fill;
   private boolean mShouldCreateBall1 = true;
   private Timer mBallTimer1;
   private boolean mShouldCreateBall2 = true;
   private Timer mBallTimer2;
   private double maxHeight = 600;
   private Aliens aliens;
   private boolean firstTime;
   private List<BlockAlien> originalListOfAliens;
   private boolean endLevel;
   private List<Block> listOfShields;
   private List<BlockAlien> listOfAliens;

   /**
    * constructor.
    *
    * @param sprites the given sprites
    * @param environment the given environment
    * @param level the given level
    * @param keyboard the given keyboard
    * @param runner the given runner
    * @param score the given score
    * @param numberOfLives the given numberOfLives
    */
   public GameLevel(SpriteCollection sprites, GameEnvironment environment,
           LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner,
           CounterScore score, CounterLives numberOfLives) {
       this.sprites = sprites;
       this.environment = environment;
       this.level = level;
       this.keyboard = keyboard;
       this.runner = runner;
       this.score = score;
       this.numberOfLives = numberOfLives;
       this.balls = new ArrayList<Ball>();
       this.blocks = this.level.blocks();
       this.numberOfBlocks = blocks.size();
       this.firstTime = true;
       this.endLevel = false;
       this.listOfAliens = new ArrayList<BlockAlien>();
   }

   /**
    *  add the given collidable to the game.
    *
    * @param c a given collidable
    */
   public void addCollidable(Collidable c) {
       this.environment.addCollidable(c);
   }

   /**
    *  add the given sprite to the game.
    *
    * @param s a given sprite
    */
   public void addSprite(Sprite s) {
       this.sprites.addSprite(s);
   }

   /**
    * Initialize a new game.
    */
   public void initialize() {
       sprites.addSprite(this.level.getBackground());
       this.counterBlocks = new CounterBlocks(0);
       this.counterBalls = new CounterBalls(0);
       this.blockRemover = new BlockRemover(this, counterBlocks);
       this.ballRemover = new BallRemover(this, counterBalls);
       this.scoreTrackingListener = new ScoreTrackingListener(score);

       int guiWidth = 800;
//       int guiHeight = 600;
       ArrayList<Block> blocksAround = new ArrayList<Block>();

       ScoreIndicator scoreIndicator = new ScoreIndicator(score);
       LivesIndicator livesIndicator = new LivesIndicator(numberOfLives);
       scoreIndicator.addToGame(this);
       livesIndicator.addToGame(this);

       scoreIndicator.addHitListener(scoreTrackingListener);

//       int randomNumber = 25;
       Block upperBlock = new Block(new Rectangle(new Point(0, 20), guiWidth, 1),
               Color.black, "X", this.fill);
//       Block rightBlock = new Block(new Rectangle(new Point(guiWidth - randomNumber, 20), randomNumber, guiHeight),
//               Color.gray, "X", this.fill);
//       Block rightBlock = new Block(new Rectangle(new Point(guiWidth - 1, 20), 1, guiHeight),
//               Color.gray, "X", this.fill);
//       Block leftBlock = new Block(new Rectangle(new Point(0, 20), 1, guiHeight),
//               Color.gray, "X", this.fill);
//       Block deathBlock = new Block(new Rectangle(new Point(0, guiHeight), guiWidth, randomNumber),
//               Color.gray, "3", this.fill);
//
//       deathBlock.addHitListener(ballRemover);
       upperBlock.addHitListener(ballRemover);
//
       blocksAround.add(upperBlock);
//       blocksAround.add(rightBlock);
//       blocksAround.add(leftBlock);
//       blocksAround.add(deathBlock);
//
       for (int i = 0; i < blocksAround.size(); i++) {
           blocksAround.get(i).addToGame(this);
       }

       for (int i = 0; i < numberOfBlocks; i++) {
           blocks.get(i).addToGame(this);
           blocks.get(i).addHitListener(blockRemover);
           blocks.get(i).addHitListener(ballRemover);
           if (this.blocks.get(i).getCollisionRectangle().getWidth() == 40) {
               blocks.get(i).addHitListener(scoreTrackingListener);
//               this.counterBlocks.increase(1);
           }
           this.counterBlocks.increase(1);
       }

       this.originalListOfAliens = new ArrayList<BlockAlien>();
       for (int i = 0; i < this.blocks.size(); i++) {
           if (this.blocks.get(i).getCollisionRectangle().getWidth() == 40) {
               originalListOfAliens.add((BlockAlien) this.blocks.get(i));
           }
       }

       listOfShields = new ArrayList<Block>();
       for (int i = 0; i < this.blocks.size(); i++) {
           if (this.blocks.get(i).getCollisionRectangle().getWidth() != 40) {
               listOfShields.add(this.blocks.get(i));
           }
       }
       for (int i = 0; i < listOfShields.size(); i++) {
           if (listOfShields.get(i).getCollisionRectangle().getUpperLeft().getY() < maxHeight) {
               maxHeight = listOfShields.get(i).getCollisionRectangle().getUpperLeft().getY();
           }
       }
   }

   /**
    * play one turn.
    */
   public void playOneTurn() {
       this.createBallsOnTopOfPaddle();
       this.runner.run(new CountdownAnimation(2000, 3, this.sprites));
       this.running = true;
       this.runner.run(this);
   }

   /**
    *
    * @param c the collidable
    */
   public void removeCollidable(Collidable c) {
       this.environment.removeTheCollidable(c);
   }

   /**
    *
    * @param s the sprite
    */
   public void removeSprite(Sprite s) {
       this.sprites.removeTheSprite(s);
   }

   /**
    * @return if the loop should stop.
    */
   @Override
   public boolean shouldStop() {
       return (!this.running);
   }

   /**
    * @param d the given drawSurface.
    * @param dt number of seconds.
    */
   @Override
   public void doOneFrame(DrawSurface d, double dt) {
       this.sprites.drawAllOn(d);

       listOfAliens = new ArrayList<BlockAlien>();
       for (int i = 0; i < this.blocks.size(); i++) {
           if (this.blocks.get(i).getCollisionRectangle().getWidth() == 40) {
               listOfAliens.add((BlockAlien) this.blocks.get(i));
           }
       }
       aliens = new Aliens(listOfAliens, this.maxHeight);
       aliens.moveAll();
//       if (aliens.getToTheShield()) {
//           aliens.setGetToTheShield();
//           this.endLevel = true;
//           this.removeBallsFromGame();
//           this.numberOfLives.decrease(1);
//           this.running = false;
//           aliens = new Aliens(listOfAliens, this.maxHeight, new Point(25, 80));
//       }

       java.util.Random rand = new java.util.Random();
       if (mShouldCreateBall2) {
           mShouldCreateBall2 = false;
           activateTimer2();
           int randomNumber = rand.nextInt(aliens.getBottomAliens().size());
           double midPoint = aliens.getBottomAliens().get(randomNumber).getCollisionRectangle().getWidth() / 2;
           Point newAlienPoint = new Point(aliens.getBottomAliens().get(randomNumber).getCollisionRectangle().
                   getBottomLeft().getX() + midPoint, aliens.getBottomAliens().get(randomNumber).
                   getCollisionRectangle().getBottomLeft().getY());
           Ball ballAlien = new Ball(newAlienPoint, 5, java.awt.Color.red, this.environment);
           ballAlien.addToGame(this);
           ballAlien.setVelocity(-0.00001, 6);
           this.balls.add(ballAlien);
       }

       if (this.paddle.paddleBeingHit()) {
           this.endLevel = true;
           this.removeBallsFromGame();
           this.numberOfLives.decrease(1);
           this.running = false;
           aliens = new Aliens(listOfAliens, this.maxHeight, new Point(25, 80));
       }
       if (aliens.getToTheShield()) {
           aliens.setGetToTheShield();
           this.endLevel = true;
           this.removeBallsFromGame();
           this.numberOfLives.decrease(1);
           this.running = false;
           aliens = new Aliens(listOfAliens, this.maxHeight, new Point(25, 80));
       }

       this.sprites.notifyAllTimePassed(dt);

       if (listOfAliens.size() == 0) {
           this.firstTime = true;
           this.endLevel = true;
           this.running = false;
           sprites.removeTheSprite(this.level.getBackground());

           this.removeBallsFromGame();
           this.paddle.removeFromGame(this);
       }
       if (this.keyboard.isPressed("p")) {
           this.runner.run(new PauseScreen(this.keyboard));
       }
       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
           if (mShouldCreateBall1) {
               mShouldCreateBall1 = false;
               activateTimer1();
               Point newPoint = new Point((this.paddle.getCollisionRectangle().getWidth() / 2)
                       + this.paddle.getNewPoint().getX(), this.paddle.getCollisionRectangle()
                       .getUpSide().getY1() - 10);
               Ball ball = new Ball(newPoint, 3, java.awt.Color.white, this.environment);
               ball.addToGame(this);
               ball.setVelocity(0.00001, 4);
               this.balls.add(ball);
           }
       }
    }

   /**
    * creates the balls and the paddle.
    */
   public void createBallsOnTopOfPaddle() {
       int guiWidth = 800;
       int guiHeight = 600;

       if (this.firstTime) {
           this.firstTime = false;
           Rectangle paddleRect = new Rectangle(new Point(guiWidth / 2 - (this.level.paddleWidth() / 2) - 10,
                   guiHeight - 35), this.level.paddleWidth(), 20);
           this.paddle = new Paddle(keyboard, paddleRect);
           this.paddle.addHitListener(ballRemover);
           this.paddle.addToGame(this);
       } else {
           Rectangle paddleRect = this.paddle.getCollisionRectangle();
           this.paddle = new Paddle(keyboard, paddleRect);
           this.paddle.addHitListener(ballRemover);
           this.paddle.addToGame(this);
       }
   }

   /**
    * @return if the game has more blocks.
    */
   public boolean thereAreBlocks() {
       return ((listOfAliens.size() > 0) || (!this.endLevel));
   }

   /**
    * @return if the game has more lives.
    */
   public boolean thereAreLives() {
       return (this.numberOfLives.getValue() != 0);
   }

   /**
    * @return the score.
    */
   public CounterScore getScore() {
       return this.score;
   }

   /**
    * removes balls from the game.
    */
   public void removeBallsFromGame() {
       if (this.balls != null && !this.balls.isEmpty()) {
           for (Ball ball : balls) {
               ball.removeFromGame(this);
           }
       }
   }

   /**
    * @return the fill.
    */
   public List<Fill> fill() {
       return this.fill;
   }

   /**
    * activateTimer1.
    */
   private void activateTimer1() {
       if (mBallTimer1 == null) {
           mBallTimer1 = new Timer();
       }
       if (!mShouldCreateBall1) {
           mBallTimer1.schedule(new TimerTask() {
                   @Override
                   public void run() {
                      mShouldCreateBall1 = true;
                   }
               }, 350);
       }
   }

   /**
    * activateTimer2.
    */
   private void activateTimer2() {
       if (mBallTimer2 == null) {
           mBallTimer2 = new Timer();
       }
       if (!mShouldCreateBall2) {
           mBallTimer2.schedule(new TimerTask() {
                   @Override
                   public void run() {
                      mShouldCreateBall2 = true;
                   }
               }, 500);
       }
   }

   /**
    *
    * @param block the given block
    */
    public void removeFromBallList(Block block) {
        blocks.remove(block);
    }
}