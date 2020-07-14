package animation;

/**
 *
 * @author hadar
 * @param <T>
 */
public interface Menu<T> extends Animation {

    /**
     * @param key the given key to stop
     * @param message the given message
     * @param returnVal the given return value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     *
     * @return the status.
     */
    T getStatus();

    /**
     * @param key the given key to stop
     * @param message the given message
     * @param subMenu the given subMenu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
 }
