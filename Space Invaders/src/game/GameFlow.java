package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import levels.LevelSpecificationReader;
import sprites.SpriteCollection;

/**
 *
 * @author hadar
 *
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private CounterScore score;
    private CounterLives numberOfLives;
    private String situation;
    private List<String> newArgs;
    private boolean thereAreArgs;
    private HighScoresTable highScores;
    private File scoreFile;
    private int currentScore;
    private DialogManager dialog;
    private SetUpGame setUpGame;
    private boolean winning;

    /**
     * constructor with no args.
     *
     * @param ar the given animation runner
     * @param ks the given keyboard sensor
     * @param sprites the given sprites
     * @param environment the given environment
     * @param scoreFile the given file
     * @param dialog the given dialog
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, SpriteCollection sprites,
            GameEnvironment environment, File scoreFile, DialogManager dialog) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.sprites = sprites;
        this.environment = environment;
//        if (!winning) {
//            this.score = new CounterScore(0);
//            this.numberOfLives = new CounterLives(3);
//        }
        this.thereAreArgs = false;
        this.scoreFile = scoreFile;
        this.highScores = HighScoresTable.loadFromFile(scoreFile);
        this.dialog = dialog;
        this.winning = false;
    }

    /**
     * constructor with args.
     *
     * @param ar the given animation runner
     * @param ks the given keyboard sensor
     * @param sprites the given sprites
     * @param environment the given environment
     * @param newArgs the given args from the cmd
     * @param scoreFile the given file
     * @param dialog the given dialog
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, SpriteCollection sprites,
            GameEnvironment environment, List<String> newArgs, File scoreFile, DialogManager dialog) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.sprites = sprites;
        this.environment = environment;
//        if (!winning) {
//            this.score = new CounterScore(0);
//            this.numberOfLives = new CounterLives(3);
//        }
        this.newArgs = newArgs;
        this.thereAreArgs = true;
        this.scoreFile = scoreFile;
        this.highScores = HighScoresTable.loadFromFile(scoreFile);
        this.dialog = dialog;
        this.winning = false;
    }

    /**
     *
     * @param levels a list of the levels game.
     * @throws IOException an exception;
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {
        List<LevelInformation> list = new ArrayList<LevelInformation>();

        String path = null;
        if (this.thereAreArgs) {
            path = newArgs.get(0);
        } else {
            path = "my_resources/definitions/level_definitions.txt";
        }
        BufferedReader br = null;
        FileReader fr = null;
        fr = new FileReader(path);
        br = new BufferedReader(fr);
        if (this.thereAreArgs) {
            path = "resources/";
        } else {
            path = "my_resources/";
        }
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader(path);
        list = levelSpecificationReader.fromReader(br);
        br.close();
        fr.close();

        for (int i = 0; i < list.size(); i++) {
            levels.add(list.get(i));
        }

        if (!this.winning) {
            this.setUpGame = new SetUpGame(this.animationRunner, this.keyboardSensor, highScores);
            if (!setUpGame.go()) {
                return;
            }
        }
        int countLevels = 0;
        for (LevelInformation levelInfo : levels) {
          countLevels++;
          GameLevel level = new GameLevel(this.sprites, this.environment, levelInfo,
                  this.keyboardSensor, this.animationRunner, this.score, this.numberOfLives);
          level.initialize();
          while (level.thereAreBlocks() && level.thereAreLives()) {
             level.playOneTurn();
          }
          if (!level.thereAreLives()) {
              this.situation = "lose";
              this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                      new EndScreen(this.situation, level.getScore())));
              this.currentScore = level.getScore().getValue();
              this.winning = false;
              break;
          }
          if ((countLevels == levels.size())) {
              this.winning = true;
          }
       }
        if (!this.winning) {
            if ((this.highScores.getRank(this.currentScore) >= 1)
                    && (this.highScores.getRank(this.currentScore) <= this.highScores.size())) {
                String namePlayer = this.dialog.showQuestionDialog("Name", "What is your name?", "");
                ScoreInfo player = new ScoreInfo(namePlayer, this.currentScore);
                this.highScores.add(player);
                this.highScores.save(scoreFile);
            }
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                    new HighScoresAnimation(this.highScores)));
        }
    }

    /**
     * @return the value of winning.
     */
    public boolean winning() {
        return this.winning;
    }

    /**
     * @return the score.
     */
    public CounterScore getScore() {
        return this.score;
    }

    /**
     * @return the number of lives.
     */
    public CounterLives getLives() {
        return this.numberOfLives;
    }

    /**
     * set the score.
     * @param s the given score
     */
    public void setScore(CounterScore s) {
        this.score = s;
    }

    /**
     * set the lives.
     * @param l the given lives
     */
    public void setLives(CounterLives l) {
        this.numberOfLives = l;
    }

    /**
     * set the winning.
     * @param w the given winning
     */
    public void setWinning(boolean w) {
        this.winning = w;
        if (!this.winning) {
            this.score = new CounterScore(0);
            this.numberOfLives = new CounterLives(3);
        }
    }
 }