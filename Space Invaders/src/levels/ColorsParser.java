package levels;

import java.awt.Color;

/**
 *
 * @author hadar
 *
 */
public class ColorsParser {

    /**
     *
     * @param s the given string
     * @return color;
     */
    public java.awt.Color colorFromString(String s) {
        Color color = null;
        if (s.startsWith("RGB")) {
            int firstColor = 0;
            int secondColor = 0;
            int thirdColor = 0;
            s = s.substring(4, s.length() - 1);
            String[] colorParts = s.split(",");
            if (colorParts.length != 3) {
                System.out.println("Ilegal parameter");
                System.exit(1);
            } else {
                if (Integer.valueOf(colorParts[0]) >= 0) {
                    firstColor = Integer.valueOf(colorParts[0]);
                } else {
                    System.out.println("Ilegal parameter");
                    System.exit(1);
                }
                if (Integer.valueOf(colorParts[1]) >= 0) {
                    secondColor = Integer.valueOf(colorParts[1]);
                } else {
                    System.out.println("Ilegal parameter");
                    System.exit(1);
                }
                if (Integer.valueOf(colorParts[2]) >= 0) {
                    thirdColor = Integer.valueOf(colorParts[2]);
                } else {
                    System.out.println("Ilegal parameter");
                    System.exit(1);
                }
            }
            color = new Color(firstColor, secondColor, thirdColor);
        } else {
            if (s.equals("black")) {
                color = Color.black;
            } else if (s.equals("blue")) {
                color = Color.blue;
            } else if (s.equals("cyan")) {
                color = Color.cyan;
            } else if (s.equals("gray")) {
                color = Color.gray;
            } else if (s.equals("lightGray")) {
                color = Color.lightGray;
            } else if (s.equals("green")) {
                color = Color.green;
            } else if (s.equals("orange")) {
                color = Color.orange;
            } else if (s.equals("pink")) {
                color = Color.pink;
            } else if (s.equals("red")) {
                color = Color.red;
            } else if (s.equals("white")) {
                color = Color.white;
            } else if (s.equals("yellow")) {
                color = Color.yellow;
            }
        }
        return color;
    }
 }
