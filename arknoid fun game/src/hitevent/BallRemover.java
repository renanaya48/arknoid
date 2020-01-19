package hitevent;

import game.Ball;
import game.Block;
import game.GameLevel;

/**
 * BallRemover class implements HitListener.
 */
public class BallRemover implements HitListener {
    //members
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game          the current game
     * @param remainingBall the number of ball in the game
     */
    public BallRemover(GameLevel game, Counter remainingBall) {
        this.game = game;
        this.remainingBalls = remainingBall;
    }

    /**
     * @return the current game.
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * set the game.
     *
     * @param game1 the game to change to.
     */
    public void setGame(GameLevel game1) {
        this.game = game1;
    }

    /**
     * @return the number of balls in the game
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * change number of balls in the game.
     *
     * @param remainingBall the current number of balls in the game
     */
    public void setRemainingBalls(Counter remainingBall) {
        this.remainingBalls = remainingBall;
    }

    /**
     * @param beingHit the object is hit.
     * @param hitter   The hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (hitter.getTrajectory().intersectionWith(beingHit.getCollisionRectangle().getUp()) != null) {
            hitter.removeHitListener(this);
            //remove the ball from the game
            hitter.removeFromGame(game);
            //less 1 ball from the game
            this.remainingBalls.decrease(1);
        }
    }
}
