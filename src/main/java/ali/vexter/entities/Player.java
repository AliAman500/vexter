package ali.vexter.entities;

import ali.vexter.ECS.Entity;
import ali.vexter.basicComponents.CollisionComponent;
import ali.vexter.basicComponents.HealthComponent;
import ali.vexter.basicComponents.TransformComponent;
import ali.vexter.playerComponents.PlayerMovementComponent;
import ali.vexter.playerComponents.PlayerRenderComponent;
import ali.vexter.tools.Handler;
import ali.vexter.tools.Transform;

public class Player extends Entity {
   private Handler handler;

   public Player(int x, int y, Handler handler) {
      super("Player");
      this.handler = handler;
      this.addComponent(new TransformComponent(this, new Transform(x, y, 34, 28)));
      this.addComponent(new HealthComponent(this, 100.0F));
      this.addComponent(new PlayerMovementComponent(this));
      this.addComponent(new PlayerRenderComponent(this));
      this.addComponent(new CollisionComponent(this, this.handler));
   }
}
