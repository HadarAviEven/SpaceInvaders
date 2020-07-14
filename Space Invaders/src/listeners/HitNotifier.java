package listeners;

/**
 *
 * @author hadar
 *
 */
public interface HitNotifier {

    /**
     *
     * @param hl a given HitListener
     *
     * Add hl as a listener to hit events.
     */
   void addHitListener(HitListener hl);

   /**
    *
    * @param hl a given HitListener
    *
    * Remove hl from the list of listeners to hit events.
    */
   void removeHitListener(HitListener hl);
}