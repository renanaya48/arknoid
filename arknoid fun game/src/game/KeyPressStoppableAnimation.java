package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class implements Animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    //members
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed = true;


    /**
     * @param sensor1    KeyboardSensor
     * @param key1       the key to stop
     * @param animation1 animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor1, String key1, Animation animation1) {
        this.sensor = sensor1;
        this.key = key1;
        this.animation = animation1;
        this.stop = false;

    }

    /**
     * @param d  DrawSurface - to draw with
     * @param dt parameter
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);

        if (this.sensor.isPressed(this.key)) {
            if (isAlreadyPressed) {
                isAlreadyPressed = true;
            } else {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;

        }


    }

    /**
     * @return when the game should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
