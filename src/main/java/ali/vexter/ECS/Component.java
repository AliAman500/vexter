package ali.vexter.ECS;

import java.awt.Graphics;

public abstract class Component {
   protected Entity owner;
   private String name;

   public Component(Entity owner) {
      this.owner = owner;
      this.name = this.getClass().getSimpleName();
   }

   public void update() {
   }

   public void render(Graphics g) {
   }

   public Entity getOwner() {
      return this.owner;
   }

   public void setOwner(Entity owner) {
      this.owner = owner;
   }

   public String getName() {
      return this.name;
   }
}
