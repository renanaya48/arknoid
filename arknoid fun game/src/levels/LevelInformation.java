package levels;
import game.Block;
import game.Sprite;
import game.Velocity;

import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * @return the number of balls at the current level
     */
    int numberOfBalls();

    /**
     * @return The list of initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed at the current level
     */
    int paddleSpeed();

    /**
     * @return the paddle width at the current level
     */
    int paddleWidth();

    /**
     * @return the name of the current level
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
