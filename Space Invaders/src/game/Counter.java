package game;
/**
 *
 * @author hadar
 *
 */
public abstract class Counter {
    private int currentCount;

    /**
     * constructor.
     *
     * @param num a given number
     */
    public Counter(int num) {
        this.currentCount = num;
    }

    /**
     * add number to current count.
     *
     * @param number a new number
     */
    public void increase(int number) {
        this.currentCount += number;
   }

   /**
    * subtract number from current count.
    *
    * @param number a new number
    */
    public void decrease(int number) {
        this.currentCount -= number;
   }

   /**
    * get current count.
    *
    * @return the current number
    */
    public int getValue() {
        return this.currentCount;
   }
}