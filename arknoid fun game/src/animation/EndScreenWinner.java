package animation;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * EndScreenWinner class implements Animation.
 */
public class EndScreenWinner implements Animation {
    //members
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * constructor.
     *
     * @param keyboard the keyboard
     * @param score1   the score of the game
     */
    public EndScreenWinner(KeyboardSensor keyboard, int score1) {
        this.keyboard = keyboard;
        this.stop = false;
        this.score = score1;
    }

    /**
     * print a massage when the user win the game and exit when space key pressed.
     *
     * @param d DrawSurface - to draw with
     * @param dt to change velocity
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "HAREY!! YOU WIN!! :) YOUR SCORE IS: " + Integer.toString(score), 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }

    }

    /**
     * @return when the function needs to stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
