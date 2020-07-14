package game;

/**
 *
 * @author hadar
 * @param <T>
 */
public interface Task<T> {

    /**
     *
     * @return the task object
     */
    T run();
}
