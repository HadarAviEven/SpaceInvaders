package game;
import java.util.ArrayList;
import java.util.List;

import geometry.Line;
import sprites.Collidable;
import sprites.CollisionInfo;

/**
 *
 * @author hadar
 *
 */
public class GameEnvironment {
    private List<Collidable> collidableList = new ArrayList<>();

    /**
     * add the given collidable to the environment.
     *
     * @param c a given collidable
     */
   public void addCollidable(Collidable c) {
       this.collidableList.add(c);
   }

   /**
    * remove the given collidable from the environment.
    *
    * @param c a given collidable
    */
   public void removeTheCollidable(Collidable c) {
       this.collidableList.remove(c);
   }

   /**
    *
    * @param trajectory the object's path
    * @return information about the closest collision or null
    */
   public CollisionInfo getClosestCollision(Line trajectory) {
       List<Collidable> collidables = new ArrayList<Collidable>(this.collidableList);
       return new CollisionInfo(collidables, trajectory);
   }
}