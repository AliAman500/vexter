package ali.vexter.framework;

import ali.vexter.enumerations.Direction;
import java.awt.Rectangle;

public class ExitMap {
   private Direction direction;
   private Rectangle rectangle;

   public ExitMap(Direction direction, Rectangle rectangle) {
      this.direction = direction;
      this.rectangle = rectangle;
   }

   public ExitMap(ExitMap exitMap) {
      this.direction = exitMap.direction;
      this.rectangle = new Rectangle(exitMap.rectangle);
   }

   public Direction getDirection() {
      return this.direction;
   }

   public void setDirection(Direction direction) {
      this.direction = direction;
   }

   public Rectangle getRectangle() {
      return this.rectangle;
   }

   public void setRectangle(Rectangle rectangle) {
      this.rectangle = rectangle;
   }
}
