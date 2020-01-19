package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen class implements Animation.
 */
public class PauseScreen implements Animation {
    //members
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * print a message of pause on the screen.
     *
     * @param d DrawSurface - to draw with
     * @param dt to change velocity
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * @return when the function needs to stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
