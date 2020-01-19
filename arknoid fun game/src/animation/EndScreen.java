package animation;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * EndScreen class implements Animation.
 */
public class EndScreen implements Animation {
    //members
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * constructor.
     *
     * @param keyboard keyboard
     * @param score1   the score of the game
     */
    public EndScreen(KeyboardSensor keyboard, int score1) {
        this.keyboard = keyboard;
        this.stop = false;
        this.score = score1;
    }

    /**
     * print a massage when the game is over and exit when space key pressed.
     *
     * @param d DrawSurface - to draw with
     * @param dt to change velocity
     *
     */
    @Override
    public void doOneFrame(DrawSurface d ,double dt) {
        d.drawText(10, d.getHeight() / 2, "GAME OVER! :( YOUR SCORE IS: " + Integer.toString(score), 32);
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
