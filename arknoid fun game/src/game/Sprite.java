package game;

import biuoop.DrawSurface;

/**
 * Sprite interface.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface to draw with
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed(double dt);
}
