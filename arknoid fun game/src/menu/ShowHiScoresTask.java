package menu;

import animation.Animation;
import animation.AnimationRunner;
import animation.Task;
import biuoop.KeyboardSensor;

/**
 * ShowHiScoresTask class implements Task.
 */
public class ShowHiScoresTask implements Task<Void> {
    //members
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private Animation animation;
    private double dt;

    /**
     * @param kb                   KeyboardSensor
     * @param runner1              AnimationRunner
     * @param highScoresAnimation1 Animation
     */
    public ShowHiScoresTask(AnimationRunner runner1, Animation highScoresAnimation1, KeyboardSensor kb) {
        this.runner = runner1;
        this.animation = highScoresAnimation1;
        this.keyboard = kb;
    }

    /**
     * @param runner1 AnimationRunner
     * @param kb      KeyboardSensor
     */
    public ShowHiScoresTask(AnimationRunner runner1, KeyboardSensor kb) {
        this.runner = runner1;
        //this.animation = highScoresAnimation1;
        this.keyboard = kb;
    }

    /**
     * @return null
     */
    public Void run() {
        this.runner.run(this.animation);
        return null;
    }
}
