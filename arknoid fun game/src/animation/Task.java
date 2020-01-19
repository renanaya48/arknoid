package animation;

/**
 * Task interface.
 * @param <T> generic.
 */
public interface Task<T> {
    /**
     * @return run the generic function
     */
    T run();
}
