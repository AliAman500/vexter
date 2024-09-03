package ali.vexter.entities;

import ali.vexter.ECS.Entity;
import ali.vexter.basicComponents.TransformComponent;
import ali.vexter.blockComponents.BlockRenderComponent;
import ali.vexter.tools.Images;
import ali.vexter.tools.Transform;

public class Block extends Entity {
   private int id;

   public Block(int x, int y, int width, int height, int id) {
      super("Block");
      this.id = id;
      int normalWidth = width;
      this.addComponent(new TransformComponent(this, new Transform(x, y, width, height)));
      this.addComponent(new BlockRenderComponent(this));
      width = Images.blockImages[id].getWidth() / 64 * 46;
      height = Images.blockImages[id].getWidth() / 64 * 46;
      if (normalWidth >= 46) {
         ((TransformComponent)this.getComponent("TransformComponent")).getTransform().width = width;
         ((TransformComponent)this.getComponent("TransformComponent")).getTransform().height = height;
      }

   }

   public Block(Block block) {
      this(((TransformComponent)block.getComponent("TransformComponent")).getTransform().x, ((TransformComponent)block.getComponent("TransformComponent")).getTransform().y, ((TransformComponent)block.getComponent("TransformComponent")).getTransform().width, ((TransformComponent)block.getComponent("TransformComponent")).getTransform().height, block.id);
      TransformComponent transform = (TransformComponent)block.getComponent("TransformComponent");
      TransformComponent localTransform = (TransformComponent)this.getComponent("TransformComponent");
      this.id = block.id;
      localTransform.getTransform().x = transform.getTransform().x;
      localTransform.getTransform().y = transform.getTransform().y;
      localTransform.getTransform().width = transform.getTransform().width;
      localTransform.getTransform().height = transform.getTransform().height;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
