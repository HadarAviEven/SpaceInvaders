package game;
import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import sprites.BlockAlien;

/**
 *
 * @author hadar
 *
 */
public class Aliens {
    private List<BlockAlien> listOfAliens;
    private double maxX = 800;
    private double minX = 0;
    private final int numberOfPixelsToGetDown = -20;
    private double maxHeight;
    private boolean getToShield = false;
    private Point startPoint;

    /**
     * constructor.
     *
     * @param listOfAliens the given list of aliens
     * @param maxHeight the given max height
     */
    public Aliens(List<BlockAlien> listOfAliens, double maxHeight) {
        this.listOfAliens = listOfAliens;
        this.maxHeight = maxHeight;
    }

    /**
     * constructor with start point.
     *
     * @param listOfAliens the given list of aliens
     * @param maxHeight the given max height
     * @param startPoint the given start point
     */
    public Aliens(List<BlockAlien> listOfAliens, double maxHeight, Point startPoint) {
        this.listOfAliens = listOfAliens;
        this.maxHeight = maxHeight;
        this.startPoint = startPoint;
        this.organizeList();
    }

    /**
     * organize the list.
     */
    public void organizeList() {
        double oldX = this.listOfAliens.get(0).getCollisionRectangle().getUpperLeft().getX();
        double oldY = this.listOfAliens.get(0).getCollisionRectangle().getUpperLeft().getY();
        double distanceX = oldX - this.startPoint.getX();
        double distanceY = oldY - this.startPoint.getY();
        for (int i = 0; i < this.listOfAliens.size(); i++) {
            this.listOfAliens.get(i).organize(distanceX, distanceY);
        }
    }

    /**
     * moves all the aliens.
     */
    public void moveAll() {
        if (listOfAliens.size() > 0) {
            if (hasHitRightSide()) {
                getDownPixels();
                changeVelocityToLeft();
            } else if (hasHitLeftSide()) {
                getDownPixels();
                changeVelocityToRight();
            }
        }

        for (BlockAlien alien : listOfAliens) {
            alien.move(maxHeight);
            if (alien.getToTheShield()) {
                this.getToShield = true;
                break;
            }
        }
    }

    /**
     *
     * @return if the alien get to the shield.
     */
    public boolean getToTheShield() {
        return this.getToShield;
    }

    /**
     *
     *
     */
    public void setGetToTheShield() {
        for (int i = 0; i < this.listOfAliens.size(); i++) {
            this.listOfAliens.get(i).setGetToTheShield();
        }
        this.getToShield = false;
    }

    /**
     * change velocity to left.
     */
    private void changeVelocityToLeft() {
        for (BlockAlien alien : listOfAliens) {
            alien.turnVelocityLeft();
        }
    }

    /**
     * change velocity to right.
     */
    private void changeVelocityToRight() {
        for (BlockAlien alien : listOfAliens) {
            alien.turnVelocityRight();
        }
    }

    /**
     *
     * @return if the alien has hit the right side.
     */
    private boolean hasHitRightSide() {
        return getTopRightX(getMostRightAlien()) >= maxX;
    }

    /**
     *
     * @return if the alien has hit the left side.
     */
    private boolean hasHitLeftSide() {
        return getTopLeftX(getMostLeftAlien()) <= minX;
    }

    /**
     * moves the aliens down.
     */
    private void getDownPixels() {
        for (BlockAlien alien : listOfAliens) {
            alien.getDownPixels(numberOfPixelsToGetDown);
        }
    }

    /**
     *
     * @return the most right alien.
     */
    private BlockAlien getMostRightAlien() {
        BlockAlien mostRightAlien = listOfAliens.get(0);
        for (int i = 1; i < listOfAliens.size(); i++) {
            if (getTopRightX(listOfAliens.get(i)) > getTopRightX(mostRightAlien)) {
                mostRightAlien = listOfAliens.get(i);
            }
        }
        return mostRightAlien;
    }

    /**
     *
     * @return the most left alien.
     */
    private BlockAlien getMostLeftAlien() {
        BlockAlien mostLeftAlien = listOfAliens.get(0);
        for (int i = 1; i < listOfAliens.size(); i++) {
            if (getTopLeftX(listOfAliens.get(i)) < getTopLeftX(mostLeftAlien)) {
                mostLeftAlien = listOfAliens.get(i);
            }
        }
        return mostLeftAlien;
    }

    /**
     *
     * @param alien the given alien
     * @return the x right value of the alien
     */
    private double getTopRightX(BlockAlien alien) {
        return alien.getCollisionRectangle().getUpperRight().getX();
    }

    /**
     *
     * @param alien the given alien
     * @return the x left value of the alien
     */
    private double getTopLeftX(BlockAlien alien) {
        return alien.getCollisionRectangle().getUpperLeft().getX();
    }

    /**
    *
    * @return list of the bottom aliens.
    */
   public List<BlockAlien> getBottomAliens() {
       List<List<BlockAlien>> listOfColumns = new ArrayList<List<BlockAlien>>();
       List<BlockAlien> column = new ArrayList<BlockAlien>();
       int counter = 0;
       List<BlockAlien> bottomAliens = new ArrayList<BlockAlien>();
       int j = -1;
       while (counter < this.listOfAliens.size() && (j < this.listOfAliens.size() - 1)) {
           j++;
           column = new ArrayList<BlockAlien>();
           double x = listOfAliens.get(j).getCollisionRectangle().getUpperLeft().getX();
           column.add(listOfAliens.get(j));
           for (int i = j + 1; i < listOfAliens.size(); i++) {
               if (listOfAliens.get(i).getCollisionRectangle().getUpperLeft().getX() == x) {
                   counter++;
                   column.add(listOfAliens.get(i));
               }
           }
           listOfColumns.add(column);
       }
       for (int i = 0; i < listOfColumns.size(); i++) {
           BlockAlien bottomAlien = listOfColumns.get(i).get(0);
           double oneBottomAlien = listOfColumns.get(i).get(0).getCollisionRectangle().getBottomLeft().getY();
           for (int t = 1; t < listOfColumns.get(i).size(); t++) {
               if (listOfColumns.get(i).get(t).getCollisionRectangle().getBottomLeft().getY() > oneBottomAlien) {
                   bottomAlien = listOfColumns.get(i).get(t);
                   oneBottomAlien = listOfColumns.get(i).get(t).getCollisionRectangle().getBottomLeft().getY();
               }
           }
           bottomAliens.add(bottomAlien);
       }
       return bottomAliens;
   }
}
