package menu;

import animation.AnimationRunner;
import animation.Task;

/**
 * QuitMenu class implements Task<Void>.
 */
public class QuitMenu implements Task<Void> {
    //members
    private AnimationRunner quitGame;

    /**
     * @param quitGame1 AnimationRunner
     */
    public QuitMenu(AnimationRunner quitGame1) {
        this.quitGame = quitGame1;
    }

    /**
     * exit from menu.
     *
     * @return null
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}
