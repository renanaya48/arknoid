package levels;
import game.Block;
import game.Sprite;
import game.Velocity;
import hitevent.Counter;
import levels.Background;
import levels.FullCircle;
import levels.LevelInformation;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * SecondLeve class implements LevelInformation.
 */
public class SecondLeve implements LevelInformation {

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;

    /**
     * constructor.
     */
    public SecondLeve() {
    }

    /**
     * @return number Of the Balls-10
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * @return the list of initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList();
        double speed = 10;
        velocities.add(Velocity.fromAngleAndSpeed(50, speed));
        velocities.add(Velocity.fromAngleAndSpeed(40, speed));
        velocities.add(Velocity.fromAngleAndSpeed(30, speed));
        velocities.add(Velocity.fromAngleAndSpeed(20, speed));
        velocities.add(Velocity.fromAngleAndSpeed(10, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-10, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-20, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-30, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-40, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-50, speed));
        return velocities;
    }

    /**
     * @return the paddle speed - 8
     */
    @Override
    public int paddleSpeed() {
        return 8;
    }

    /**
     * @return the paddle width - 600
     */
    @Override
    public int paddleWidth() {
        return 600;
    }

    /**
     * @return the level name - "Wide Easy"
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        //a list of the shape on this level
        List<Shapes> shapesList = new ArrayList<Shapes>();
        //the background rectangle - white on all over the screen
        Rectangle backg = new Rectangle(new Point(0, 20),
                WIDTH_SCREEN, HI_SCREEN);
        backg.setColor(Color.WHITE);
        //create full circle and add it to the shape list
        FullCircle circle1 = new FullCircle(new Point(150, 150), 60, new Color(239, 231, 176));
        circle1.addToList(shapesList);
        int numRays = 100;
        int startX = 25;
        int endX = 775;

        //create 100 lines and add them to the shape list
        for (int i = 1; i <= numRays; ++i) {
            Line line = new Line(150, 150, (endX - startX) / numRays * i, 250);
            line.setColor(new Color(239, 231, 176));
            line.addToList(shapesList);
        }
        //create 2 full circle and add them to the shape list
        FullCircle circle2 = new FullCircle(new Point(150, 150), 50, new Color(236, 215, 73));
        circle2.addToList(shapesList);
        FullCircle circle3 = new FullCircle(new Point(150, 150), 40, new Color(255, 225, 24));
        circle3.addToList(shapesList);
        //set new background according to the shapes
        Background back = new Background(backg, shapesList);
        return back;
    }

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList();
        int y = 250;
        int blockHeight = 25;
        int blockWidth = 50;
        Counter c = new Counter(1);
        blocks.add(new Block(new Rectangle(new Point(25, y), blockWidth, blockHeight), Color.RED, c));
        blocks.add(new Block(new Rectangle(new Point(75, y), blockWidth, blockHeight), Color.RED, c));
        blocks.add(new Block(new Rectangle(new Point(125, y), blockWidth, blockHeight), Color.ORANGE, c));
        blocks.add(new Block(new Rectangle(new Point(175, y), blockWidth, blockHeight), Color.ORANGE, c));
        blocks.add(new Block(new Rectangle(new Point(225, y), blockWidth, blockHeight), Color.YELLOW, c));
        blocks.add(new Block(new Rectangle(new Point(275, y), blockWidth, blockHeight), Color.YELLOW, c));
        blocks.add(new Block(new Rectangle(new Point(325, y), blockWidth, blockHeight), Color.GREEN, c));
        blocks.add(new Block(new Rectangle(new Point(375, y), blockWidth, blockHeight), Color.GREEN, c));
        blocks.add(new Block(new Rectangle(new Point(425, y), blockWidth, blockHeight), Color.GREEN, c));
        blocks.add(new Block(new Rectangle(new Point(475, y), blockWidth, blockHeight), Color.BLUE, c));
        blocks.add(new Block(new Rectangle(new Point(525, y), blockWidth, blockHeight), Color.BLUE, c));
        blocks.add(new Block(new Rectangle(new Point(575, y), blockWidth, blockHeight), Color.PINK, c));
        blocks.add(new Block(new Rectangle(new Point(625, y), blockWidth, blockHeight), Color.PINK, c));
        blocks.add(new Block(new Rectangle(new Point(675, y), blockWidth, blockHeight), Color.CYAN, c));
        blocks.add(new Block(new Rectangle(new Point(725, y), blockWidth, blockHeight), Color.CYAN, c));
        return blocks;

    }

    /**
     * @return the number Of Blocks To Remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
