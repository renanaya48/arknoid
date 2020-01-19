package game;

import shapes.Line;
import shapes.Point;

/**
 * CollisionInfo class.
 */
public class CollisionInfo {
    //members
    private Point collPoint;
    private Line boomLine;
    private Collidable colliObj;


    /**
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collPoint;
    }


    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.colliObj;
    }

    /**
     * create a CollisionInfo by collisionPoint and collision Object.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the object that was collision with
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collPoint = collisionPoint;
        this.colliObj = collisionObject;
    }

    /**
     * create a CollisionInfo by collisionLine and collision Object.
     *
     * @param collisionLine   the collision line
     * @param collisionObject the object that was collision with
     */
    public CollisionInfo(Line collisionLine, Collidable collisionObject) {
        this.boomLine = collisionLine;
        this.colliObj = collisionObject;
    }

    /**
     * print the CollisionInfo.
     *
     * @return the string
     */
    public String toString() {
        String stringRank;
        stringRank = ("coolasion info: point" + String.valueOf(this.collPoint) + ", object"
                + String.valueOf(this.colliObj) + ")");
        return stringRank;
    }
}
