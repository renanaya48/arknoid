
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.GameFlow;
import hitevent.Counter;
import levels.*;

import java.util.ArrayList;
import java.util.List;

/**
 * game.Ass5Game class.
 */
public class Ass5Game {
    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;
    static final int START_NUM_LIVES = 1;

    /**
     * main function.
     *
     * @param args value to the main
     */
    public static void main(String[] args) throws Exception {
        List<LevelInformation> levelInformationList = new ArrayList<LevelInformation>();
        List<LevelInformation> list = new ArrayList<LevelInformation>();
        FirstLevel level1 = new FirstLevel();

        SecondLeve level2 = new SecondLeve();

        ThirdLevel level3 = new ThirdLevel();

        FourLevel level4 = new FourLevel();

        //game.GameLevel game = new game.GameLevel(level4);
        GUI gui = new GUI("jumping", WIDTH_SCREEN, HI_SCREEN);
        Sleeper sleeper = new Sleeper();
        AnimationRunner runner = new AnimationRunner(gui, 60, sleeper);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        Counter score = new Counter(0);
        Counter numOfLives = new Counter(START_NUM_LIVES);
        Counter blockCounter = new Counter(level1.numberOfBlocksToRemove());
        GameFlow game = new GameFlow(runner, keyboardSensor, score, numOfLives, blockCounter, gui);
        //animation.Animation animation = new KeyPressStoppableAnimation(keyboardSensor,"h",
        //      new HighScoresAnimation(highScoresTable, KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor()));
        //AnimationRunner ar=new AnimationRunner(gui, 60, sleeper);
        if (args.length == 0) {
            levelInformationList.add(level1);
            levelInformationList.add(level2);
            levelInformationList.add(level3);
            levelInformationList.add(level4);
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levelInformationList.add(level1);
            }
            if (args[i].equals("2")) {
                levelInformationList.add(level2);
            }
            if (args[i].equals("3")) {
                levelInformationList.add(level3);
            }
            if (args[i].equals("4")) {
                levelInformationList.add(level4);
            }
        }try {
            game.runLevels(levelInformationList, list);
        }catch (Exception e){
            System.out.println(e);
        }
        //game.runMenu(levelInformationList, list);
        /*

       /* while (true) {
            MenuAnimation<Task> menu = new MenuAnimation<Task>(keyboardSensor);
            menu.addSelection("h", "Hi scores", new ShowHiScoresTask(runner, keyboardSensor));
            menu.addSelection("q", "QUIT", new QuitMenu(runner));
            menu.addSelection("s", "START GAME", new StartGameTask(runner));
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            if (task != null) {
                task.run();
            }

            game.runLevels(levelInformationList);
            menu.initializeStatus();
        }*/
    }

}