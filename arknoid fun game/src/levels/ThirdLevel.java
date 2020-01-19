package levels;
import game.Block;
import game.Sprite;
import game.Velocity;
import hitevent.Counter;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ThirdLevel class implements LevelInformation.
 */
public class ThirdLevel implements LevelInformation {
    //member
    private List<Block> blocks = new LinkedList();

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;

    /**
     * constructor.
     */
    public ThirdLevel() {
        //the color of the blocks
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        //how many times to hit each block
        int[] hitPoints = new int[]{2, 1, 1, 1, 1};
        int y = 150;
        int blockWidth = 25;

        //set the blocks
        for (int i = 0; i < colors.length; ++i) {
            for (int j = i + 5; j < 15; ++j) {
                this.blocks.add(new Block(new Rectangle(new Point(j * 50 + 25, y), 50, blockWidth),
                        colors[i], new Counter(hitPoints[i])));
            }

            y += blockWidth;
        }
    }

    /**
     * @return number Of the Balls - 2
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * @return the list of initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList();
        double speed = 10;
        velocities.add(Velocity.fromAngleAndSpeed(40, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-40, speed));
        return velocities;
    }

    /**
     * @return the paddle speed - 7
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
     * @return the level name: "Green 3"
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        //a list of the shape on this level
        List<Shapes> shapesList = new ArrayList<Shapes>();
        //the background rectangle -green on all over the screen
        Rectangle backg = new Rectangle(new Point(0, 20),
                WIDTH_SCREEN, HI_SCREEN);
        backg.setColor(new Color(42, 130, 21));
        //create rectangle  and add it to the shape list
        Rectangle rec1 = new Rectangle(new Point(110, 200), 10, 200);
        rec1.setColor(new Color(78, 74, 73));
        rec1.addToList(shapesList);
        //create 3 full circles and add them to the shape list
        FullCircle circle1 = new FullCircle(new Point(115, 200), 12, new Color(216, 172, 102));
        circle1.addToList(shapesList);
        FullCircle circle2 = new FullCircle(new Point(115, 200), 8, new Color(246, 77, 54));
        circle2.addToList(shapesList);
        FullCircle circle3 = new FullCircle(new Point(115, 200), 3, Color.WHITE);
        circle3.addToList(shapesList);
        //create 2 rectrangles and add them to the shape list
        Rectangle rec2 = new Rectangle(new Point(100, 400), 30, 200);
        rec2.setColor(new Color(62, 58, 57));
        rec2.addToList(shapesList);
        Rectangle rec3 = new Rectangle(new Point(65, 450), 100, 200);
        rec3.setColor(new Color(46, 42, 41));
        rec3.addToList(shapesList);
        int startX = 75;
        int startY = 460;
        //create white Rectangles and add them to the shape list
        for (int rows = 0; rows < 5; ++rows) {
            for (int columns = 0; columns < 5; ++columns) {
                Rectangle recALot = new Rectangle(new Point(startX + columns * 18, startY + rows * 32), 10, 25);
                recALot.addToList(shapesList);
                recALot.setColor(Color.WHITE);
            }
        }
        //set new background according to the shapes
        Background back = new Background(backg, shapesList);
        return back;
    }

    /**
     * @return the Blocks that make up this level, each block contains its size, color and location.
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
