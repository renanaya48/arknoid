package game;

import animation.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import hitevent.BallRemover;
import hitevent.BlockRemover;
import hitevent.Counter;
import hitevent.ScoreTrackingListener;
import levels.LevelIndicator;
import levels.LevelInformation;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * GameLevel class.
 */
public class GameLevel implements Animation {
    //members
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private Paddle paddle;
    private Counter blocksCounter;
    private Counter ballCounter;
    private Counter score = new Counter(0);
    private Counter numOfLives;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;
    static final int FIRST_ROW_OBJ = 7;
    static final int BLOCK_WIDTH = 50;
    static final int BLOCK_HI = 20;
    static final int NUM_OF_ROWS = 7;
    static final int START_NUM_LIVES = 4;
    static final int NUM_OF_BALLS = 2;
    static final int RADIUS_OF_BALL = 7;

    /**
     * @param levelInformation the level information
     */
    public GameLevel(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    /**
     * constructor.
     *
     * @param levelInformation levelInformation
     * @param keyboard         keyboard
     * @param runner           AnimationRunner
     * @param score            the score of the game
     * @param numOfLives       the number of lives
     * @param blocksCounter    the number of blocks
     * @param gui              GUI
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner,
                     Counter score, Counter numOfLives, Counter blocksCounter, GUI gui) {
        this.gui = gui;
        this.keyboard = keyboard;
        this.blocksCounter = blocksCounter;
        this.score = score;
        this.numOfLives = numOfLives;
        this.runner = runner;
        this.levelInformation = levelInformation;
    }

    /**
     * add the collidable to the game environment.
     *
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add the sprite to the sprites list.
     *
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * initialize the Blocks.
     */
    public void initializeBlocks() {
        //set a list of Blocks
        List<Block> arrayBlock = this.levelInformation.blocks();
        //set an array of BlockRemover
        BlockRemover[] removeBlock = new BlockRemover[arrayBlock.size()];
        ScoreTrackingListener[] countScore = new ScoreTrackingListener[arrayBlock.size()];
        //for each Block:
        for (int i = 0; i < arrayBlock.size(); i++) {
            //set a BlockRemover, count score, HitListener of remove and count blocks
            removeBlock[i] = new BlockRemover(this, arrayBlock.get(i).getNumOfBump());
            countScore[i] = new ScoreTrackingListener(arrayBlock.get(i).getNumOfBump());
            removeBlock[i].setRemainingBlocks(blocksCounter);
            countScore[i].setCurrentScore(score);
            arrayBlock.get(i).addHitListener(removeBlock[i]);
            arrayBlock.get(i).addHitListener(countScore[i]);
            //add the block to the game
            arrayBlock.get(i).addToGame(this);
        }

    }


  /*  public void addBlocksToTheGame(Block [] arrayBlocks, GameEnvironment game1, SpriteCollection sprite){
        for (int i = 0; i < arrayBlocks.length; i++) {
            game1.addCollidable((Collidable) arrayBlocks[i]);
            sprite.addSprite((Sprite) arrayBlocks[i]);
            arrayBlocks[i].addToGame(this);
        }
    }*/

    /**
     * initialize the Balls.
     */
    public void initializeBall() {
        //get the ball velocities
        List<Velocity> velo = this.levelInformation.initialBallVelocities();
        //create a list of balls
        List<Ball> balls = new ArrayList<Ball>();
        //for each ball
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            //set the ball according to: center point, radius, color and velocity
            Ball ball = new Ball(new Point(WIDTH_SCREEN / 2 - RADIUS_OF_BALL, HI_SCREEN - 50),
                    RADIUS_OF_BALL, Color.WHITE, velo.get(i));
            //add the ball to the list
            balls.add(ball);
            //set game environment and add the ball to the game
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            //remove the ball if it felt
            BallRemover removeBall = new BallRemover(this, ballCounter);
            removeBall.setRemainingBalls(ballCounter);
            ball.addHitListener(removeBall);

        }

    }

    /**
     * initialize the Paddle.
     */
    public void initializePaddle() {
        //create a rectangle to the paddle
        Rectangle forPaddel = new Rectangle(new Point(WIDTH_SCREEN / 2, HI_SCREEN - 50),
                this.levelInformation.paddleWidth(), 30);
        this.paddle = new Paddle(forPaddel, Color.GREEN, this.gui.getKeyboardSensor(),
                this.levelInformation.paddleSpeed());

    }

