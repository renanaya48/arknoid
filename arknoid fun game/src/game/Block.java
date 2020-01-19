package game;

import biuoop.DrawSurface;
import hitevent.Counter;
import hitevent.HitListener;
import hitevent.HitNotifier;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class, implements Collidable and Sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //members
    private Rectangle rectangle;
    private java.awt.Color color;
    private Counter numOfBump;
    //private int numOfBump;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * create the block according to the rectangle.
     *
     * @param rect the rectangle
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * create the block according to the rectangle and color.
     *
     * @param rectangle1 the rectangle
     * @param color      the color
     */
    public Block(Rectangle rectangle1, Color color) {
        this.rectangle = rectangle1;
        this.color = color;
    }

    /**
     * create the block according to the rectangle, color and num of bumps.
     *
     * @param rect      the rectangle
     * @param color     the color
     * @param numOfBump the number of the times the ball hit the block
     */
    public Block(Rectangle rect, Color color, Counter numOfBump) {
        this.rectangle = rect;
        this.color = color;
        this.numOfBump = numOfBump;
    }

    /**
     * change the color of the block.
     *
     * @param colo the color to change to
     */
    public void setColor(Color colo) {
        this.color = colo;
    }

    /**
     * get the color of the block.
     *
     * @return the color of the block
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * return the rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @return the number of times that the block has been hit
     */
    public Counter getNumOfBump() {
        return numOfBump;
    }

    /**
     * chane the velocity of the ball according to the angle of
     * hitting the block.
     *
     * @param collisionPoint  the collision Point
     * @param currentVelocity the velocity of the ball before the change
     * @param hitter          the ball hit with
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the ball moves to the right
        if (currentVelocity.getDx() > 0) {
            //if the ball hits the block by it left side
            if (this.rectangle.getLeft().isPointOnLine(collisionPoint)) {
                //change the direction of the ball
                currentVelocity.setDx((-1) * (currentVelocity.getDx()));
                //change the number of hitting the block
                this.numOfBump.decrease(1);
            }
            //if the ball isn't moves to the right
        } else {
            //if the ball moves to the left
            if (currentVelocity.getDx() < 0) {
                //if the ball hits the block by it right side
                if (this.rectangle.getRight().isPointOnLine(collisionPoint)) {
                    //change the direction of the ball
                    currentVelocity.setDx((-1) * (currentVelocity.getDx()));
                    //change the number of hitting the block
                    this.numOfBump.decrease(1);
                }
            }
        }
        //if the ball moves down
        if (currentVelocity.getDy() > 0) {
            //if the ball hits the block by it upper line
            if (this.rectangle.getUp().isPointOnLine(collisionPoint)) {
                //change the direction of the ball
                currentVelocity.setDy((-1) * (currentVelocity.getDy()));
                //change the number of hitting the block
                this.numOfBump.decrease(1);
            }
            //if the ball does'nt move down
        } else {
            //if the ball moves up
            if (currentVelocity.getDy() < 0) {
                //if the ball hits the block by it down line
                if (this.rectangle.getDown().isPointOnLine(collisionPoint)) {
                    //change the direction of the ball
                    currentVelocity.setDy((-1) * (currentVelocity.getDy()));
                    //change the number of hitting the block
                    this.numOfBump.decrease(1);
                }
            }
        }
        this.notifyHit(hitter);
        //return the new velocity
        return currentVelocity;
    }

    /**
     * draw the block.
     *
     * @param d the DrawSurface to draw with
     */
    public void drawOn(DrawSurface d) {
        //set the girth to be black and draw it
        d.setColor(Color.BLACK);
        d.drawRectangle((int) (this.rectangle.getUpperLeft().getX() - 1),
                (int) this.rectangle.getUpperLeft().getY() + 1, (int) this.rectangle.getWidth() + 1,
                (int) this.rectangle.getHeight() + 1);
        //chane the color to the color of the block and draw the block
        d.setColor(this.getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        /*
        //the middle point values of the block
        int midX = (int) this.rectangle.getUpperLeft().getX() + ((int) this.rectangle.getWidth()) / 2 - 3;
        int midY = (int) (this.rectangle.getUpperLeft().getY()) + ((int) this.rectangle.getHeight() / 2) + 3;
        //if the number of hitting the block is 0 or less
        if (this.numOfBump.getValue() <= 0) {
            //draw "x"
            d.setColor(Color.WHITE);
            d.drawText(midX, midY, "X", 16);
        } else {
            //draw the number of hitting the block
            d.setColor(Color.WHITE);
            d.drawText(midX, midY, Integer.toString(numOfBump.getValue()), 16);
        }*/
    }

    /**
     * timePassed.
     * @param dt to change velocity
     */
    public void timePassed(double dt) {
    }

    /**
     * add the block to game.
     *
     * @param g the game to add the block to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes the Block from the game.
     *
     * @param game the game to be moved from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * print the block.
     *
     * @return the string
     */
    public String toString() {
        String stringRank;
        stringRank = ("Block " + String.valueOf(this.rectangle) + ")");
        return stringRank;
    }

    /**
     * @param hl Add hl as a listener to hit events.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl the object to be removed.
     */
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners.contains(hl)) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * @param hitter the ball that hit in the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
