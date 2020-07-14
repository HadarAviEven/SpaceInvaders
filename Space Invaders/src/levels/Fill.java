package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

/**
 *
 * @author hadar
 *
 */
public class Fill {
    private String value;
    private Image img;
    private boolean isImage;
    private String whatColor;
    private Color color;
    private ColorsParser colorsParser;
    private List<String> fillList;
    private String currentFill;

    /**
     * constructor.
     *
     */
    public Fill() {
        this.fillList = new ArrayList<String>();
        this.img = null;
        this.isImage = false;
        this.colorsParser = new ColorsParser();
    }

    /**
     *
     * @param line the given line
     */
    public void addToFill(String line) {
        this.fillList.add(line);
    }

    /**
     * set the list from the high fill to the low fill.
     */
    public void setFillList() {
        Collections.sort(fillList, new Comparator<String>() {
            @Override
            public int compare(String string1, String string2) {
                String[] parts1 = string1.split(":");
                String key1 = parts1[0];
                int num1 = 0;
                if (key1.contains("-")) {
                    num1 = Integer.valueOf(key1.substring(5, key1.length()));
                }
                String[] parts2 = string2.split(":");
                String key2 = parts2[0];
                int num2 = 0;
                if (key2.contains("-")) {
                    num2 = Integer.valueOf(key2.substring(5, key2.length()));
                }
                return (num2 - num1);
            }
        });
    }

    /**
     * set the fill.
     *
     * @param curFill the currentFill
     */
    public void setFill(String curFill) {
        if (!this.isImage()) {
            this.currentFill = curFill;
            String[] parts = this.currentFill.split(":");
            value = parts[1];
            if (value.startsWith("image")) {
                this.isImage = true;
                if (value.contains("/")) {
                    int index = value.indexOf(")");
                    String imgName = value.substring(6, index);
                    imgName = "resources/" + imgName;
                    try {
                        this.img = ImageIO.read(new File(imgName));
                    } catch (IOException e) {
                        System.out.println("Something went wrong while opening the photo");
                    }
                } else {
                    int startIndex = value.indexOf("(");
                    int endIndex = value.indexOf(")");
                    String imgName = value.substring(startIndex + 1, endIndex);
                    try {
                        img = ImageIO.read(new File(imgName));
                    } catch (IOException e) {
                        System.out.println("Something went wrong while opening the photo");
                    }
                }
            } else if (value.startsWith("color")) {
                this.whatColor = value.substring(6, value.length() - 1);
                this.color = colorsParser.colorFromString(whatColor);
            } else {
                System.out.println("Ilegal parameter");
                System.exit(1);
            }
        }
    }

    /**
     *
     * @return the image.
     */
    public Image getImage() {
        return this.img;
    }

    /**
     *
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return if is image.
     */
    public boolean isImage() {
        return this.isImage;
    }

    /**
     * @return the list.
     */
    public List<String> getFillList() {
        return this.fillList;
    }

    /**
     * @return the size of the list.
     */
    public int getSize() {
        return this.fillList.size();
    }

//  } else if (key.contains("fill")) {
//  String line = key + ":" + value;
//  fill.addToFill(line);//


//  if ((fill.length() == 0)) {
//  if (defaultfill.length() != 0) {
//      fill = defaultfill;
//  } else {
//      System.out.println("Not enough parameters");
//        System.exit(1);//
//    }
//}
}
