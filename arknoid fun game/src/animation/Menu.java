package animation;

/**
 * menu interface.
 * @param <T> generic
 */
public interface Menu<T> extends Animation {
    /**
     * @param key       String
     * @param message   String
     * @param returnVal generic
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the status
     */
    T getStatus();

    /**
     * @param key     String
     * @param message String
     * @param subMenu Menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
