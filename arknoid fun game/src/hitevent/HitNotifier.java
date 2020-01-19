package hitevent;

/**
 * HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * @param hl Add hl as a listener to hit events.
     */
    void addHitListener(HitListener hl);


    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the object to be removed.
     */
    void removeHitListener(HitListener hl);
}
