package game;

import animation.*;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import hitevent.Counter;
import levels.LevelInformation;
import menu.MenuAnimation;
import menu.SubMenuAnimation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {
    //members
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter numOfLives;
    private Counter blocksCounter;
    private GUI gui;
    private HighScoresTable highScoresTable;

    //final numbers
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;
    static final int START_NUM_LIVES = 7;
    static final int MAX_IN_HISCORE = 3;


    /**
     * constructor.
     *
     * @param animationRunner animationRunner
     * @param keyboardSensor  keyboardSensor
     * @param score           the score of the game
     * @param numOfLives      number of lives that left
     * @param blocksCounter   the number of blocks that left
     * @param gui             GUI
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor,
                    Counter score, Counter numOfLives, Counter blocksCounter, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.score = score;
        this.numOfLives = numOfLives;
        this.blocksCounter = blocksCounter;
        this.gui = gui;
    }

    /**
     * run the game according to the current level.
     *
     * @param levels a list of the levels to run
     */
    public void runLevels(List<LevelInformation> levels, List<LevelInformation> otherLevelList) {
        File highscores = new File("hiScore");
        this.highScoresTable = new HighScoresTable(MAX_IN_HISCORE);
        if (highscores.exists()) {
            highScoresTable.load(highscores);
        } else {
            highScoresTable.save(highscores);
        }
        //run on all over the levels
        for (LevelInformation levelInfo : levels) {
            //get the current number of the blocks
            this.blocksCounter = new Counter(levelInfo.numberOfBlocksToRemove());

            //set the game level
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.score, this.numOfLives, this.blocksCounter, this.gui);


            //initialize the game according to the current level
            level.initialize();

            //if level has more blocks and player has more lives
            while ((this.numOfLives.getValue() > 0) && (this.blocksCounter.getValue() > 0)) {
                level.playOneTurn();
                //if the player lost all the balls
                if (this.blocksCounter.getValue() != 0) {
                    //less 1 life
                    this.numOfLives.decrease(1);
                }

            }

            //if the player lost the game
            if (this.numOfLives.getValue() == 0) {
                //end the game and print him a message
                //check score
                checkScores();
                Animation gameOver = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(gui.getKeyboardSensor(), this.score.getValue()));
                this.animationRunner.run(gameOver);
                //this.animationRunner.run(new EndScreen(gui.getKeyboardSensor(), this.score.getValue()));
                highScoresTable.save(highscores);
                Animation hiScoreScreen = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(highScoresTable, KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor()));
                this.animationRunner.run(hiScoreScreen);
                runMenu(levels, otherLevelList);
                return;

                //gui.close();
            } else {
                //if the level complete-get 100 scores
                this.score.increase(100);
            }

        }

        //if the player win the game- print a message and end the game
        checkScores();
        Animation finishWinner = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreenWinner(gui.getKeyboardSensor(), this.score.getValue()));
        this.animationRunner.run(finishWinner);
        //this.animationRunner.run(new EndScreenWinner(gui.getKeyboardSensor(), this.score.getValue()));
        //HighScoresAnimation showHiScore = new HighScoresAnimation(this.highScoresTable, "way",
        //gui.getKeyboardSensor());
        highScoresTable.save(highscores);
        Animation hiScoreScreen = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(highScoresTable, KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor()));
        this.animationRunner.run(hiScoreScreen);
        //this.animationRunner.run(new HighScoresAnimation(highScoresTable, "m", gui.getKeyboardSensor()));
        //gui.close();
        runMenu(levels, otherLevelList);
