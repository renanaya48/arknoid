package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;


/**
 * Paddle class, implements Sprite and Collidable.
 */
public class Paddle implements Sprite, Collidable {
    //members
    private biuoop.KeyboardSensor keyboard;
    private java.awt.Color color;
    private Rectangle rectangle;
    private GameEnvironment game;
    private int veloci;

    //final numbers
    static final int STEP = 5;
    static final int SCREEN_WID = 800;
    static final int SCREEN_HI = 600;

    /**
     * constractors.
     *
     * @param rectangle build the Paddle by rectangle
     */
    public Paddle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * build the Paddle by rectangle, color, game environment ang GUI.
     *
     * @param rec   the rectangle
     * @param color the color of the block
     * @param game1 the game environment of the block
     * @param gui   the GUI of the block
     */
    public Paddle(Rectangle rec, java.awt.Color color, GameEnvironment game1, biuoop.GUI gui) {
        this.rectangle = rec;
        this.color = color;
        this.game = game1;
        this.keyboard = gui.getKeyboardSensor();


    }

    /**
     * build the Paddle by rectangle, color and keyboard.
     *
     * @param rectangle the rectangle
     * @param color     the color of the block
     * @param keyboard  the keyboard
     * @param velocity  the velocity of the paddle
     */
    public Paddle(Rectangle rectangle, Color color, biuoop.KeyboardSensor keyboard, int velocity) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.veloci = velocity;
    }

    /**
     * returns the rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * change the color of the block.
     *
     * @param colo the color to change to.
     */
    public void setColor(Color colo) {
        this.color = colo;
    }


    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        //the paddle will move only in the frame
        if (this.rectangle.getUpperLeft().getX() > 15) {
            Point newLocation = new Point(this.rectangle.getUpperLeft().getX()
                    - this.veloci, this.rectangle.getUpperLeft().getY());
            //change the location of the paddle according to the keyboard to the left
            this.rectangle.setUpperLeft(newLocation);
        }
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        //the paddle will move only in the frame
        if (this.rectangle.getUpperLeft().getX() < SCREEN_WID - 15
                - this.rectangle.getWidth()) {
            Point newLocation = new Point(this.rectangle.getUpperLeft().getX()
                    + this.veloci, this.rectangle.getUpperLeft().getY());
            //change the location of the paddle according to the keyboard to the right
            this.rectangle.setUpperLeft(newLocation);
        }
    }


    // Sprite

    /**
     * how to move the paddle.
     * @param dt to change velocity
     */
    public void timePassed(double dt) {
        //if something has been pressed
        if (this.keyboard != null) {
            //if pressed left-move to the left
            if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                this.moveLeft();
            }
            //if pressed right-move to the right.
            if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                this.moveRight();
            }
        }

    }

    /**
     * draw the paddle.
     *
     * @param d DrawSurface to draw with
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());

    }


    // Collidable

    /**
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @param collisionPoint  the collosion point
     * @param currentVelocity the current velocity that will change
     * @param hitter          the ball hit with
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // creates left side of paddle
        Line left = this.rectangle.getLeft();
        // creates right side of paddle
        Line right = this.rectangle.getRight();
        // creates bottom side of paddle
        Line bottom = this.rectangle.getDown();
        // creates upper side of paddle
        Line up = this.rectangle.getUp();
        // an array of lines - 5 segments from the line "up"
        Line[] segments = new Line[5];
        double l = (up.length()) / 5;
        for (int i = 0; i < segments.length; i++) {
            Point startSeg = new Point(up.start().getX() + (l * i), up.end().getY());
            Point endSeg = new Point(up.start().getX() + (l * (i + 1)), up.end().getY());
            segments[i] = new Line(startSeg, endSeg);
        }
        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy()); // constructs new Velocity
        double absSpeed = Math.sqrt((v.getDx() * v.getDx()) + (v.getDy() * v.getDy()));
        //set the jump from touching the paddle
        if (segments[0].isPointOnLine(collisionPoint)) {
            v.setVelocity(v.fromAngleAndSpeed(300, absSpeed));
        }
        if (segments[1].isPointOnLine(collisionPoint)) {
            v.setVelocity(v.fromAngleAndSpeed(330, absSpeed));
        }

        if (segments[3].isPointOnLine(collisionPoint)) {
            v.setVelocity(v.fromAngleAndSpeed(30, absSpeed));
        }
        if (segments[4].isPointOnLine(collisionPoint)) {
            v.setVelocity(v.fromAngleAndSpeed(60, absSpeed));
        }

        // if collision point "touches" one of the horizontal sides of paddle
        if (segments[2].isPointOnLine(collisionPoint) || bottom.isPointOnLine(collisionPoint)) {
            v.setVelocity(currentVelocity.getDx(), currentVelocity.getDy() * -1); // change the y direction

        }
        // if collision point "touches" one of the vertical sides of paddle
        if (left.isPointOnLine(collisionPoint) || right.isPointOnLine(collisionPoint)) {
            v.setVelocity(currentVelocity.getDx() * -1, currentVelocity.getDy()); // change the x direction
        }

        return v;
    }


    /**
     * Add this paddle to the game.
     *
     * @param g -the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
