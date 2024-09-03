package ali.vexter.playerComponents;

import ali.vexter.ECS.Component;
import ali.vexter.ECS.Entity;
import ali.vexter.basicComponents.TransformComponent;
import ali.vexter.enumerations.Direction;
import java.awt.Graphics;
import ali.vexter.tools.Animation;
import ali.vexter.tools.Images;

public class PlayerRenderComponent extends Component {
   private TransformComponent transformComponent;
   private PlayerMovementComponent movementComponent;
   private Animation idleAnimation;
   private Animation runAnimation;
   private Animation runDownAnimation;
   private Animation runUpAnimation;
   private int offsetX = -14;
   private int offsetY = -24;

   public PlayerRenderComponent(Entity owner) {
      super(owner);
      this.transformComponent = (TransformComponent)owner.getComponent("TransformComponent");
      this.movementComponent = (PlayerMovementComponent)owner.getComponent("PlayerMovementComponent");
      this.idleAnimation = new Animation(75, Images.playerIdleImages);
      this.runAnimation = new Animation(8, 2, Images.playerRunImages);
      this.runDownAnimation = new Animation(15, Images.playerRunDownImages);
      this.runUpAnimation = new Animation(15, Images.playerRunUpImages);
   }

   public void update() {
      this.idleAnimation.update();
      this.runAnimation.update();
      this.runDownAnimation.update();
      this.runUpAnimation.update();
   }

   public void render(Graphics g) {
      if (this.movementComponent.getVelX() == 0 && this.movementComponent.getVelY() == 0.0F && this.movementComponent.getDirection() == Direction.LEFT) {
         this.idleAnimation.render(g, this.transformComponent.getTransform().x + this.offsetX, this.transformComponent.getTransform().y + this.offsetY);
      } else if (this.movementComponent.getVelX() == 0 && this.movementComponent.getVelY() == 0.0F && this.movementComponent.getDirection() == Direction.RIGHT) {
         this.idleAnimation.render(g, this.transformComponent.getTransform().x + this.offsetX + 64, this.transformComponent.getTransform().y + this.offsetY, -64, 64);
      } else if (this.movementComponent.getVelX() < 0) {
         this.runAnimation.render(g, this.transformComponent.getTransform().x + this.offsetX, this.transformComponent.getTransform().y + this.offsetY);
      } else if (this.movementComponent.getVelX() > 0) {
         this.runAnimation.render(g, this.transformComponent.getTransform().x + this.offsetX + 64, this.transformComponent.getTransform().y + this.offsetY, -64, 64);
      } else if (this.movementComponent.getVelY() > 0.0F) {
         this.runDownAnimation.render(g, this.transformComponent.getTransform().x + this.offsetX, this.transformComponent.getTransform().y + this.offsetY);
      } else if (this.movementComponent.getVelY() < 0.0F) {
         this.runUpAnimation.render(g, this.transformComponent.getTransform().x + this.offsetX, this.transformComponent.getTransform().y + this.offsetY);
      } else {
         this.idleAnimation.render(g, this.transformComponent.getTransform().x + this.offsetX, this.transformComponent.getTransform().y + this.offsetY);
      }

   }
}
