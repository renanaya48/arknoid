package animation;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;
import hitevent.Counter;

import java.awt.Color;

/**
 * ScoreIndicator class implements Sprite.
 */
public class ScoreIndicator implements Sprite {

    //member
    private Counter scoreIndicator;

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCORE_INDICATOR = 20;
    static final int FONT_SIZE = 20;

    /**
     * constructor.
     *
     * @param scoreIndi the number of scores
     */
    public ScoreIndicator(Counter scoreIndi) {
        this.scoreIndicator = scoreIndi;
    }

    /**
     * @return the number of the scores.
     */
    public Counter getScoreIndi() {
        return scoreIndicator;
    }

    /**
     * draw the score on the top of the screen.
     *
     * @param d DrawSurface to draw with
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, WIDTH_SCREEN, HI_SCORE_INDICATOR);
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, WIDTH_SCREEN, HI_SCORE_INDICATOR);
        d.setColor(Color.BLACK);
        d.drawText(WIDTH_SCREEN / 2, HI_SCORE_INDICATOR, "Score: "
                + Integer.toString(scoreIndicator.getValue()), FONT_SIZE);

    }

    /**
     * add the score indicator to the game.
     *
     * @param game the game to add to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * timePassed.
     * @param dt to change velocity
     */
    @Override
    public void timePassed(double dt) {

    }
}
