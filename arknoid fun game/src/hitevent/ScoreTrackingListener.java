package hitevent;

import game.Ball;
import game.Block;

/**
 * ScoreTrackingListener class implements HitListener.
 */
public class ScoreTrackingListener implements HitListener {
    //member
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounte the number of scores
     */
    public ScoreTrackingListener(Counter scoreCounte) {
        this.currentScore = scoreCounte;
    }

    /**
     * @return the scores
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * change the number of scores.
     *
     * @param currentScore1 the new score
     */
    public void setCurrentScore(Counter currentScore1) {
        this.currentScore = currentScore1;
    }

    /**
     * @param beingHit the object is hit.
     * @param hitter   The hitter parameter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //if the block removed-add 10 points
        if (beingHit.getNumOfBump().getValue() <= 0) {
            this.currentScore.increase(10);
        }
        //if the block has hitted-add 5 points
        if (beingHit.getNumOfBump().getValue() == 1) {
            this.currentScore.increase(5);
        }
    }
}
