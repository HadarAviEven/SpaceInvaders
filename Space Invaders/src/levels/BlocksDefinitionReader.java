package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hadar
 *
 */
public class BlocksDefinitionReader {

    /**
     *
     * @param reader
     *            the given reader
     * @return a BlocksFromSymbolsFactory object
     * @throws IOException
     *             an exception.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
        String symbol = "";
        int width = 0;
        int height = 0;
        String hitPoints = "";
        Color stroke = null;
        Color color = null;
        String defaultSymbol = "";
        int defaultWidth = 0;
        int defaultHeight = 0;
        String defaultHitPoints = "";
        Color defaultStroke = null;
        Image img = null;
        boolean isImage = false;
        boolean thereIsStroke = false;
        String whatColor = "";
        Fill fill = new Fill();
        ColorsParser colorsParser = new ColorsParser();
        Map<String, String> defaultMap = new HashMap<String, String>();
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
        List<Map<String, String>> bdefList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> sdefList = new ArrayList<Map<String, String>>();
        ReadingSingleBlockFile readingSingleBlock = new ReadingSingleBlockFile();
        List<Fill> listOfFills = new ArrayList<Fill>();
        Help help1 = new Help();
        try {
            List<String> listBlock = readingSingleBlock.fromReader(reader);
            for (int i = 0; i < listBlock.size(); i++) {
//                if (listBlock.get(i).startsWith("#")) {
                if (listBlock.get(i).startsWith("default")) {
                    String line = listBlock.get(i).substring(8, listBlock.get(i).length());
                    String[] spacesParts = line.split(" ");
                    for (int j = 0; j < spacesParts.length; j++) {
                        String[] dotsParts = spacesParts[j].split(":");
                        defaultMap.put(dotsParts[0], dotsParts[1]);
                    }
                } else if (listBlock.get(i).startsWith("bdef")) {
                    Map<String, String> bdefMap = new HashMap<String, String>();
                    String line = listBlock.get(i).substring(5, listBlock.get(i).length());
                    String[] spacesParts = line.split(" ");
                    for (int j = 0; j < spacesParts.length; j++) {
                        String[] dotsParts = spacesParts[j].split(":");
                        bdefMap.put(dotsParts[0], dotsParts[1]);
                    }
                    bdefList.add(bdefMap);
                } else if (listBlock.get(i).startsWith("sdef")) {
                    Map<String, String> sdefMap = new HashMap<String, String>();
                    String line = listBlock.get(i).substring(5, listBlock.get(i).length());
                    String[] spacesParts = line.split(" ");
                    for (int j = 0; j < spacesParts.length; j++) {
                        String[] dotsParts = spacesParts[j].split(":");
                        sdefMap.put(dotsParts[0], dotsParts[1]);
                    }
                    sdefList.add(sdefMap);
                }
            }
            Map<String, Integer> spacerWidths = new HashMap<String, Integer>();
            Map<String, String> spacerSymbols = new HashMap<String, String>();
            help1.helper(sdefList, spacerSymbols, spacerWidths);
            Map<String, BlockCreator> blockCreators = new HashMap<String, BlockCreator>();
            for (int t = 0; t < bdefList.size(); t++) {
                fill = new Fill();
                for (Map.Entry<String, String> entry : bdefList.get(t).entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key.equals("symbol")) {
                        symbol = help1.helper7(value, symbol);
                    } else if (key.equals("width")) {
                        width = help1.helper8(value, width);
                    } else if (key.equals("height")) {
                        height = help1.helper9(value, height);
                    } else if (key.equals("hit_points")) {
                        hitPoints = help1.helper10(value, hitPoints);
                    } else if (key.contains("fill")) {
                        String line = key + ":" + value;
                        fill.addToFill(line);
                    } else if (key.equals("stroke")) {
                        thereIsStroke = true;
                        if (value.startsWith("color")) {
                            whatColor = value.substring(6, value.length() - 1);
                            stroke = colorsParser.colorFromString(whatColor);
                        } else {
                            help1.ilegalParameter();
                        }
                    }
                }
                for (Map.Entry<String, String> entry : defaultMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key.equals("symbol")) {
                        if (value.length() == 1) {
                            defaultSymbol = value;
                        }
                    } else if (key.equals("width")) {
                        if (Integer.valueOf(value) > 0) {
                            defaultWidth = Integer.valueOf(value);
                        }
                    } else if (key.equals("height")) {
                        if (Integer.valueOf(value) > 0) {
                            defaultHeight = Integer.valueOf(value);
                        }
                    } else if (key.equals("hit_points")) {
                        if (Integer.valueOf(value) > 0) {
                            defaultHitPoints = value;
                        }
                    } else if (key.equals("stroke")) {
                        if (value.startsWith("color")) {
                            thereIsStroke = true;
                            whatColor = value.substring(6, value.length() - 1);
                            defaultStroke = colorsParser.colorFromString(whatColor);
                        }
                    }
                }
                symbol = help1.helper2(symbol, defaultSymbol);
                width = help1.helper3(width, defaultWidth);
                height = help1.helper4(height, defaultHeight);
                hitPoints = help1.helper5(hitPoints, defaultHitPoints);
                stroke = help1.helper6(stroke, defaultStroke);
                fill.setFillList();
                List<String> fillList = fill.getFillList();
                String currentFill = fillList.get(0);
                listOfFills.add(fill);
                fill.setFill(currentFill);
                if (fill.isImage()) {
                    isImage = true;
                    img = fill.getImage();
                } else {
                    color = fill.getColor();
                }
                if (isImage) {
                    MyBlockCreator blockCreator = new MyBlockCreator(width, height, hitPoints, img, listOfFills);
                    blockCreators.put(symbol , blockCreator);
                } else if (thereIsStroke) {
                    MyBlockCreator blockCreator = new MyBlockCreator(width, height, hitPoints, color,
                            stroke, listOfFills);
                    blockCreators.put(symbol , blockCreator);
                } else {
                    MyBlockCreator blockCreator = new MyBlockCreator(width, height, hitPoints, color, listOfFills);
                    blockCreators.put(symbol , blockCreator);
                }
            }
            blocksFromSymbolsFactory = new BlocksFromSymbolsFactory(spacerWidths, blockCreators, spacerSymbols);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading");
        } finally {
            reader.close();
        }
        return blocksFromSymbolsFactory;
    }
}