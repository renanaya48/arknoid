package shapes;

import biuoop.DrawSurface;

import java.util.List;

/**
 * Shapes interface.
 */
public interface Shapes {
    /**
     * draw the shape.
     *
     * @param d DrawSurface to draw with
     */
    void drawOn(DrawSurface d);

    /**
     * add a shape to the list.
     *
     * @param shapeList the list to add the shape to.
     */
    void addToList(List<Shapes> shapeList);
}
