package levels;
import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;

import java.awt.Color;

/**
 * LevelIndicator class implements Sprite.
 */
public class LevelIndicator implements Sprite {
    //member
    private String levelName;

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCORE_INDICATOR = 20;
    static final int FONT_SIZE = 20;

    /**
     * @param levelName the name of the level
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * draw the name of the level on top of the screen.
     *
     * @param d DrawSurface to draw with
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(WIDTH_SCREEN - 100, HI_SCORE_INDICATOR, this.levelName, FONT_SIZE);
    }

    /**
     * add the level indicator to the game.
     *
     * @param game the game to add to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * timePassed.
     */
    @Override
    public void timePassed(double dt) {

    }
}
