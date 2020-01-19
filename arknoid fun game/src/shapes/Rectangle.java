package shapes;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 */
public class Rectangle implements Shapes {
    //members
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * create the rectangle according to it upper left point, width and height.
     *
     * @param upperLeft the upper left point
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * return the lines of the rectangle.
     *
     * @return the lines of the rectangle
     */
    public Line[] getRectangle() {
        Line[] rec = getRecLines();
        return rec;
    }

    /**
     * set the upper left point of the rectangle.
     *
     * @param upperLef the new upper left point
     */
    public void setUpperLeft(Point upperLef) {
        this.upperLeft = upperLef;
    }

    /**
     * return a (possibly empty) List of intersection points with the line.
     *
     * @param line the line to check if there intersection points with
     * @return a (possibly empty) List of intersection points with the line.
     */
    public java.util.List intersectionPoints(Line line) {
        //the list that will hold the points
        List<Point> listPoint = new ArrayList<Point>();
        //array of the rectangle lines
        Line[] linesOfRec = this.getRecLines();
        //a loop will run on the lines of the rectangle
        for (int i = 0; i < linesOfRec.length; i++) {
            //if there intersection point-add it to the list
            if (linesOfRec[i].isIntersecting(line)) {
                listPoint.add(line.intersectionWith(linesOfRec[i]));
            }
        }
        //if there is no intersection points - return null
        if (listPoint.isEmpty()) {
            return null;
        }
        //return the intersection points list
        return listPoint;
    }

    /**
     * get the line of the left side of the rectangle.
     *
     * @return the line of the left side of the rectangle
     */
    public Line getLeft() {
        Point p = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line line = new Line(this.upperLeft, p);
        return line;
    }

    /**
     * get the line of the upper side of the rectangle.
     *
     * @return the line of the upper side of the rectangle
     */
    public Line getUp() {
        Point p = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Line line = new Line(this.upperLeft, p);
        return line;
    }

    /**
     * get the line of the down side of the rectangle.
     *
     * @return the line of the down side of the rectangle
     */
    public Line getDown() {
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point bottomRight = new Point(bottomLeft.getX() + this.width, bottomLeft.getY());
        Line line = new Line(bottomLeft, bottomRight);
        return line;
    }

    /**
     * get the line of the right side of the rectangle.
     *
     * @return the line of the right side of the rectangle
     */
    public Line getRight() {
        Point upRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downRight = new Point(upRight.getX(), upRight.getY() + this.height);
        Line line = new Line(upRight, downRight);
        return line;
    }

    /**
     * the function returns array with the lines of the rectangles.
     *
     * @return array with the lines of the rectangles
     */
    public Line[] getRecLines() {
        Line[] linesOfRec = new Line[4];
        Point upRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downLeft = new Point(this.upperLeft.getX(), (this.upperLeft.getY() + this.height));
        Point downRight = new Point(upRight.getX(), downLeft.getY());
        linesOfRec[0] = new Line(this.upperLeft, upRight);
        linesOfRec[1] = new Line(downLeft, downRight);
        linesOfRec[2] = new Line(this.upperLeft, downLeft);
        linesOfRec[3] = new Line(upRight, downRight);
        return linesOfRec;
    }

    /**
     * return the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * return the height of the rectangle.
     *
     * @return return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the color of the rectangle
     */
    public Color getColor() {
        return color;
    }

    /**
     * chane the color of the rectangle.
     *
     * @param color1 the new color of the rectangle
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * draw the rectangle.
     *
     * @param d DrawSurface to draw with
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }

    /**
     * add the rectangle to a shape list.
     *
     * @param shapeList the list to add the rectangle to.
     */
    @Override
    public void addToList(List<Shapes> shapeList) {
        shapeList.add(this);
    }


    /**
     * print the rectangle values.
     *
     * @return the string
     */
    public String toString() {
        String stringRank;
        stringRank = ("rectangle" + String.valueOf(this.upperLeft) + "," + String.valueOf(this.width) + ","
                + String.valueOf(this.height) + ")");
        return stringRank;
    }

}
