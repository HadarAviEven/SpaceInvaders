package animation;

import biuoop.DrawSurface;
import game.HighScoresTable;

/**
 *
 * @author hadar
 *
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * constructor.
     *
     * @param scores the score table
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    /**
     *
     * @param d the given drawSurface
     * @param dt number of seconds.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(120, 120, "High Scores", 100);
        int y = 195;

        for (int i = 0; i < this.scores.size(); i++) {
            if (this.scores.getHighScores().get(i) != null) {
                d.drawText(280, y, (i + 1) + ". " + this.scores.getHighScores().get(i).getName(), 25);
                d.drawText(510, y, String.valueOf(this.scores.getHighScores().get(i).getScore()), 25);
                y += 40;
            }
        }
    }

    /**
     * @return if it should stop
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
