package ali.vexter.tools;

import java.awt.Rectangle;

public class Transform extends Rectangle {
   private static final long serialVersionUID = 1L;
   public double rotation = 0.0D;

   public Transform(int x, int y, int width, int height) {
      super(x, y, width, height);
   }

   public boolean inside(Rectangle rectangle) {
      return rectangle.x >= this.x && rectangle.x + rectangle.width <= this.x + this.width && rectangle.y >= this.y && rectangle.y + rectangle.height <= this.y + this.height;
   }

   public Transform(Rectangle rectangle) {
      super(rectangle);
   }

   public Transform(Transform transform) {
      super(transform);
   }
}
