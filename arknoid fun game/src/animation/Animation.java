package animation;

import biuoop.DrawSurface;

/**
 * animation.Animation interface.
 */
public interface Animation {
    /**
     * @param d DrawSurface - to draw with
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * @return ehwn the function shaul stop
     */
    boolean shouldStop();
}
