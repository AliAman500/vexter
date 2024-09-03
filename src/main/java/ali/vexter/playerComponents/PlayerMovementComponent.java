package ali.vexter.playerComponents;

import ali.vexter.ECS.Component;
import ali.vexter.ECS.Entity;
import ali.vexter.basicComponents.TransformComponent;
import ali.vexter.enumerations.Direction;
import ali.vexter.tools.Transform;

public class PlayerMovementComponent extends Component {
   private int velX = 0;
   private int velY = 0;
   public boolean up = false;
   public boolean down = false;
   public boolean left = false;
   public boolean right = false;
   private TransformComponent transformComponent;
   private Direction direction;

   public PlayerMovementComponent(Entity owner) {
      super(owner);
      this.transformComponent = (TransformComponent)owner.getComponent("TransformComponent");
   }

   public void update() {
      Transform var10000 = this.transformComponent.getTransform();
      var10000.x += this.velX;
      var10000 = this.transformComponent.getTransform();
      var10000.y += this.velY;
      if (this.up) {
         this.velY = -4;
      } else if (!this.down) {
         this.velY = 0;
      }

      if (this.down) {
         this.velY = 4;
      } else if (!this.up) {
         this.velY = 0;
      }

      if (this.left) {
         this.velX = -4;
      } else if (!this.right) {
         this.velX = 0;
      }

      if (this.right) {
         this.velX = 4;
      } else if (!this.left) {
         this.velX = 0;
      }

      if (this.velY < 0) {
         this.direction = Direction.UP;
      }

      if (this.velY > 0) {
         this.direction = Direction.DOWN;
      }

      if (this.velX < 0) {
         this.direction = Direction.LEFT;
      }

      if (this.velX > 0) {
         this.direction = Direction.RIGHT;
      }

   }

   public int getVelX() {
      return this.velX;
   }

   public void setVelX(int velX) {
      this.velX = velX;
   }

   public float getVelY() {
      return (float)this.velY;
   }

   public void setVelY(int velY) {
      this.velY = velY;
   }

   public Direction getDirection() {
      return this.direction;
   }
}
