package game;

/**
 *
 * @author hadar
 *
 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * constructor.
     *
     * @param name the name of the player
     * @param score the score of the player
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }
 }
