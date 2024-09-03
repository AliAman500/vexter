package ali.vexter.blockComponents;

import ali.vexter.ECS.Component;
import ali.vexter.ECS.Entity;
import ali.vexter.basicComponents.TransformComponent;
import ali.vexter.entities.Block;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import ali.vexter.tools.Images;

public class BlockRenderComponent extends Component {
   private TransformComponent transformComponent;

   public BlockRenderComponent(Entity owner) {
      super(owner);
      this.transformComponent = (TransformComponent)owner.getComponent("TransformComponent");
   }

   public void update() {
   }

   public void render(Graphics g) {
      g.drawImage(Images.blockImages[((Block)this.owner).getId()], this.transformComponent.getTransform().x, this.transformComponent.getTransform().y, this.transformComponent.getTransform().width, this.transformComponent.getTransform().height, (ImageObserver)null);
   }
}
