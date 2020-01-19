package hitevent;


import game.Ball;
import game.Block;
import game.GameLevel;

/**
 * BlockRemover class.
 */
public class BlockRemover implements HitListener {
    //members
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          the game to remove from
     * @param removedBlocks the number of time the block has been hited
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;

    }

    /**
     * change the number of time the ball has been hitted.
     *
     * @param remainingBlock the number to change to
     */
    public void setRemainingBlocks(Counter remainingBlock) {
        this.remainingBlocks = remainingBlock;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the game.
     *
     * @param beingHit the object is hit.
     * @param hitter   The hitter parameter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfBump().getValue() <= 0) {
            beingHit.removeHitListener(this);
            //remove the block from the game
            beingHit.removeFromGame(game);
            //one less block in the game
            this.remainingBlocks.decrease(1);
        }
    }
}
