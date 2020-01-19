package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * HighScoresAnimation class implements Animation.
 */
public class HighScoresAnimation implements Animation {
    //members
    private KeyboardSensor keyboard;
    private boolean stop;
    private HighScoresTable score;
    private String endKey;

    /**
     * @param scores  HighScoresTable
     * @param endKey1 String
     * @param keyboar KeyboardSensor
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey1, KeyboardSensor keyboar) {
        this.keyboard = keyboar;
        this.score = scores;
        this.endKey = endKey1;
        this.stop = false;
    }

    /**
     * @param scores score of the game
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.score = scores;
        this.stop = false;
    }


    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.GREEN);
        d.drawText(d.getWidth() / 2 - 50, 50, "SCORE TABLE", 32);
        d.setColor(Color.RED);
        d.drawText((d.getWidth() / 2) - 80, 100, "NAME           SCORE", 26);
        d.setColor(Color.BLUE);
        if (score != null) {
            for (int i = 0; i < score.size(); i++) {
                d.drawText((d.getWidth() / 2) - 50, ((i + 3) * 50), score.getHighScores().get(i).getName(), 20);
                d.drawText(d.getWidth() / 2 + 100, ((i + 3) * 50),
                        Integer.toString(score.getHighScores().get(i).getScore()), 20);
            }
        }

        //if (this.keyboard.isPressed(this.endKey)) {
        //this.stop = true;
        //}
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
