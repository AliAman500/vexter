package ali.vexter.projectiles;

import ali.vexter.ECS.Entity;
import java.awt.Color;
import java.awt.Graphics;
import ali.vexter.tools.Animation;
import ali.vexter.tools.Handler;
import ali.vexter.tools.Transform;

public class Bullet extends Projectile {
   private Animation animation = null;

   public Bullet(Entity owner, int x, int y, int velX, int velY, Handler handler) {
      super(owner, x, y, velX, velY, handler);
   }

   public void update() {
      Transform var10000 = this.transform;
      var10000.x += this.velX;
      var10000 = this.transform;
      var10000.y += this.velY;
      if (this.animation != null) {
         this.animation.update();
      }

   }

   public void render(Graphics g) {
      if (this.animation == null) {
         g.setColor(Color.YELLOW);
         g.fillOval(this.transform.x, this.transform.y, this.transform.width, this.transform.height);
      } else {
         this.animation.render(g, this.transform.x, this.transform.y, this.transform.width, this.transform.height);
      }

   }

   public Animation getAnimation() {
      return this.animation;
   }

   public void setAnimation(Animation animation) {
      this.animation = animation;
   }
}
