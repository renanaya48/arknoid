package game;

import java.io.Serializable;

/**
 * game.ScoreInfo class.
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    //members
    private String name;
    private int score;

    /**
     * @param name1  -the name of the player
     * @param score1 the player's score.
     */
    public ScoreInfo(String name1, int score1) {
        this.name = name1;
        this.score = score1;

    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the player's score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @param another the score to compare to
     * @return -1 if small, 1 else
     */
    @Override
    public int compareTo(ScoreInfo another) {
        if (this.getScore() < another.getScore()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @return the new way to print
     */
    public String toString() {
        return this.getName() + "" + this.getScore();
    }
}
