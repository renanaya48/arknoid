package game;
import biuoop.DrawSurface;
import hitevent.Counter;
import hitevent.HitListener;
import hitevent.HitNotifier;
import shapes.Point;
import shapes.Line;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Ball class.
 */
public class Ball implements Sprite, HitNotifier {
    //members
    private Point center;
    private double radius;
    private java.awt.Color color;
    private Velocity velocity;
    //private Point leftPoint;
    //private Point rightPoint;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    //final numbers
    static final int WIDTH_BORDER = 800;
    static final int HI_BORDER = 600;


    /**
     * create the ball according to the center, radius, color, velocity,
     * left point, right point and the game environment.
     *
     * @param center          -center  point
     * @param radius          -the     radius of the ball
     * @param color           -the      color of the ball
     * @param velocity        -the   velocity of the ball
     * @param leftPoint       -the  left point of creating the ball
     * @param rightPoint      -the right point of creating the ball
     * @param gameEnvironment - the ball game environment
     */
    public Ball(Point center, double radius, java.awt.Color color,
                Velocity velocity, Point leftPoint, Point rightPoint, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        //this.leftPoint = leftPoint;
        //this.rightPoint = rightPoint;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * create the ball according to the center, radius, color and game environment.
     *
     * @param center          -the    center of the ball
     * @param radius          -the    radius of the ball
     * @param color           -the     color of the ball
     * @param gameEnvironment - the ball game environment
     * @param velocity        - the velocity of the ball
     */
    public Ball(Point center, double radius, java.awt.Color color, Velocity velocity,
                GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        //this.leftPoint = leftPoint;
        //this.rightPoint = rightPoint;
        this.velocity = velocity;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @param x      -the      x value of the point center of the ball
     * @param y      -the      y value of the point center of the ball
     * @param radius -the center value of the ball
     * @param color  -the  color value of the ball
     * @param v      -the      velocity value of the ball
     * @param left   -the left point of the creating ball
     * @param right  -the right point of the creating ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color,
                Velocity v, Point left, Point right) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.velocity = v;
        //this.leftPoint = new Point(left.getX(), left.getY());
        //this.rightPoint = new Point(right.getX(), right.getY());

    }

    /**
     * create the ball according to the center, radius, color and velocity.
     *
     * @param center -the point center of the ball
     * @param r      -the      radius value of the ball
     * @param color  -the  color value of the ball
     * @param v      - the velocity of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity v) {
        this.radius = r;
        this.color = color;
        this.center = center;
        this.velocity = v;

    }


    /**
     * @return the x value of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @param x -the new x value of the center point
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * @param y -the new y value of the center point
     */
    public void setY(double y) {
        this.center.setY(y);
    }


