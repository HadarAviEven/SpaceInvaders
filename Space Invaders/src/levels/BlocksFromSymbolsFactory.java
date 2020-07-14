package levels;

import java.util.Map;

import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    private Map<String, String> spacerSymbols;

    /**
     * constructor.
     *
     * @param spacerWidths the given spacer widths
     * @param blockCreators the given block creators
     * @param spacerSymbols the given spacer symbols
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators,
            Map<String, String> spacerSymbols) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
        this.spacerSymbols = spacerSymbols;
    }

    /**
     *
     * @param s the given string
     * @return if it is a space symbol
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerSymbols.containsValue(s);
    }

    /**
     *
     * @param s the given string
     * @return if it is a block symbol
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * @param s a block symbol
     * @param xpos the value of x
     * @param ypos the value of y
     * @return a new block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        BlockCreator blockCreator = this.blockCreators.get(s);
        if (blockCreator != null) {
            Block block = blockCreator.create(xpos, ypos, s);
            return block;
        }
        return null;
     }

    /**
     *
     * @param s the given string
     * @return the width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
     }

    /**
     *
     * @param s the given string
     * @return the symbol
     */
    public String getSpaceSymbol(String s) {
        return this.spacerSymbols.get(s);
     }

    /**
     * @return the block creators.
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockCreators;
    }
 }