    /**
     * Initialize a new game: create the Blocks and Ball and Paddle
     * and add them to the game.
     */
    public void initialize() {
        /**********************************/
        //this.numOfLives=new hitevent.Counter(START_NUM_LIVES);
        //this.blocksCounter = new hitevent.Counter(this.levelInformation.numberOfBlocksToRemove());
        //this.gui = new GUI("jumping", WIDTH_SCREEN, HI_SCREEN);
        /*****************************************/
        //create new point to create the edges of the screen
        Point p1 = new Point(-5, 20);
        Point p2 = new Point(-5, -5);
        Point p3 = new Point(WIDTH_SCREEN - 20, -5);
        Point p4 = new Point(-5, HI_SCREEN - 15);
        //an array to the blocks of the edges
        Block[] edges = new Block[4];
        //create the adges of the screen
        Counter[] edgeCount = new Counter[4];
        edgeCount[0] = new Counter(1);
        edges[0] = new Block(new Rectangle(p1, WIDTH_SCREEN + 20, 25), Color.RED, edgeCount[0]);
        edgeCount[1] = new Counter(1);
        edges[1] = new Block(new Rectangle(p2, 25, HI_SCREEN + 20), Color.RED, edgeCount[1]);
        edgeCount[2] = new Counter(1);
        edges[2] = new Block(new Rectangle(p3, 25, HI_SCREEN + 20), Color.RED, edgeCount[2]);
        edgeCount[3] = new Counter(1);
        edges[3] = new Block(new Rectangle(p4, WIDTH_SCREEN + 20, 25), Color.RED, edgeCount[3]);

        //initilize the paddle
        initializePaddle();
        //sprite collection and game evnironment
        SpriteCollection sprite = new SpriteCollection();
        GameEnvironment game1 = new GameEnvironment();

        //add the Background of the level to the sprite collection
        this.addSprite(this.levelInformation.getBackground());
        //create score Indicator, lives Indicator and level name
        ScoreIndicator scoreIndicator1 = new ScoreIndicator(score);
        LivesIndicator livesIndicator1 = new LivesIndicator(numOfLives);
        LevelIndicator levelName = new LevelIndicator(this.levelInformation.levelName());

        game1.addCollidable((Collidable) this.paddle);
        sprite.addSprite((Sprite) this.paddle);
        sprite.addSprite((Sprite) scoreIndicator1);
        sprite.addSprite((Sprite) livesIndicator1);
        sprite.addSprite((Sprite) levelName);

        //add the paddle, score Indicator, lives Indicator and level name to the game
        this.paddle.addToGame(this);
        scoreIndicator1.addToGame(this);
        livesIndicator1.addToGame(this);
        levelName.addToGame(this);

        //initilize the Blocks
        initializeBlocks();

    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        //the number of balls in the game
        this.ballCounter = new Counter(this.levelInformation.numberOfBalls());
        Sleeper sleeper = new Sleeper();

        this.runner = new AnimationRunner(gui, 60, sleeper);
        this.paddle.getRectangle().setUpperLeft(new Point(WIDTH_SCREEN / 2 - (this.levelInformation.paddleWidth() / 2),
                HI_SCREEN - 40));
        //initialize the balls
        initializeBall();

        //start the game by counting down
        if (this.sprites != null) {
            this.runner.run(new CountdownAnimation(2, 4, this.sprites));
        }
        //run the game
        this.running = true;
        this.runner.run(this);

    }
/****************************
 public void run(){
 while (this.numOfLives.getValue()>0){
 playOneTurn();
 if(this.blocksCounter.getValue()==0){
 gui.close();
 }
 this.numOfLives.decrease(1);
 }
 gui.close();
 }******/

    /**
     * @param c Collidable to move from gameEnvironment
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeFromGameEnvironment(c);
    }

    /**
     * @param s Sprite to remove From Sprite List
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeFromSpriteList(s);
    }

    /**
     * @param d DrawSurface - to draw with
     * @param dt to change velocity
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        //draw all
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        this.paddle.timePassed(dt);

        //if there no more blocks, move to the next level
        if (this.blocksCounter.getValue() <= 0) {
            this.running = false;
        }
        //if there are no maor balls
        if (this.ballCounter.getValue() <= 0) {
            this.running = false;
        }
        //if the player want to pause the game
        this.keyboard = gui.getKeyboardSensor();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }


    /**
     * @return when the function needs to stop
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}

   /* public void playOneTurn() {
        int flagForBlocks=0, flagForBalls=0;
        Ball ball1 =initializeBall(70, 10, new Point(100, 260), 7, Color.WHITE);
        Ball ball2 = initializeBall(50, 8, new Point(400,160),7, Color.WHITE);
        this.paddle.getRectangle().setUpperLeft(new Point(WIDTH_SCREEN/2-90, HI_SCREEN-40));
        /------>>>>>>
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            this.paddle.timePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if(this.blocksCounter.getValue()==1){
                flagForBlocks =1;
            }
            if((this.blocksCounter.getValue()<=0)&&(flagForBlocks==1)){
                this.score.increase(100);
                break;
            }
            if(this.ballCounter.getValue()==1){
                flagForBalls=1;
            }
            if((this.ballCounter.getValue()<=0)&&(flagForBalls==1)){
                break;
                //gui.close();
            }
        }
    }*/