package ali.vexter.tools;

import ali.vexter.enumerations.Direction;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utils {
   public static double angle(Point centerPt, Point targetPt) {
      double theta = Math.atan2((double)(targetPt.y - centerPt.y), (double)(targetPt.x - centerPt.x));
      ++theta;
      double angle = Math.toDegrees(theta);
      if (angle < 0.0D) {
         angle += 360.0D;
      }

      return angle;
   }

   public static BufferedImage[] reverseDuplicateImages(BufferedImage[] array) {
      BufferedImage[] result = new BufferedImage[array.length * 2];

      for(int i = 0; i < array.length; ++i) {
         result[i] = array[i];
      }

      ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();

      for(int i = 0; i < array.length; ++i) {
         list.add(array[i]);
      }

      Collections.reverse(list);
      BufferedImage[] reversed = new BufferedImage[list.size()];

      int i;
      for(i = 0; i < list.size(); ++i) {
         reversed[i] = (BufferedImage)list.get(i);
      }

      for(i = 0; i < reversed.length; ++i) {
         result[i + array.length] = reversed[i];
      }

      list.clear();
      return result;
   }

   public static Direction getOppositeDirection(Direction direction) {
      if (direction == Direction.UP) {
         return Direction.DOWN;
      } else if (direction == Direction.DOWN) {
         return Direction.UP;
      } else if (direction == Direction.LEFT) {
         return Direction.RIGHT;
      } else {
         return direction == Direction.RIGHT ? Direction.LEFT : null;
      }
   }

   public static Direction getRandomDirection(Random random) {
      int randomIndex = random.nextInt(Direction.values().length);
      return Direction.values()[randomIndex];
   }

   public static Direction getRandomDirectionExclude(Random random, Direction... excludes) {
      List<Direction> directions = new ArrayList<Direction>();
      directions.add(Direction.UP);
      directions.add(Direction.DOWN);
      directions.add(Direction.LEFT);
      directions.add(Direction.RIGHT);

      for(int i = 0; i < excludes.length; ++i) {
         directions.remove(excludes[i]);
      }

      Direction randomDirection = (Direction)directions.get(random.nextInt(directions.size()));
      return randomDirection;
   }

   public static Direction getRandomDirectionSpecified(Random random, Direction[] directions) {
      int randomIndex = random.nextInt(directions.length);
      return directions[randomIndex];
   }
}
