package levels;

import biuoop.DrawSurface;
import game.Sprite;
import shapes.Rectangle;
import shapes.Shapes;

import java.util.List;

/**
 * Background class implements Sprite and Shapes.
 */
public class Background implements Sprite, Shapes {
    //members
    private Rectangle back;
    private List<Shapes> shapesList;

    /**
     * @param back1       the rectangle background, on all over the screen.
     * @param shapesList1 - the list of shapes to draw on the screen.
     */
    public Background(Rectangle back1, List<Shapes> shapesList1) {
        this.back = back1;
        this.shapesList = shapesList1;
    }

    /**
     * draw the background.
     *
     * @param d DrawSurface to draw with
     */
    @Override
    public void drawOn(DrawSurface d) {
        //draw the rectangle
        this.back.drawOn(d);
        //draw all the other shapes
        if (shapesList != null) {
            for (int i = 0; i < shapesList.size(); i++) {
                shapesList.get(i).drawOn(d);
            }
        }
    }

    /**
     * time passed method.
     * @param dt to change velocity
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * @param shapeList the list to add to.
     */
    @Override
    public void addToList(List<Shapes> shapeList) {

    }
}