/*
        //run on all over the levels
        for (LevelInformation levelInfo : levels) {
            //get the current number of the blocks
            this.blocksCounter = new Counter(levelInfo.numberOfBlocksToRemove());

            //set the game level
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.score, this.numOfLives, this.blocksCounter, this.gui);


            //initialize the game according to the current level
            level.initialize();

            //if level has more blocks and player has more lives
            while ((this.numOfLives.getValue() > 0) && (this.blocksCounter.getValue() > 0)) {
                level.playOneTurn();
                //if the player lost all the balls
                if (this.blocksCounter.getValue() != 0) {
                    //less 1 life
                    this.numOfLives.decrease(1);
                }

            }

            //if the player lost the game
            if (this.numOfLives.getValue() == 0) {
                //end the game and print him a message
                this.animationRunner.run(new EndScreen(gui.getKeyboardSensor(), this.score.getValue()));
                gui.close();
            }
            //if the level complete-get 100 scores
            this.score.increase(100);
        }

        //if the player win the game- print a message and end the game
        this.animationRunner.run(new EndScreenWinner(gui.getKeyboardSensor(), this.score.getValue()));
        gui.close();
*/
    }

    /**
     * checks if the score get in to the hi-score table.
     */
    public void checkScores() {
        if (highScoresTable.size() < MAX_IN_HISCORE) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            System.out.println(name);
            this.highScoresTable.add(new ScoreInfo(name, this.score.getValue()));
        } else {
            if (highScoresTable != null) {
                if (this.score.getValue() > this.highScoresTable.getHighScores()
                        .get(highScoresTable.size() - 1).getScore()) {
                    DialogManager dialog = gui.getDialogManager();
                    String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                    System.out.println(name);
                    this.highScoresTable.add(new ScoreInfo(name, this.score.getValue()));
                }
            }
        }
    }

    /**
     * run the menu.
     *
     * @param easyLevels list of the easy levels
     * @param hardLevels list of the hard levels
     * @throws Exception if something get wrong
     */
    public void runMenu(List<LevelInformation> easyLevels, List<LevelInformation> hardLevels) throws Exception {
        File highscores1 = new File("hiScore");
        this.highScoresTable = new HighScoresTable(MAX_IN_HISCORE);
        if (highscores1.exists()) {
            highScoresTable.load(highscores1);
        } else {
            highScoresTable.save(highscores1);
        }
        MenuAnimation<Task> menu = new MenuAnimation<Task>(keyboardSensor);
        //SubMenuAnimation<Task> subMenu=new SubMenuAnimation<Task>(keyboardSensor);
        //Menu<Task<Void>> menu=new MenuAnimation<Task<Void>>(keyboardSensor);

        menu.addSelection("s", "START GAME", new Task<Void>() {
            @Override
            public Void run() {

                try {
                    runSubMenu(easyLevels, hardLevels);
                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;
            }
        });
        menu.addSelection("h", "Hi SCORE TABLE", new Task<Void>() {
            @Override
            public Void run() {
                runHiscore();
                return null;
            }
        });
        menu.addSelection("q", "QUIT", new Task<Void>() {
            @Override
            public Void run() {
                gui.close();
                return null;
            }
        });
        while (true) {
            numOfLives.setCounter(START_NUM_LIVES);
            score.setCounter(0);
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            //task.run();

            if (task != null) {
                task.run();
            }
            menu.initializeStatus();

        }

    }

    /**
     * run the hi score table.
     */
    public void runHiscore() {
        if (this.highScoresTable != null) {
            Animation hiScoreScreen = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new HighScoresAnimation(this.highScoresTable, KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor()));
            this.animationRunner.run(hiScoreScreen);
        }
    }

    /**
     * run the sub menu.
     *
     * @param easyLevels list of the easy levels
     * @param hardLevels list of the hard levels
     * @throws Exception if something get wrong
     */
    public void runSubMenu(List<LevelInformation> easyLevels, List<LevelInformation> hardLevels) throws Exception {
        SubMenuAnimation<Task> subMenu = new SubMenuAnimation<Task>(keyboardSensor);
        subMenu.addSelection("e", "EAZY", new Task() {
            @Override
            public Void run() {
                try {
                    //runLevels(levels);
                    runLevels(easyLevels, hardLevels);
                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;
            }
        });
        subMenu.addSelection("h", "HARD", new Task() {
            @Override
            public Void run() {
                try {
                    //runLevels(levels);
                    runLevels(hardLevels, easyLevels);
                } catch (IOException e) {
                    System.out.println(e);
                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;
            }
        });

        while (true) {
            score.setCounter(0);
            numOfLives.setCounter(START_NUM_LIVES);
            animationRunner.run(subMenu);
            Task<Void> task = subMenu.getStatus();
            //task.run();

            if (task != null) {
                task.run();
            }

            subMenu.initializeStatus();
        }

    }
}
