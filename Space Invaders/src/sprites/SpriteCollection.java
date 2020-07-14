package sprites;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 *
 * @author hadar
 *
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<>();

    /**
     * add the given sprite to the environment.
     *
     * @param s a given sprite
     */
   public void addSprite(Sprite s) {
       this.spriteList.add(s);
   }

   /**
    * remove the given sprite from the environment.
    *
    * @param s a given sprite
    */
  public void removeTheSprite(Sprite s) {
      this.spriteList.remove(s);
  }

   /**
    * call timePassed() on all sprites.
    * @param dt number of seconds.
    */
   public void notifyAllTimePassed(double dt) {
       List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
       for (Sprite sp : sprites) {
           sp.timePassed(dt);
        }
   }

   /**
    *
    * @param d the given drawSurface
    */
   public void drawAllOn(DrawSurface d) {
       for (int i = 0; i < this.spriteList.size(); i++) {
           this.spriteList.get(i).drawOn(d);
       }
   }
}