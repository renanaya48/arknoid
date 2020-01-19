package levels;
import biuoop.DrawSurface;
import shapes.Point;
import shapes.Shapes;

import java.awt.Color;
import java.util.List;

/**
 * FullCircle class implements Shapes.
 */
public class FullCircle implements Shapes {
    //members
    private Point center;
    private int radius;
    private Color color;

    /**
     * constructors.
     *
     * @param center the center of the circle
     * @param radius the radius of the circle
     * @param color  the color of the circle
     */
    public FullCircle(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * change the color of the circle.
     *
     * @param color1 the color to change to
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * draw the full circle.
     *
     * @param d the Drawsurface to draw with
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                this.radius);
    }

    /**
     * add the circle to the shape list.
     *
     * @param shapeList the list to add the circle to.
     */
    @Override
    public void addToList(List<Shapes> shapeList) {
        shapeList.add(this);

    }
}
