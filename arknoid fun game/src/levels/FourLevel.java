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
import java.util.LinkedList;
import java.util.List;

/**
 * FourLevel class implements LevelInformation.
 */
public class FourLevel implements LevelInformation {
    //member
    private List<Block> blocks = new LinkedList();

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;

    /**
     * FourLevel constructor.
     */
    public FourLevel() {
        //the color of the blocks
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN,
                Color.WHITE, Color.PINK, Color.CYAN};
        //how many times to hit each block
        int[] hitPoints = new int[]{2, 1, 1, 1, 1, 1, 1};
        int y = 100;
        int blockWidth = 25;

        //set the blocks
        for (int i = 0; i < colors.length; ++i) {
            for (int j = 0; j < 15; ++j) {
                this.blocks.add(new Block(new Rectangle(new Point(j * 50 + 25, y), 50, blockWidth),
                        colors[i], new Counter(hitPoints[i])));
            }

            y += blockWidth;
        }

    }

    /**
     * @return number Of the Balls-3
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * @return the list of initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList();
        double speed = 10;
        velocities.add(Velocity.fromAngleAndSpeed(40, speed));
        velocities.add(Velocity.fromAngleAndSpeed(175, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-40, speed));
        return velocities;
    }

    /**
     * @return the  paddle Speed-7
     */
    @Override
    public int paddleSpeed() {
        return 7;
    }

    /**
     * @return the paddle width - 85
     */
    @Override
    public int paddleWidth() {
        return 85;
    }

    /**
     * @return the level name: "Final Four".
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        //a list of the shape on this level
        List<Shapes> shapesList = new ArrayList<Shapes>();
        //the background rectangle - light blue on all over the screen
        Rectangle backg = new Rectangle(new Point(0, 20),
                WIDTH_SCREEN, HI_SCREEN);
        backg.setColor(new Color(23, 136, 208));
        //create 10 white lines and add them to the shape list
        for (int i = 0; i < 10; ++i) {
            Line line = new Line(105 + i * 10, 400, 80 + i * 10, 600);
            line.setColor(Color.WHITE);
            line.addToList(shapesList);
        }
        //create 5 full circle and add them to the shape list
        FullCircle circle1 = new FullCircle(new Point(100, 400), 23, new Color(204, 204, 204));
        circle1.addToList(shapesList);
        FullCircle circle2 = new FullCircle(new Point(120, 420), 27, new Color(204, 204, 204));
        circle2.addToList(shapesList);
        FullCircle circle3 = new FullCircle(new Point(140, 390), 29, new Color(187, 187, 187));
        circle3.addToList(shapesList);
        FullCircle circle4 = new FullCircle(new Point(160, 420), 22, new Color(170, 170, 170));
        circle4.addToList(shapesList);
        FullCircle circle5 = new FullCircle(new Point(180, 400), 32, new Color(170, 170, 170));
        circle5.addToList(shapesList);
        //create 10 white lines and add them to the shape list
        for (int i = 0; i < 10; ++i) {
            Line line = new Line(605 + i * 10, 520, 580 + i * 10, 600);
            line.setColor(Color.WHITE);
            line.addToList(shapesList);
        }
        //create 5 full circle and add them to the shape list
        FullCircle circle6 = new FullCircle(new Point(600, 500), 23, new Color(204, 204, 204));
        circle6.addToList(shapesList);
        FullCircle circle7 = new FullCircle(new Point(620, 540), 27, new Color(204, 204, 204));
        circle7.addToList(shapesList);
        FullCircle circle8 = new FullCircle(new Point(640, 510), 29, new Color(187, 187, 187));
        circle8.addToList(shapesList);
        FullCircle circle9 = new FullCircle(new Point(660, 530), 22, new Color(170, 170, 170));
        circle9.addToList(shapesList);
        FullCircle circle10 = new FullCircle(new Point(680, 520), 32, new Color(170, 170, 170));
        circle10.addToList(shapesList);

        //set new background according to the shapes
        Background back = new Background(backg, shapesList);
        return back;

    }

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return the number Of Blocks To Remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }
}
