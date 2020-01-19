package menu;

import animation.AnimationRunner;
import animation.Task;

/**
 * StartGameTask class implements Task<Void>.
 */
public class StartGameTask implements Task<Void> {
    //members
    private AnimationRunner runner;

    /**
     * @param runner1 AnimationRunner
     */
    public StartGameTask(AnimationRunner runner1) {
        this.runner = runner1;
    }

    /**
     * @return null
     */
    public Void run() {
        return null;
    }
}