    /**
     * @return the y value of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius of the Ball
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * @param newRadius -the new radius of the ball
     */
    public void setSize(int newRadius) {
        this.radius = newRadius;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param surface -draw a ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());

    }

    /**
     * @return the height of the screen that the ball can move in
     */
    //public int getHeight() {
    //return (int) (this.rightPoint.getY() - this.leftPoint.getY());
    //}

    /**
     * @return the width of the screen that the ball can move in
     */
    //public int getWidth() {
    //return (int) (this.rightPoint.getX() - this.leftPoint.getY());
    //}

    /**
     * @return the ball game environment
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * @param gameEnvir - change the ball game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvir) {
        this.gameEnvironment = gameEnvir;
    }

    /**
     * @param p -the new left point
     */
    /*public void setLeftPoint(Point p) {
        this.leftPoint = p;
    }*/

    /**
     * @param x the new x value of the left point
     * @param y the new y value of the left point
     */
    /*public void setLeftPoint(double x, double y) {
        this.leftPoint.setX(x);
        this.leftPoint.setY(y);
    }*/

    /**
     * @param p -the new right point
     */
    /*public void setRightPoint(Point p) {
        this.rightPoint = p;
    }*/

    /**
     * @param x the new x value of the right point
     * @param y the new y value of the right point
     */
    /*public void setRightPoint(double x, double y) {
        this.rightPoint.setX(x);
        this.rightPoint.setY(y);
    }*/

    /**
     * @param v -the new  velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /*
     *change the velocity of the ball by exact values
     */

    /**
     * @param dx the new dx value of the velocity of the ball
     * @param dy the new dy value of the velocity of the ball
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * move the ball one step according to it values.
     *
     * @param beinHit block
     */
    public void moveOneStep(Block beinHit) {
        //move the ball
        this.getVelocity().applyToPoint(this.center);
        //set new x and y to be the new line
        double newX = (int) (this.getTrajectory().end().getX());
        double newY = (int) (this.getTrajectory().end().getY());
        //if the ball moves to the right
        if (this.velocity.getDx() > 0) {
            //change the new x to be the next step
            newX = (int) (this.getTrajectory().start().getX() + (this.velocity.getDx()));
        } else {
            //if the ball moves to the left
            if (this.velocity.getDx() < 0) {
                //change the new x to be the next step
                newX = (int) (this.getTrajectory().start().getX() + (this.velocity.getDx()));
            }
        }
        //if the ball moves down
        if (this.velocity.getDy() > 0) {
            //change the new y to be the next step
            newY = (int) (this.getTrajectory().start().getY() + (this.velocity.getDy()));
        } else {
            //if the ball moves up
            if (this.velocity.getDy() < 0) {
                //change the new y to be the next step
                newY = (int) (this.getTrajectory().start().getY() + (this.velocity.getDy()));
            }
        }
        //set new point with the new value of x and y
        Point newEnd = new Point(newX, newY);
        //set a new line accoring to the new point
        Line lineTraje = new Line(new Point((int) this.center.getX(), (int) this.center.getY()), newEnd);
        //if the game environment isnt empty
        if (this.gameEnvironment != null) {
            //if there id a collision between the line and the block
            if ((this.gameEnvironment.getClosestCollision(lineTraje) != null)) {
                //if there is a point collision
                if (this.gameEnvironment.getClosestCollision(lineTraje).collisionPoint() != null) {
                    //save the point value
                    Point collPoint = (this.gameEnvironment.getClosestCollision(lineTraje).collisionPoint());
                    //if there is an object
                    if (this.gameEnvironment.getClosestCollision(lineTraje).collisionObject() != null) {
                        //save the collidable
                        Collidable colli = this.gameEnvironment.getClosestCollision(lineTraje).collisionObject();
                        if (collPoint != null) {
                            //if there is a hit-change the direction of the ball
                            Velocity v = colli.hit(this, collPoint, this.velocity);
                            this.velocity.setDx(v.getDx());
                            this.velocity.setDy(v.getDy());
                        }
                    }
                }
            }
        }
        this.backToGame();
        //check if the ball felt down
        this.notifyHit(beinHit);
        //change the center of the ball
        this.center = this.getVelocity().applyToPoint(this.center);


    }

    /**
     * call move one step.
     * @param dt to change velocity
     */
    public void timePassed(double dt) {
        Block deathRegion = new Block(new Rectangle(new Point(0, HI_BORDER - 20),
                WIDTH_BORDER + 2, 8), Color.RED, new Counter(1));
        this.moveOneStep(deathRegion);
    }

    /**
     * add the ball to the game.
     *
     * @param g the game to add the ball to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * return the ball back to the game if there was a problem.
     */
    public void backToGame() {
        //the ball is going to stack in right border
        if (this.center.getX() >= WIDTH_BORDER) {
            this.center.setX(WIDTH_BORDER - 2);
            if (this.velocity.getDx() > 0) {
                this.velocity.setDx(this.velocity.getDx() * (-1));
            }
        }
        //the ball is going to stack in left border
        if (this.center.getX() <= 0) {
            this.center.setX(2);
            if (this.velocity.getDx() < 0) {
                this.velocity.setDx(this.velocity.getDx() * (-1));
            }
        }
        //the ball is going to stack in the upper border
        if (this.center.getY() <= 25) {
            this.center.setY(27);
            if (this.velocity.getDy() < 0) {
                this.velocity.setDy(this.velocity.getDy() * (-1));
            }
        }
        //the ball is going to stack in the down border
        if (this.center.getY() >= HI_BORDER - 25) {
            this.center.setY(HI_BORDER + 28);
        }
    }


    /**
     * get the trajectory line.
     *
     * @return the trajectory line
     */
    public Line getTrajectory() {
        Point v = new Point(this.velocity.getDx(), this.velocity.getDy());
        Point c = this.center;
        Line trajectory = new Line(c, v);
        return trajectory;
    }

    /**
     * @param hl Add hl as a listener to hit events.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl the object to be removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners.contains(hl)) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * @param beinHit the block that the ball get hit with
     */
    private void notifyHit(Block beinHit) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(beinHit, this);
        }
    }

    /**
     * remove the ball from the game.
     *
     * @param game the game to be delete from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

