package levels;

import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hadar
 *
 */
public class Help {

    /**
     *
     * @param sdefList the given sdefList
     * @param spacerSymbols the given spacerSymbols
     * @param spacerWidths the given spacerWidths
     */
    public void helper(List<Map<String, String>> sdefList, Map<String, String> spacerSymbols,
            Map<String, Integer> spacerWidths) {
        for (int t = 0; t < sdefList.size(); t++) {
            int spacersNumOfParameters = 0;
            for (Map.Entry<String, String> entry : sdefList.get(t).entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("symbol")) {
                    spacersNumOfParameters++;
                    if (value.length() == 1) {
                        spacerSymbols.put(key, value);
                    } else {
                        System.out.println("Ilegal parameter");
                        System.exit(1);
                    }
                } else if (key.equals("width")) {
                    spacersNumOfParameters++;
                    if (Integer.valueOf(value) > 0) {
                        spacerWidths.put(key, Integer.parseInt(value));
                    } else {
                        System.out.println("Ilegal parameter");
                        System.exit(1);
                    }
                }
            }
            if (spacersNumOfParameters < 2) {
                System.out.println("Not enough parameters");
                System.exit(1);
            }
        }
    }

    /**
     *
     * @param symbol the given symbol
     * @param defaultSymbol the given defaultSymbol
     * @return the symbol
     */
    public String helper2(String symbol, String defaultSymbol) {
        if ((symbol.length() == 0)) {
            if (defaultSymbol.length() != 0) {
                symbol = defaultSymbol;
            } else {
                this.notEnoughParameters();
            }
        }
        return symbol;
    }

    /**
    *
    * @param width the given width
    * @param defaultWidth the given defaultWidth
    * @return the width
    */
   public int helper3(int width, int defaultWidth) {
       if ((width == 0)) {
           if (defaultWidth != 0) {
               width = defaultWidth;
           } else {
               this.notEnoughParameters();
           }
       }
       return width;
   }

   /**
   *
   * @param height the given height
   * @param defaultHeight the given defaultHeight
   * @return the height
   */
  public int helper4(int height, int defaultHeight) {
      if ((height == 0)) {
          if (defaultHeight != 0) {
              height = defaultHeight;
          } else {
              this.notEnoughParameters();
          }
      }
      return height;
  }

  /**
  *
  * @param hitPoints the given hitPoints
  * @param defaultHitPoints the given defaultHitPoints
  * @return the hitPoints
  */
 public String helper5(String hitPoints, String defaultHitPoints) {
     if ((hitPoints.length() == 0)) {
         if (defaultHitPoints.length() != 0) {
             hitPoints = defaultHitPoints;
         } else {
             this.notEnoughParameters();
         }
     }
     return hitPoints;
 }

 /**
 *
 * @param stroke the given stroke
 * @param defaultStroke the given defaultStroke
 * @return the stroke
 */
 public Color helper6(Color stroke, Color defaultStroke) {
     if ((stroke == null)) {
         if (defaultStroke != null) {
             stroke = defaultStroke;
         }
     }
     return stroke;
 }

/**
 *
 * @param value the given value
 * @param symbol the given symbol
 * @return symbol
 */
 public String helper7(String value, String symbol) {
     if (value.length() == 1) {
         symbol = value;
     } else {
         this.ilegalParameter();
     }
     return symbol;
 }

 /**
 *
 * @param value the given value
 * @param width the given width
 * @return width
 */
 public int helper8(String value, int width) {
     if (Integer.valueOf(value) > 0) {
         width = Integer.valueOf(value);
     } else {
         this.ilegalParameter();
     }
     return width;
 }

 /**
 *
 * @param value the given value
 * @param height the given height
 * @return height
 */
 public int helper9(String value, int height) {
     if (Integer.valueOf(value) > 0) {
         height = Integer.valueOf(value);
     } else {
         this.ilegalParameter();
     }
     return height;
 }

 /**
 *
 * @param value the given value
 * @param hitPoints the given hitPoints
 * @return hitPoints
 */
 public String helper10(String value, String hitPoints) {
     if (Integer.valueOf(value) > 0) {
         hitPoints = value;
     } else {
         this.ilegalParameter();
     }
     return hitPoints;
 }

    /**
     * something is wrong.
     */
    public void ilegalParameter() {
        System.out.println("Ilegal parameter");
        System.exit(1);
    }

    /**
     * something is wrong.
     */
    public void notEnoughParameters() {
        System.out.println("Not enough parameters");
        System.exit(1);
    }
}
