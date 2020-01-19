package shapes;

/**
 * Point class, has x and y.
 */
public class Point {
    private double x;
    private double y;

    /**
     * @param x -x value of the point
     * @param y -y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other -the point to check the distance
     * @return the distance between 2 points
     */
    public double distance(Point other) {
        if (other != null) {
            double dis;
            dis = (Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
            dis = Math.sqrt(dis);
            //return Math.abs(dis);
            return dis;
        }
        return 0;
    }

    /**
     * @param other -check if the point is equal
     * @return true the points are equals
     */
    public boolean equals(Point other) {
        boolean eq = false;
        if ((this.x == other.x) && (this.y == other.y)) {
            eq = true;
        }
        return eq;
    }

    /**
     * @return the x of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param xNew - change the x value of the point
     */
    public void setX(double xNew) {
        this.x = xNew;
    }

    /**
     * @param yNew -change the y value of the point
     */
    public void setY(double yNew) {
        this.y = yNew;
    }

    @Override
    /**
     * change the printing way
     */
    public String toString() {
        String stringRank;
        stringRank = ("(" + String.valueOf(this.x) + "," + String.valueOf(this.y) + ")");
        return stringRank;
    }
}



