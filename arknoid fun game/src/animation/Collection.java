package animation;

/**
 *
 * @param <T> generic
 */
public class Collection<T> {
    //members
    private String key;
    private String message;
    private T status;

    /**
     * @param key     string
     * @param message string
     * @param status  genery
     */
    public Collection(String key, String message, T status) {
        this.key = key;
        this.message = message;
        this.status = status;
    }

    /**
     * @return the key value
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the message to print
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the status
     */
    public T getStatus() {
        return status;
    }
}
