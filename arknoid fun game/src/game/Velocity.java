package game;

import shapes.Point;

/**
 *velocity class.
 */
public class Velocity {
    //members
    private double dx;
    private double dy;

    /**
     * @param dx - of the velocity
     * @param dy - of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * @return dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }


    /**
     * @return -dy of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param otherDx -change the dx of the velocity
     */
    public void setDx(double otherDx) {
        this.dx = otherDx;
    }


    /**
     * @param otherDy -the dy of the velocity
     */
    public void setDy(double otherDy) {
        this.dy = otherDy;
    }

    /**
     * set the velocity.
     *
     * @param dX the new dx of the velocity
     * @param dY the new dy of the velocity
     */
    public void setVelocity(double dX, double dY) {
        this.dx = dX;
        this.dy = dY;
    }

    /**
     * set the velocity.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * @param p -point-to change accordint to it
     * @return the point according to the velocity
     */
    public Point applyToPoint(Point p) {
        Point p1 = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return p1;
    }

    /**
     * @param angle -the angel to change
     * @param speed -the speed to change
     * @return the velocity according to angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        if (((dy < 0.0001) && (dy > 0)) || (dy > -0.0001) && (dy < 0)) {
            dy = 0;
        }
        if (((dx < 0.0001) && (dx > 0)) || (dx > -0.0001) && (dx < 0)) {
            dx = 0;
        }
        return new Velocity(dx, dy);
    }

    /**
     * print the velocity.
     *
     * @return the string
     */
    public String toString() {
        String stringRank;
        stringRank = ("dx: (" + String.valueOf(this.getDx()) + ")"
                + " dy: (" + String.valueOf(this.getDy()) + ")");
        return stringRank;
    }
}
