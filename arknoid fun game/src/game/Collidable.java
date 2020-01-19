package game;

import shapes.Point;
import shapes.Rectangle;

/**
 * interface Collidable.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the object that we collided with it at collisionPoint with
     * @param currentVelocity a given velocity
     * @param hitter          the ball hit with
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}