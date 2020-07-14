package levels;

import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public interface BlockCreator {

    /**
     * @param xpos the value of x
     * @param ypos the value of y
     * @param s the string
     * @return a new block
     */
    Block create(int xpos, int ypos, String s);
 }
