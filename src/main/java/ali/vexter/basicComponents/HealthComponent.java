package ali.vexter.basicComponents;

import ali.vexter.ECS.Component;
import ali.vexter.ECS.Entity;

public class HealthComponent extends Component {
   private float health;
   private float maxHealth;

   public HealthComponent(Entity owner, float health, float maxHealth) {
      super(owner);
      this.health = health;
      this.maxHealth = maxHealth;
   }

   public HealthComponent(Entity owner, float health) {
      this(owner, health, 100.0F);
   }

   public void update() {
      if (this.health <= 0.0F) {
         this.health = 0.0F;
      }

      if (this.health >= this.maxHealth) {
         this.health = this.maxHealth;
      }

   }

   public float getFactor(int bounds) {
      return this.health / this.maxHealth * (float)bounds;
   }

   public float getHealth() {
      return this.health;
   }

   public void setHealth(float health) {
      this.health = health;
   }
}
