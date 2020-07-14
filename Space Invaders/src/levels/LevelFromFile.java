package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 *
 * @author hadar
 *
 */
public class LevelFromFile {
    private Map<String, String> mapLevel;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private int numOfParameters = 0;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numBlocks;
    private String name;
    private String whatColor = "";
    private ColorsParser colorsParser = new ColorsParser();
    private Color color;

    /**
     *
     * @param reader
     *            the given reader
     * @return list of levels
     * @throws IOException
     *             an exception.
     */
    public LevelInformation fromReader(java.io.Reader reader) throws IOException {
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.mapLevel = new HashMap<String, String>();
        ReadingSingleLevelFile readingSingleLevel = new ReadingSingleLevelFile();

        try {
            List<String> listLevel = readingSingleLevel.fromReader(reader);
            for (int i = 0; i < listLevel.size(); i++) {
                if (listLevel.get(i).contains(":")) {
                    String[] s = listLevel.get(i).split(":");
                    mapLevel.put(s[0], s[1]);
                }
            }
            this.check();

            if (this.numOfParameters < 10) {
                System.out.println("Not enough parameters");
                System.exit(1);
            }

            this.blocks = new ArrayList<Block>();
            boolean hasFoundStartBlock = false;

            int x = this.blocksStartX;
            int y = this.blocksStartY;

            for (int i = 0; i < listLevel.size(); i++) {
                x = this.blocksStartX;
                if (listLevel.get(i).equals("START_BLOCKS")) {
                    hasFoundStartBlock = true;
                    continue;
                }
                if (hasFoundStartBlock) {
                    BufferedReader br = null;
                    FileReader fr = null;
                    fr = new FileReader(this.name);
                    br = new BufferedReader(fr);
                    BlocksFromSymbolsFactory mBlocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(br);

                    if (!listLevel.get(i).equals("END_BLOCKS")) {
                        String line = listLevel.get(i);
                        char[] chars = line.toCharArray();
                        if (!isLineOfSpacers(chars, mBlocksFromSymbolsFactory)) {
                            int maxHeight = 0;
                            for (int charPos = 0, length = chars.length; charPos < length; charPos++) {
                                String currChar = String.valueOf(chars[charPos]);
                                if (!mBlocksFromSymbolsFactory.isSpaceSymbol(currChar)) {
                                    Block block = mBlocksFromSymbolsFactory.getBlock(currChar, x, y);
                                    this.blocks.add(block);
                                    x += block.getCollisionRectangle().getWidth();
                                    double height = block.getCollisionRectangle().getHeight();
                                    if (height > maxHeight) {
                                        maxHeight = (int) height;
                                    }
                                } else {
                                    x += mBlocksFromSymbolsFactory.getSpaceWidth("width");
                                }
                            }
                            y += maxHeight;
                        } else {
                            y += rowHeight;
                        }
                    } else {
                        break;
                    }
                }
            }

            this.numberOfBlocksToRemove = this.numBlocks;
        } catch (IOException e) {
            System.out.println("Something went wrong while reading");
        } finally {
            reader.close();
        }
        LevelInformation level = new NewLevel(this.initialBallVelocities, this.paddleSpeed,
                this.paddleWidth, this.levelName, this.background, this.blocks, this.numberOfBlocksToRemove);
        return level;
    }

    /**
     * @param line the given line
     * @param blocksFromSymbolsFactory the given blocksFromSymbolsFactory
     * @return if the line contains only spacers
     */
    public boolean isLineOfSpacers(char[] line, BlocksFromSymbolsFactory blocksFromSymbolsFactory) {
        boolean isLineSpacer = true;
        for (int i = 0; i < line.length; i++) {
            if (!blocksFromSymbolsFactory.isSpaceSymbol(String.valueOf(line[i]))) {
                isLineSpacer = false;
                break;
            }
        }
        return isLineSpacer;
    }

    /**
     * move over the map.
     */
    public void check() {
        for (Map.Entry<String, String> entry : this.mapLevel.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals("level_name")) {
                this.numOfParameters++;
                this.levelName = value;
            } else if (key.equals("ball_velocities")) {
                this.numOfParameters++;
                int spaceCount = 0;
                for (char c : value.toCharArray()) {
                    if (c == ' ') {
                        spaceCount++;
                    }
                }
                if (spaceCount > 0) {
                    String[] spaceParts = value.split(" ");
                    for (int i = 0; i < spaceCount + 1; i++) {
                        String newPart = spaceParts[i];
                        String[] commaParts = newPart.split(",");
                        Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(commaParts[0]),
                                Double.parseDouble(commaParts[1]));
                        this.initialBallVelocities.add(v);
                    }
                } else {
                    String[] commaParts = value.split(",");
                     Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(commaParts[0]),
                     Double.parseDouble(commaParts[1]));
                     this.initialBallVelocities.add(v);
                }
            } else if (key.equals("background")) {
                this.numOfParameters++;
                if (value.startsWith("image")) {
                    if (value.contains("/")) {
                        String[] imgParts = value.split("/");
                        int index = imgParts[1].indexOf(")");
                        String imgName = imgParts[1].substring(0, index);
                        Image img = null;
                        try {
                            img = ImageIO.read(new File(imgName));
                            this.background = new Background0(this.levelName, img);
                        } catch (IOException e) {
                            System.out.println("Something went wrong while opening the photo");
                        }
                    } else {
                        int startIndex = value.indexOf("(");
                        int endIndex = value.indexOf(")");
                        String imgName = value.substring(startIndex + 1, endIndex);
                        Image img = null;
                        try {
                            img = ImageIO.read(new File(imgName));
                            this.background = new Background0(this.levelName, img);
                        } catch (IOException e) {
                            System.out.println("Something went wrong while opening the photo");
                        }
                    }
                } else if (value.startsWith("color")) {
                    whatColor = value.substring(6, value.length() - 1);
                    this.color = colorsParser.colorFromString(whatColor);
                    this.background = new Background0(this.levelName, this.color);
                } else {
                    System.out.println("Ilegal parameter");
                    System.exit(1);
                }
            } else if (key.equals("paddle_speed")) {
                this.numOfParameters++;
                this.paddleSpeed = Integer.parseInt(value);
            } else if (key.equals("paddle_width")) {
                this.numOfParameters++;
                this.paddleWidth = Integer.parseInt(value);
            } else if (key.equals("blocks_start_x")) {
                this.numOfParameters++;
                this.blocksStartX = Integer.parseInt(value);
            } else if (key.equals("blocks_start_y")) {
                this.numOfParameters++;
                this.blocksStartY = Integer.parseInt(value);
            } else if (key.equals("block_definitions")) {
                this.numOfParameters++;
                if (value.contains("/")) {
                    String[] parts = value.split("/");
                    this.name = parts[1];
                } else {
                    this.name = value;
                }
            } else if (key.equals("row_height")) {
                this.numOfParameters++;
                this.rowHeight = Integer.parseInt(value);
            } else if (key.equals("num_blocks")) {
                this.numOfParameters++;
                this.numBlocks = Integer.parseInt(value);
            }
        }
    }
}
