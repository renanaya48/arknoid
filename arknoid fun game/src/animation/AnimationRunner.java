package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 */
public class AnimationRunner {
    //members
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;

    /**
     * constructors.
     *
     * @param gui1             the GUI
     * @param framesPerSecond1 frames Per Second
     * @param sleeper1         sleeper
     */
    public AnimationRunner(GUI gui1, int framesPerSecond1, Sleeper sleeper1) {
        this.gui = gui1;
        this.framesPerSecond = framesPerSecond1;
        this.sleeper = sleeper1;
    }

    /**
     * run the animation.
     *
     * @param animation the animation to run according to.
     */
    public void run(Animation animation) {
        double dt = ((1.0) / this.framesPerSecond);
        int millisecondsPerFrame = this.framesPerSecond - 40;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            //do the frame of the animation
            animation.doOneFrame(d, dt);

            //show it on the screen
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
