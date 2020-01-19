package levels;

import biuoop.DrawSurface;
import shapes.Point;
import shapes.Shapes;

import java.awt.Color;
import java.util.List;

/**
 * levels.Circle class implements shapes.Shapes.
 */
public class Circle implements Shapes {
    //members
    private Point center;
    private int radius;
    private Color color;

    /**
     * constructor.
     *
     * @param center the center point of the circle
     * @param radius the radius of the circle
     * @param color  the color of the circle
     */
    public Circle(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * @return the center of the circle
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return the radius of the circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * change the center of the circle.
     *
     * @param center1 the new center of the circle
     */
    public void setCenter(Point center1) {
        this.center = center1;
    }

    /**
     * change the radius of the circle.
     *
     * @param radius1 the new radius of the circle
     */
    public void setRadius(int radius1) {
        this.radius = radius1;
    }

    /**
     * change the color of the circle.
     *
     * @param color1 the new color of the circle
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * draw the circle.
     *
     * @param d drawSurface to draw with
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(),
                this.radius);
    }

    /**
     * add the circle to a list of shapes.
     *
     * @param shapeList the list to add the circle to
     */
    @Override
    public void addToList(List<Shapes> shapeList) {
        shapeList.add(this);
    }
}
