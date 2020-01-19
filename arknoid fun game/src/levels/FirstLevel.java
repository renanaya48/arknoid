package levels;
import game.Block;
import game.Sprite;
import game.Velocity;
import hitevent.Counter;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FirstLevel class implements LevelInformation.
 */
public class FirstLevel implements LevelInformation {

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;

    /**
     * @return 1 ball
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * @return The list of initial velocity of the ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return Arrays.asList(Velocity.fromAngleAndSpeed(0.0, 10));
    }

    /**
     * @return the paddle Speed-5
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * @return the paddle width - 85
     */
    @Override
    public int paddleWidth() {
        return 85;
    }

    /**
     * @return the level name= "Direct Hit".
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        //a list of the shape on this level
        List<Shapes> shapesList = new ArrayList<Shapes>();
        //the background rectangle - black on all over the screen
        Rectangle backg = new Rectangle(new Point(0, 20),
                WIDTH_SCREEN, HI_SCREEN);
        backg.setColor(Color.BLACK);
        //the circle to draw
        Circle circle1 = new Circle(new Point(400, 162), 60, Color.BLUE);
        circle1.addToList(shapesList);
        Circle circle2 = new Circle(new Point(400, 162), 90, Color.BLUE);
        circle2.addToList(shapesList);
        Circle circle3 = new Circle(new Point(400, 162), 120, Color.BLUE);
        circle3.addToList(shapesList);
        //the lines to draw
        Line line1 = new Line(new Point(400, 182), new Point(400, 302));
        line1.addToList(shapesList);
        line1.setColor(Color.BLUE);
        Line line2 = new Line(new Point(420, 162), new Point(540, 162));
        line2.addToList(shapesList);
        line2.setColor(Color.BLUE);
        Line line3 = new Line(new Point(380, 162), new Point(260, 162));
        line3.addToList(shapesList);
        line3.setColor(Color.BLUE);
        Line line4 = new Line(new Point(400, 142), new Point(400, 22));
        line4.addToList(shapesList);
        line4.setColor(Color.BLUE);
        //set new background according to the shapes
        Background back = new Background(backg, shapesList);
        return back;

    }

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocksArray = new ArrayList<Block>();
        Counter c = new Counter(1);
        Block block = new Block(new Rectangle(new Point(385, 150), 30, 30), Color.RED, c);
        blocksArray.add(block);
        return blocksArray;
    }

    /**
     * @return the number Of Blocks To Remove - 1
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
