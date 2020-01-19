package animation;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.SpriteCollection;

import java.awt.Color;

/**
 * CountdownAnimation class implements Animation.
 */
public class CountdownAnimation implements Animation {
    //members
    private double numOfSecond;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long timePast = 0;
    private int counter = 0;

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;

    /**
     * constructors.
     *
     * @param numOfSeconds1 total num of second
     * @param countFrom1    the number to count from
     * @param gameScreen1   the screen of the game
     */
    public CountdownAnimation(double numOfSeconds1, int countFrom1, SpriteCollection gameScreen1) {
        this.numOfSecond = numOfSeconds1;
        this.countFrom = countFrom1;
        this.gameScreen = gameScreen1;
    }

    /**
     * @param d DrawSurface - to draw with
     * @param dt to change velocity
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        Sleeper sleeper = new Sleeper();
        long numOFsec = (long) this.numOfSecond;
        this.gameScreen.drawAllOn(d);
        //write the numbers
        d.setColor(Color.WHITE);
        d.drawText(WIDTH_SCREEN / 2, HI_SCREEN / 2, Integer.toString(countFrom - 1), 48);
        d.setColor(Color.BLACK);
        d.drawText(WIDTH_SCREEN / 2, HI_SCREEN / 2, Integer.toString(countFrom - 1), 34);
        //wait after each number
        sleeper.sleepFor(((numOFsec * 1000) / (countFrom - 1 + counter)));
        //add 1 to the counter
        counter++;
        //count down
        countFrom--;
    }

    /**
     * @return when the function needs to stop.
     */
    @Override
    public boolean shouldStop() {
        //when the count got to 0, stop
        if (this.countFrom == 0) {
            return true;
        }
        return false;
    }
}
