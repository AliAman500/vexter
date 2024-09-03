package ali.vexter.projectiles;

import ali.vexter.ECS.Entity;
import ali.vexter.framework.Room;
import java.awt.Graphics;
import ali.vexter.tools.Handler;
import ali.vexter.tools.Transform;

public abstract class Projectile {
   protected Entity owner;
   protected Transform transform;
   protected Handler handler;
   protected int velX;
   protected int velY;
   protected int lifeLength = 100;

   public Projectile(Entity owner, int x, int y, int velX, int velY, Handler handler) {
      this.owner = owner;
      this.handler = handler;
      this.transform = new Transform(x, y, 12, 12);
      this.velX = velX;
      this.velY = velY;
   }

   public void updateLife() {
      --this.lifeLength;
      if (this.lifeLength <= 0) {
         this.handler.removeProjectile(this);
      }

      for(int i = 0; i < this.handler.getRooms().size(); ++i) {
         Room room = (Room)this.handler.getRooms().get(i);

         int j;
         for(j = 0; j < room.getBoundsUp().length; ++j) {
            if (this.transform.intersects(room.getBoundsUp()[j])) {
               this.handler.removeProjectile(this);
            }
         }

         for(j = 0; j < room.getBoundsDown().length; ++j) {
            if (this.transform.intersects(room.getBoundsDown()[j])) {
               this.handler.removeProjectile(this);
            }
         }

         for(j = 0; j < room.getBoundsLeft().length; ++j) {
            if (this.transform.intersects(room.getBoundsLeft()[j])) {
               this.handler.removeProjectile(this);
            }
         }

         for(j = 0; j < room.getBoundsRight().length; ++j) {
            if (this.transform.intersects(room.getBoundsRight()[j])) {
               this.handler.removeProjectile(this);
            }
         }
      }

   }

   public abstract void update();

   public abstract void render(Graphics var1);

   public Entity getOwner() {
      return this.owner;
   }

   public void setOwner(Entity owner) {
      this.owner = owner;
   }

   public Transform getTransform() {
      return this.transform;
   }

   public int getVelX() {
      return this.velX;
   }

   public void setVelX(int velX) {
      this.velX = velX;
   }

   public int getVelY() {
      return this.velY;
   }

   public void setVelY(int velY) {
      this.velY = velY;
   }

   public int getLifeLength() {
      return this.lifeLength;
   }

   public void setLifeLength(int lifeLength) {
      this.lifeLength = lifeLength;
   }
}
