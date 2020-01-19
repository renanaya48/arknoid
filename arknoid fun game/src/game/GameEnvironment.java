package game;

import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {

    private List<Collidable> collidableList = new ArrayList<Collidable>();

    /**
     * add the given collidable to the environment.
     *
     * @param c the Collidable to add to the list
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * returns the Collidable list.
     *
     * @return the Collidable list
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * @param c the Collidable to move from the Game Environment
     */
    public void removeFromGameEnvironment(Collidable c) {
        if (this.collidableList != null) {
            if (collidableList.contains(c)) {
                collidableList.remove(c);
            }
        }
    }

    /**
     * check if there is an collision between the line and the Collidable.
     * return the point and the Collidable.
     *
     * @param trajectory the line to check if there is a collision
     * @return the Collidable and the point that collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //if the Collidable list isn't empty
        if (this.collidableList.size() > 0) {
            //set the Collidable and the point. at the begging-the first object in the list
            Collidable close = this.collidableList.get(0);
            Point closest = trajectory.closestIntersectionToStartOfLine((Rectangle)
                    this.collidableList.get(0).getCollisionRectangle());
            //run on all over the list and get the Collidable and the point
            for (int i = 1; i < this.collidableList.size(); i++) {
                Point p = trajectory.closestIntersectionToStartOfLine((Rectangle)
                        this.collidableList.get(i).getCollisionRectangle());
                Collidable rec = this.collidableList.get(i);
                //if there is a point intersection with the object
                if (p != null) {
                    if (closest != null) {
                        //get the distance between the 2 points and equals them.
                        if (p.distance(trajectory.end()) < closest.distance(trajectory.end())) {
                            //put the closest point into closest and the save the rectangle in close
                            closest = p;
                            close = rec;
                        }
                        //if closest is null
                    } else {
                        //put the value of the point into closest and save the maching rectangle
                        closest = p;
                        close = rec;
                    }
                }
            }
            //if there is value in closest
            if (closest != null) {
                //save the point and the rectangle in CollisionInfo and return
                CollisionInfo colli = new CollisionInfo(closest, close);
                return colli;
            }
        }
        //is there isn't value-return null
        return null;
    }

    /**
     * @return the x value of the left point
     */
    public double getLeftX() {
        List list = this.getCollidableList();
        if (list.size() != 0) {
            Collidable col = (Collidable) list.get(0);
            double min = col.getCollisionRectangle().getUpperLeft().getX()
                    + col.getCollisionRectangle().getWidth();
            for (int i = 0; i < list.size(); i++) {
                Collidable col1 = (Collidable) list.get(i);
                if (Math.min(min, col1.getCollisionRectangle().getUpperLeft().getX()
                        + col1.getCollisionRectangle().getWidth()) != min) {
                    min = col1.getCollisionRectangle().getUpperLeft().getX()
                            + col1.getCollisionRectangle().getWidth();
                }
            }
            return min;
        }
        return 0;

    }

    /**
     * @return the x value of the right point
     */
    public double getRightX() {
        List list = this.getCollidableList();
        if (list.size() != 0) {
            Collidable col = (Collidable) list.get(0);
            double max = col.getCollisionRectangle().getUpperLeft().getX();
            for (int i = 0; i < list.size(); i++) {
                Collidable col1 = (Collidable) list.get(i);
                if (Math.max(max, col1.getCollisionRectangle().getUpperLeft().getX()) != max) {
                    max = col1.getCollisionRectangle().getUpperLeft().getX();
                }
            }
            return max;
        }
        return 0;
    }

    /**
     * the way to print the game environment.
     *
     * @return the string to print
     */
    public String toString() {
        String stringRank;
        stringRank = ("num of members" + String.valueOf(this.collidableList.size()));
        return stringRank;
    }
}
