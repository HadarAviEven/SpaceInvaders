package game;

import java.io.IOException;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import animation.SubMenu;
import biuoop.KeyboardSensor;

/**
 *
 * @author hadar
 *
 */
public class SetUpGame {
    private HighScoresTable highScores;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private Task<Void> gameTask;
    private Task<Void> showHiScoresTask;
    private Task<Void> quitTask;
    private boolean shouldStartGame;

    /**
     * constructor.
     *
     * @param runner the given runner
     * @param keyboardSensor the given keyboardSensor
     * @param highScores the given highScores
     */
    public SetUpGame(AnimationRunner runner, KeyboardSensor keyboardSensor, HighScoresTable highScores) {
        this.animationRunner = runner;
        this.keyboardSensor = keyboardSensor;
        this.highScores = highScores;
        this.stop = false;
        this.shouldStartGame = true;
    }

    /**
     * @return if the game should start.
     *
     * @throws IOException an exception
     */
    public boolean go() throws IOException {
        showHiScoresTask = new Task<Void>() {
            @Override
            public Void run() {
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                      new HighScoresAnimation(highScores)));
                return null;
            }
        };

        quitTask = new Task<Void>() {
            @Override
            public Void run() {
                stop = true;
                shouldStartGame = false;
                System.exit(1);
                return null;
            }
        };

        gameTask = new Task<Void>() {
            @Override
            public Void run() {
                stop = true;
                return null;
            }
        };

        while (!this.stop) {
            MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", this.keyboardSensor);
            menu.addSelection("s", "(s)  Start Game", gameTask);
            menu.addSelection("h", "(h)  High Scores", showHiScoresTask);
            menu.addSelection("q", "(q)  Quit", quitTask);
            SubMenu<Task<Void>> subMenu = new SubMenu<Task<Void>>("Arkanoid", this.keyboardSensor);
            subMenu.addSelection("h", "(h)  Hard", gameTask);
            subMenu.addSelection("e", "(e)  Easy", gameTask);
            animationRunner.run(menu);
            if (menu.stop()) {
                animationRunner.run(subMenu);
                Task<Void> task = subMenu.getStatus();
                task.run();
            } else {
                Task<Void> task = menu.getStatus();
                task.run();
            }
        }
        return shouldStartGame;
    }
}
