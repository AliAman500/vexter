package ali.vexter.basicComponents;

import ali.vexter.ECS.Component;
import ali.vexter.ECS.Entity;
import ali.vexter.entities.Block;
import ali.vexter.framework.Room;
import java.awt.Rectangle;
import java.util.LinkedList;
import ali.vexter.tools.Handler;

public class CollisionComponent extends Component {
   private Rectangle[] collisionBounds = new Rectangle[4];
   private TransformComponent transform;
   private Handler handler;
   private int offsetFactor = 5;

   public CollisionComponent(Entity owner, Handler handler) {
      super(owner);
      this.handler = handler;
      this.transform = (TransformComponent)owner.getComponent("TransformComponent");

      for(int i = 0; i < this.collisionBounds.length; ++i) {
         this.collisionBounds[i] = new Rectangle();
      }

   }

   public void update() {
      LinkedList<Entity> blocks = this.handler.getEntities("Block");
      int i;
      if (blocks != null) {
         for(i = 0; i < blocks.size(); ++i) {
            Entity entity = (Entity)blocks.get(i);
            Block block = (Block)entity;
            TransformComponent blockTransComp = (TransformComponent)block.getComponent("TransformComponent");
            this.apply(blockTransComp.getTransform());
         }
      }

      for(i = 0; i < this.handler.getRooms().size(); ++i) {
         Room room = (Room)this.handler.getRooms().get(i);

         int j;
         for(j = 0; j < room.getBoundsUp().length; ++j) {
            this.apply(room.getBoundsUp()[j]);
         }

         for(j = 0; j < room.getBoundsDown().length; ++j) {
            this.apply(room.getBoundsDown()[j]);
         }

         for(j = 0; j < room.getBoundsLeft().length; ++j) {
            this.apply(room.getBoundsLeft()[j]);
         }

         for(j = 0; j < room.getBoundsRight().length; ++j) {
            this.apply(room.getBoundsRight()[j]);
         }
      }

   }

   private void apply(Rectangle block) {
      if (this.getBoundsUp().intersects(block)) {
         this.transform.getTransform().y = block.y + block.height;
      }

      if (this.getBoundsDown().intersects(block)) {
         this.transform.getTransform().y = block.y - this.transform.getTransform().height;
      }

      if (this.getBoundsLeft().intersects(block)) {
         this.transform.getTransform().x = block.x + block.width;
      }

      if (this.getBoundsRight().intersects(block)) {
         this.transform.getTransform().x = block.x - this.transform.getTransform().width;
      }

   }

   public Rectangle getBoundsUp() {
      this.collisionBounds[0].x = this.transform.getTransform().x + this.offsetFactor;
      this.collisionBounds[0].y = this.transform.getTransform().y;
      this.collisionBounds[0].width = this.transform.getTransform().width - this.offsetFactor * 2;
      this.collisionBounds[0].height = this.transform.getTransform().height / 2;
      return this.collisionBounds[0];
   }

   public Rectangle getBoundsDown() {
      this.collisionBounds[1].x = this.transform.getTransform().x + this.offsetFactor;
      this.collisionBounds[1].y = this.transform.getTransform().y + this.transform.getTransform().height / 2;
      this.collisionBounds[1].width = this.transform.getTransform().width - this.offsetFactor * 2;
      this.collisionBounds[1].height = this.transform.getTransform().height / 2;
      return this.collisionBounds[1];
   }

   public Rectangle getBoundsLeft() {
      this.collisionBounds[2].x = this.transform.getTransform().x;
      this.collisionBounds[2].y = this.transform.getTransform().y + this.offsetFactor;
      this.collisionBounds[2].width = this.offsetFactor;
      this.collisionBounds[2].height = this.transform.getTransform().height - this.offsetFactor * 2;
      return this.collisionBounds[2];
   }

   public Rectangle getBoundsRight() {
      this.collisionBounds[3].x = this.transform.getTransform().x + (this.transform.getTransform().width - this.offsetFactor);
      this.collisionBounds[3].y = this.transform.getTransform().y + this.offsetFactor;
      this.collisionBounds[3].width = this.offsetFactor;
      this.collisionBounds[3].height = this.transform.getTransform().height - this.offsetFactor * 2;
      return this.collisionBounds[3];
   }
}
