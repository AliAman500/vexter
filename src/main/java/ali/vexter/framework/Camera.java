package ali.vexter.framework;

import ali.vexter.basicComponents.TransformComponent;
import java.awt.Rectangle;

public class Camera {
   private float x;
   private float y;
   private GameManager gameManager;
   private TransformComponent playerTransform;

   public Camera(float x, float y, GameManager gameManager) {
      this.x = x;
      this.y = y;
      this.gameManager = gameManager;
   }

   public void update() {
      this.playerTransform = (TransformComponent)this.gameManager.player.getComponent("TransformComponent");
      this.x = (float)((double)this.x + (this.playerTransform.getTransform().getX() - (double)this.x - (double)(625 - this.playerTransform.getTransform().width)) * 0.11999999731779099D);
      this.y = (float)((double)this.y + (this.playerTransform.getTransform().getY() - (double)this.y - (double)(350 - this.playerTransform.getTransform().height)) * 0.11999999731779099D);
   }

   public Rectangle getBounds() {
      return new Rectangle((int)this.x, (int)this.y, 1250, 700);
   }

   public float getX() {
      return this.x;
   }

   public void setX(float x) {
      this.x = x;
   }

   public float getY() {
      return this.y;
   }

   public void setY(float y) {
      this.y = y;
   }
}
