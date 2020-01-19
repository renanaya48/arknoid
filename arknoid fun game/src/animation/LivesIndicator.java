package animation;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;
import hitevent.Counter;

import java.awt.Color;

/**
 * LivesIndicator class implements Sprite.
 */
public class LivesIndicator implements Sprite {

    //member
    private Counter livesIndicator;

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCORE_INDICATOR = 20;
    static final int FONT_SIZE = 20;

    /**
     * constructor.
     *
     * @param livesIndicator1 LivesIndicator
     */
    public LivesIndicator(Counter livesIndicator1) {
        this.livesIndicator = livesIndicator1;
    }

    /**
     * @return the livesIndicator
     */
    public Counter getLivesIndicator() {
        return livesIndicator;
    }

    /**
     * @param livesIndicator1 livesIndicator
     */
    public void setLivesIndicator(Counter livesIndicator1) {
        this.livesIndicator = livesIndicator1;
    }

    /**
     * draw the number of lives on top of the screen.
     *
     * @param d DrawSurface to draw with
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, HI_SCORE_INDICATOR, "Lives: "
                + Integer.toString(livesIndicator.getValue()), FONT_SIZE);

    }

    /**
     * timePassed.
     * @param dt to change velocity
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * add the lives to the game.
     *
     * @param game the game to add to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
