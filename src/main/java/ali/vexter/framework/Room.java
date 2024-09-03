package ali.vexter.framework;

import ali.vexter.ECS.Entity;
import ali.vexter.basicComponents.TransformComponent;
import ali.vexter.blockComponents.BlockRenderComponent;
import ali.vexter.entities.Block;
import ali.vexter.enumerations.Direction;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import ali.vexter.tools.Handler;
import ali.vexter.tools.Images;
import ali.vexter.tools.RoomLoader;
import ali.vexter.tools.Transform;

public class Room {
   private int x;
   private int y;
   private int widthInTiles;
   private int heightInTiles;
   private int[] tileIds;
   private int[] borderIds;
   private boolean hasUpExit = false;
   private boolean hasDownExit = false;
   private boolean hasLeftExit = false;
   private boolean hasRightExit = false;
   private Room roomType;
   private Random random;
   private BufferedImage image;
   private Block[][] surfaceTiles;
   private Block[] tiles;
   private Block[] defaultTiles;
   private ArrayList<Block> blocks = new ArrayList<Block>();
   private Handler handler;
   private ExitMap[] exitMaps = new ExitMap[4];
   private Rectangle[] boundsUp = new Rectangle[2];
   private Rectangle[] boundsDown = new Rectangle[2];
   private Rectangle[] boundsLeft = new Rectangle[2];
   private Rectangle[] boundsRight = new Rectangle[2];
   private Graphics g;
   public static Room emptyRoom;
   private static int[] $SWITCH_TABLE$enumerations$Direction;

   public static void init(RoomLoader roomLoader) {
      emptyRoom = roomLoader.loadRoom("/rooms/EmptyRoom.rmf");
   }

   public Room(int x, int y, int widthInTiles, int heightInTiles, int[] tileIds, int[] borderIds, Block[] tiles, ArrayList<Block> blocks, ExitMap[] exitMaps, Random random, Handler handler) {
      this.x = x;
      this.y = y;
      this.widthInTiles = widthInTiles;
      this.heightInTiles = heightInTiles;
      this.tileIds = tileIds;
      this.borderIds = borderIds;
      this.tiles = tiles;
      this.blocks = blocks;
      this.exitMaps = exitMaps;
      tileIds = new int[tileIds.length];

      int i;
      for(i = 0; i < tileIds.length; ++i) {
         tileIds[i] = this.tileIds[i];
      }

      borderIds = new int[borderIds.length];

      for(i = 0; i < borderIds.length; ++i) {
         borderIds[i] = this.borderIds[i];
      }

      tiles = new Block[tiles.length];
      this.defaultTiles = new Block[tiles.length];

      Block block;
      for(i = 0; i < tiles.length; ++i) {
         block = this.tiles[i];
         block = new Block(block);
         tiles[i] = block;
         this.defaultTiles[i] = block;
      }

      blocks = new ArrayList<Block>();

      for(i = 0; i < this.blocks.size(); ++i) {
         block = (Block)this.blocks.get(i);
         block = new Block(block);
         blocks.add(block);
      }

      exitMaps = new ExitMap[exitMaps.length];

      for(i = 0; i < exitMaps.length; ++i) {
         ExitMap exitMap = new ExitMap(this.exitMaps[i]);
         exitMaps[i] = exitMap;
      }

      this.tileIds = tileIds;
      this.borderIds = borderIds;
      this.tiles = tiles;
      this.blocks = blocks;
      this.exitMaps = exitMaps;
      this.random = random;
      this.handler = handler;
      this.surfaceTiles = new Block[widthInTiles][heightInTiles];

      int j;
      for(i = 0; i < widthInTiles; ++i) {
         for(j = 0; j < heightInTiles; ++j) {
            block = new Block(i * 46 / 2, j * 46 / 2, 23, 23, tileIds[0]);
            int id = random.nextInt(tileIds.length);

            for(int k = 0; k < tileIds.length; ++k) {
               if (id == k) {
                  block.setId(tileIds[id]);
               }
            }

            this.surfaceTiles[i][j] = block;
         }
      }

      this.image = new BufferedImage(widthInTiles * 46 / 2, heightInTiles * 46 / 2, 2);
      this.g = this.image.getGraphics();

      for(i = 0; i < widthInTiles; ++i) {
         for(j = 0; j < heightInTiles; ++j) {
            BlockRenderComponent renderComponent = (BlockRenderComponent)this.surfaceTiles[i][j].getComponent("BlockRenderComponent");
            renderComponent.render(this.g);
         }
      }

      for(i = 0; i < tiles.length; ++i) {
         BlockRenderComponent renderComponent = (BlockRenderComponent)tiles[i].getComponent("BlockRenderComponent");
         renderComponent.render(this.g);
      }

      this.boundsUp[0] = new Rectangle();
      this.boundsUp[0].x = x;
      this.boundsUp[0].y = y;
      this.boundsUp[0].width = this.getBounds().width / 2;
      this.boundsUp[0].height = 46;
      this.boundsUp[1] = new Rectangle();
      this.boundsUp[1].x = x + this.getBounds().width / 2;
      this.boundsUp[1].y = y;
      this.boundsUp[1].width = this.getBounds().width / 2;
      this.boundsUp[1].height = 46;
      this.boundsDown[0] = new Rectangle();
      this.boundsDown[0].x = x;
      this.boundsDown[0].y = y + this.getBounds().height - 46;
      this.boundsDown[0].width = this.getBounds().width / 2;
      this.boundsDown[0].height = 46;
      this.boundsDown[1] = new Rectangle();
      this.boundsDown[1].x = x + this.getBounds().width / 2;
      this.boundsDown[1].y = y + this.getBounds().height - 46;
      this.boundsDown[1].width = this.getBounds().width / 2;
      this.boundsDown[1].height = 46;
      this.boundsLeft[0] = new Rectangle();
      this.boundsLeft[0].x = x;
      this.boundsLeft[0].y = y;
      this.boundsLeft[0].width = 46;
      this.boundsLeft[0].height = this.getBounds().height / 2;
      this.boundsLeft[1] = new Rectangle();
      this.boundsLeft[1].x = x;
      this.boundsLeft[1].y = y + this.getBounds().height / 2;
      this.boundsLeft[1].width = 46;
      this.boundsLeft[1].height = this.getBounds().height / 2;
      this.boundsRight[0] = new Rectangle();
      this.boundsRight[0].x = x + this.getBounds().width - 46;
      this.boundsRight[0].y = y;
      this.boundsRight[0].width = 46;
      this.boundsRight[0].height = this.getBounds().height / 2;
      this.boundsRight[1] = new Rectangle();
      this.boundsRight[1].x = x + this.getBounds().width - 46;
      this.boundsRight[1].y = y + this.getBounds().height / 2;
      this.boundsRight[1].width = 46;
      this.boundsRight[1].height = this.getBounds().height / 2;
   }

   public void addEntity(Entity entity) {
      TransformComponent transform = (TransformComponent)entity.getComponent("TransformComponent");
      Transform var10000 = transform.getTransform();
      var10000.x += this.x;
      var10000 = transform.getTransform();
      var10000.y += this.y;
      this.handler.addEntity(entity);
   }

   public void centerEntity(Entity entity) {
      TransformComponent transform = (TransformComponent)entity.getComponent("TransformComponent");
      transform.getTransform().x = this.getBounds().x + this.getBounds().width / 2 - transform.getTransform().width / 2;
      transform.getTransform().y = this.getBounds().y + this.getBounds().height / 2 - transform.getTransform().height / 2;
   }

   public Room createInstance(int x, int y, Random random) {
      this.roomType = this;
      Room room = new Room(x, y, this.widthInTiles, this.heightInTiles, this.tileIds, this.borderIds, this.tiles, this.blocks, this.exitMaps, random, this.handler);

      for(int i = 0; i < room.getBlocks().size(); ++i) {
         Block block = (Block)room.getBlocks().get(i);
         Block copy = new Block(block);
         room.addEntity(copy);
      }

      room.getBlocks().clear();
      return room;
   }

   public Room connect(Direction direction, Room roomType, Random random) {
      Room result = null;
      switch($SWITCH_TABLE$enumerations$Direction()[direction.ordinal()]) {
      case 1:
         this.createExit(Direction.UP);
         result = roomType.createInstance(this.x, this.y - this.image.getHeight(), random);
         result.createExit(Direction.DOWN);
         break;
      case 2:
         this.createExit(Direction.DOWN);
         result = roomType.createInstance(this.x, this.y + this.image.getHeight(), random);
         result.createExit(Direction.UP);
         break;
      case 3:
         this.createExit(Direction.LEFT);
         result = roomType.createInstance(this.x - this.image.getWidth(), this.y, random);
         result.createExit(Direction.RIGHT);
         break;
      case 4:
         this.createExit(Direction.RIGHT);
         result = roomType.createInstance(this.x + this.image.getWidth(), this.y, random);
         result.createExit(Direction.LEFT);
      }

      return result;
   }

   public void createExit(Direction direction) {
      Rectangle var10000;
      int i;
      TransformComponent transformComponent;
      int j;
      switch($SWITCH_TABLE$enumerations$Direction()[direction.ordinal()]) {
      case 1:
         if (!this.hasUpExit) {
            var10000 = this.boundsUp[0];
            var10000.width -= this.exitMaps[0].getRectangle().width / 2;
            var10000 = this.boundsUp[1];
            var10000.width -= this.exitMaps[0].getRectangle().width / 2;
            var10000 = this.boundsUp[1];
            var10000.x += this.exitMaps[0].getRectangle().width / 2;

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");
                  if (transformComponent.getTransform().intersects(this.exitMaps[0].getRectangle())) {
                     this.tiles[i] = null;
                  }
               }
            }

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");

                  for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[0]).length; ++j) {
                     if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[0])[j].getRectangle())) {
                        this.tiles[i] = null;
                     }
                  }
               }
            }

            this.refreshImage();
            this.drawExit(Direction.UP);
            if (this.hasDownExit) {
               this.drawExit(Direction.DOWN);
            }

            if (this.hasLeftExit) {
               this.drawExit(Direction.LEFT);
            }

            if (this.hasRightExit) {
               this.drawExit(Direction.RIGHT);
            }

            this.hasUpExit = true;
         }
         break;
      case 2:
         if (!this.hasDownExit) {
            var10000 = this.boundsDown[0];
            var10000.width -= this.exitMaps[1].getRectangle().width / 2;
            var10000 = this.boundsDown[1];
            var10000.width -= this.exitMaps[1].getRectangle().width / 2;
            var10000 = this.boundsDown[1];
            var10000.x += this.exitMaps[0].getRectangle().width / 2;

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");
                  if (transformComponent.getTransform().intersects(this.exitMaps[1].getRectangle())) {
                     this.tiles[i] = null;
                  }
               }
            }

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");

                  for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[1]).length; ++j) {
                     if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[1])[j].getRectangle())) {
                        this.tiles[i] = null;
                     }
                  }
               }
            }

            this.refreshImage();
            this.drawExit(Direction.DOWN);
            if (this.hasUpExit) {
               this.drawExit(Direction.UP);
            }

            if (this.hasLeftExit) {
               this.drawExit(Direction.LEFT);
            }

            if (this.hasRightExit) {
               this.drawExit(Direction.RIGHT);
            }

            this.hasDownExit = true;
         }
         break;
      case 3:
         if (!this.hasLeftExit) {
            var10000 = this.boundsLeft[0];
            var10000.height -= this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsLeft[1];
            var10000.height -= this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsLeft[1];
            var10000.y += this.exitMaps[2].getRectangle().height / 2;

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");
                  if (transformComponent.getTransform().intersects(this.exitMaps[2].getRectangle())) {
                     this.tiles[i] = null;
                  }
               }
            }

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");

                  for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[2]).length; ++j) {
                     if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[2])[j].getRectangle())) {
                        this.tiles[i] = null;
                     }
                  }
               }
            }

            this.refreshImage();
            this.drawExit(Direction.LEFT);
            if (this.hasUpExit) {
               this.drawExit(Direction.UP);
            }

            if (this.hasDownExit) {
               this.drawExit(Direction.DOWN);
            }

            if (this.hasRightExit) {
               this.drawExit(Direction.RIGHT);
            }

            this.hasLeftExit = true;
         }
         break;
      case 4:
         if (!this.hasRightExit) {
            var10000 = this.boundsRight[0];
            var10000.height -= this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsRight[1];
            var10000.height -= this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsRight[1];
            var10000.y += this.exitMaps[2].getRectangle().height / 2;

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");
                  if (transformComponent.getTransform().intersects(this.exitMaps[3].getRectangle())) {
                     this.tiles[i] = null;
                  }
               }
            }

            for(i = 0; i < this.tiles.length; ++i) {
               if (this.tiles[i] != null) {
                  transformComponent = (TransformComponent)this.tiles[i].getComponent("TransformComponent");

                  for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[3]).length; ++j) {
                     if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[3])[j].getRectangle())) {
                        this.tiles[i] = null;
                     }
                  }
               }
            }

            this.refreshImage();
            this.drawExit(Direction.RIGHT);
            if (this.hasUpExit) {
               this.drawExit(Direction.UP);
            }

            if (this.hasDownExit) {
               this.drawExit(Direction.DOWN);
            }

            if (this.hasLeftExit) {
               this.drawExit(Direction.LEFT);
            }

            this.hasRightExit = true;
         }
      }

   }

   public void removeExit(Direction direction) {
      Rectangle var10000;
      int i;
      TransformComponent transformComponent;
      int j;
      switch($SWITCH_TABLE$enumerations$Direction()[direction.ordinal()]) {
      case 1:
         if (this.hasUpExit) {
            var10000 = this.boundsUp[0];
            var10000.width = (int)((double)var10000.width + this.exitMaps[0].getRectangle().getWidth() / 2.0D);
            var10000 = this.boundsUp[1];
            var10000.width += this.exitMaps[0].getRectangle().width / 2;
            var10000 = this.boundsUp[1];
            var10000.x -= this.exitMaps[0].getRectangle().width / 2;

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");
               if (transformComponent.getTransform().intersects(this.exitMaps[0].getRectangle())) {
                  this.tiles[i] = this.defaultTiles[i];
               }
            }

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");

               for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[0]).length; ++j) {
                  if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[0])[j].getRectangle())) {
                     this.tiles[i] = this.defaultTiles[i];
                  }
               }
            }

            this.refreshImage();
            if (this.hasDownExit) {
               this.drawExit(Direction.DOWN);
            }

            if (this.hasLeftExit) {
               this.drawExit(Direction.LEFT);
            }

            if (this.hasRightExit) {
               this.drawExit(Direction.RIGHT);
            }

            this.hasUpExit = false;
         }
         break;
      case 2:
         if (this.hasDownExit) {
            var10000 = this.boundsDown[0];
            var10000.width += this.exitMaps[1].getRectangle().width / 2;
            var10000 = this.boundsDown[1];
            var10000.width += this.exitMaps[1].getRectangle().width / 2;
            var10000 = this.boundsDown[1];
            var10000.x -= this.exitMaps[0].getRectangle().width / 2;

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");
               if (transformComponent.getTransform().intersects(this.exitMaps[1].getRectangle())) {
                  this.tiles[i] = this.defaultTiles[i];
               }
            }

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");

               for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[1]).length; ++j) {
                  if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[1])[j].getRectangle())) {
                     this.tiles[i] = this.defaultTiles[i];
                  }
               }
            }

            this.refreshImage();
            if (this.hasUpExit) {
               this.drawExit(Direction.UP);
            }

            if (this.hasLeftExit) {
               this.drawExit(Direction.LEFT);
            }

            if (this.hasRightExit) {
               this.drawExit(Direction.RIGHT);
            }

            this.hasDownExit = false;
         }
         break;
      case 3:
         if (this.hasLeftExit) {
            var10000 = this.boundsLeft[0];
            var10000.height += this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsLeft[1];
            var10000.height += this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsLeft[1];
            var10000.y -= this.exitMaps[2].getRectangle().height / 2;

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");
               if (transformComponent.getTransform().intersects(this.exitMaps[2].getRectangle())) {
                  this.tiles[i] = this.defaultTiles[i];
               }
            }

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");

               for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[2]).length; ++j) {
                  if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[2])[j].getRectangle())) {
                     this.tiles[i] = this.defaultTiles[i];
                  }
               }
            }

            this.refreshImage();
            if (this.hasUpExit) {
               this.drawExit(Direction.UP);
            }

            if (this.hasDownExit) {
               this.drawExit(Direction.DOWN);
            }

            if (this.hasRightExit) {
               this.drawExit(Direction.RIGHT);
            }

            this.hasLeftExit = false;
         }
         break;
      case 4:
         if (this.hasRightExit) {
            var10000 = this.boundsRight[0];
            var10000.height += this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsRight[1];
            var10000.height += this.exitMaps[2].getRectangle().height / 2;
            var10000 = this.boundsRight[1];
            var10000.y -= this.exitMaps[2].getRectangle().height / 2;

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");
               if (transformComponent.getTransform().intersects(this.exitMaps[3].getRectangle())) {
                  this.tiles[i] = this.defaultTiles[i];
               }
            }

            for(i = 0; i < this.defaultTiles.length; ++i) {
               transformComponent = (TransformComponent)this.defaultTiles[i].getComponent("TransformComponent");

               for(j = 0; j < this.getModifiedExitMaps(this.exitMaps[3]).length; ++j) {
                  if (transformComponent.getTransform().intersects(this.getModifiedExitMaps(this.exitMaps[3])[j].getRectangle())) {
                     this.tiles[i] = this.defaultTiles[i];
                  }
               }
            }

            this.refreshImage();
            if (this.hasUpExit) {
               this.drawExit(Direction.UP);
            }

            if (this.hasDownExit) {
               this.drawExit(Direction.DOWN);
            }

            if (this.hasLeftExit) {
               this.drawExit(Direction.LEFT);
            }

            this.hasRightExit = false;
         }
         break;
      default:
         return;
      }

   }

   public void drawExit(Direction direction) {
      int i;
      switch($SWITCH_TABLE$enumerations$Direction()[direction.ordinal()]) {
      case 1:
         this.g.drawImage(Images.blockImages[this.borderIds[3]], this.exitMaps[0].getRectangle().x - 46, 0, 46, 46, (ImageObserver)null);
         this.g.drawImage(Images.blockImages[this.borderIds[2]], this.exitMaps[0].getRectangle().x + this.exitMaps[0].getRectangle().width, 0, 46, 46, (ImageObserver)null);
         break;
      case 2:
         this.g.drawImage(Images.blockImages[this.borderIds[1]], this.exitMaps[1].getRectangle().x - 46, this.image.getHeight() - 46, 46, 46, (ImageObserver)null);
         this.g.drawImage(Images.blockImages[this.borderIds[0]], this.exitMaps[1].getRectangle().x + this.exitMaps[1].getRectangle().width, this.image.getHeight() - 46, 46, 46, (ImageObserver)null);
         break;
      case 3:
         this.g.drawImage(Images.blockImages[this.borderIds[3]], 0, this.exitMaps[2].getRectangle().y - 46, 46, 46, (ImageObserver)null);
         this.g.drawImage(Images.blockImages[this.borderIds[1]], 0, this.exitMaps[2].getRectangle().y + this.exitMaps[2].getRectangle().height, 46, 46, (ImageObserver)null);

         for(i = 0; i < this.exitMaps[2].getRectangle().width; ++i) {
            if (i % 46 == 0) {
               this.g.drawImage(Images.blockImages[this.borderIds[4]], i + this.exitMaps[2].getRectangle().x, this.exitMaps[2].getRectangle().y, 46, 46, (ImageObserver)null);
            }
         }

         return;
      case 4:
         this.g.drawImage(Images.blockImages[this.borderIds[2]], this.image.getWidth() - 46, this.exitMaps[3].getRectangle().y - 46, 46, 46, (ImageObserver)null);
         this.g.drawImage(Images.blockImages[this.borderIds[0]], this.image.getWidth() - 46, this.exitMaps[3].getRectangle().y + this.exitMaps[3].getRectangle().height, 46, 46, (ImageObserver)null);

         for(i = 0; i < this.exitMaps[3].getRectangle().width; ++i) {
            if (i % 46 == 0) {
               this.g.drawImage(Images.blockImages[this.borderIds[4]], i + this.exitMaps[3].getRectangle().x, this.exitMaps[3].getRectangle().y, 46, 46, (ImageObserver)null);
            }
         }
      }

   }

   public void refreshImage() {
      this.g.clearRect(0, 0, this.image.getWidth(), this.image.getHeight());

      int i;
      for(i = 0; i < this.widthInTiles; ++i) {
         for(int j = 0; j < this.heightInTiles; ++j) {
            BlockRenderComponent renderComponent = (BlockRenderComponent)this.surfaceTiles[i][j].getComponent("BlockRenderComponent");
            renderComponent.render(this.g);
         }
      }

      for(i = 0; i < this.tiles.length; ++i) {
         if (this.tiles[i] != null) {
            BlockRenderComponent renderComponent = (BlockRenderComponent)this.tiles[i].getComponent("BlockRenderComponent");
            renderComponent.render(this.g);
         }
      }

   }

   public Room getSurroundingRoom(Direction direction) {
      Room result = null;

      for(int i = 0; i < this.handler.getRooms().size(); ++i) {
         Room room = (Room)this.handler.getRooms().get(i);
         if (room != this) {
            switch($SWITCH_TABLE$enumerations$Direction()[direction.ordinal()]) {
            case 1:
               if (room.x == this.x && room.y == this.y - this.image.getHeight()) {
                  result = room;
               }
               break;
            case 2:
               if (room.x == this.x && room.y == this.y + this.image.getHeight()) {
                  result = room;
               }
               break;
            case 3:
               if (room.x == this.x - this.image.getWidth() && room.y == this.y) {
                  result = room;
               }
               break;
            case 4:
               if (room.x == this.x + this.image.getWidth() && room.y == this.y) {
                  result = room;
               }
            }
         }
      }

      return result;
   }

   public void removeOverlaps() {
      for(int i = 0; i < this.handler.getRooms().size(); ++i) {
         Room room = (Room)this.handler.getRooms().get(i);
         if (room != this && this.x == room.x && this.y == room.y) {
            this.handler.removeRoom(room);
         }
      }

   }

   public Direction[] getAvailableDirections() {
      LinkedList<Direction> directions = new LinkedList<Direction>();

      int i;
      for(i = 0; i < Direction.values().length; ++i) {
         Direction direction = Direction.values()[i];
         if (this.getSurroundingRoom(direction) == null) {
            directions.add(direction);
         }
      }

      Direction[] availableDirections = new Direction[directions.size()];

      for(i = 0; i < directions.size(); ++i) {
         availableDirections[i] = (Direction)directions.get(i);
      }

      directions.clear();
      return availableDirections;
   }

   public ExitMap[] getModifiedExitMaps(ExitMap exitMap) {
      ExitMap[] results = new ExitMap[2];
      Rectangle rectangle = null;
      switch($SWITCH_TABLE$enumerations$Direction()[exitMap.getDirection().ordinal()]) {
      case 1:
         rectangle = new Rectangle();
         rectangle.x = exitMap.getRectangle().x - 46;
         rectangle.y = exitMap.getRectangle().y;
         rectangle.width = 46;
         rectangle.height = 46;
         results[0] = new ExitMap(Direction.UP, rectangle);
         rectangle = new Rectangle(rectangle);
         rectangle.x = exitMap.getRectangle().x + exitMap.getRectangle().width;
         results[1] = new ExitMap(Direction.UP, rectangle);
         break;
      case 2:
         rectangle = new Rectangle();
         rectangle.x = exitMap.getRectangle().x - 46;
         rectangle.y = exitMap.getRectangle().y;
         rectangle.width = 46;
         rectangle.height = 46;
         results[0] = new ExitMap(Direction.DOWN, rectangle);
         rectangle = new Rectangle(rectangle);
         rectangle.x = exitMap.getRectangle().x + exitMap.getRectangle().width;
         results[1] = new ExitMap(Direction.DOWN, rectangle);
         break;
      case 3:
         rectangle = new Rectangle();
         rectangle.x = exitMap.getRectangle().x;
         rectangle.y = exitMap.getRectangle().y - 46;
         rectangle.width = 46;
         rectangle.height = 46;
         results[0] = new ExitMap(Direction.LEFT, rectangle);
         rectangle = new Rectangle(rectangle);
         rectangle.y = exitMap.getRectangle().y + exitMap.getRectangle().height;
         results[1] = new ExitMap(Direction.LEFT, rectangle);
         break;
      case 4:
         rectangle = new Rectangle();
         rectangle.x = exitMap.getRectangle().x;
         rectangle.y = exitMap.getRectangle().y - 46;
         rectangle.width = 46;
         rectangle.height = 46;
         results[0] = new ExitMap(Direction.RIGHT, rectangle);
         rectangle = new Rectangle(rectangle);
         rectangle.y = exitMap.getRectangle().y + exitMap.getRectangle().height;
         results[1] = new ExitMap(Direction.RIGHT, rectangle);
      }

      return results;
   }

   public boolean checkEntityInBounds(Entity entity) {
      TransformComponent transformComponent = (TransformComponent)entity.getComponent("TransformComponent");
      Transform t = new Transform(this.getBounds());
      return t.inside(transformComponent.getTransform());
   }

   public void render(Graphics g) {
      g.drawImage(this.image, this.x, this.y, (ImageObserver)null);
   }

   public Rectangle getBounds() {
      return new Rectangle(this.x, this.y, 23 * this.widthInTiles, 23 * this.heightInTiles);
   }

   public Rectangle[] getBoundsUp() {
      return this.boundsUp;
   }

   public void setBoundsUp(Rectangle[] boundsUp) {
      this.boundsUp = boundsUp;
   }

   public Rectangle[] getBoundsDown() {
      return this.boundsDown;
   }

   public void setBoundsDown(Rectangle[] boundsDown) {
      this.boundsDown = boundsDown;
   }

   public Rectangle[] getBoundsLeft() {
      return this.boundsLeft;
   }

   public void setBoundsLeft(Rectangle[] boundsLeft) {
      this.boundsLeft = boundsLeft;
   }

   public Rectangle[] getBoundsRight() {
      return this.boundsRight;
   }

   public void setBoundsRight(Rectangle[] boundsRight) {
      this.boundsRight = boundsRight;
   }

   public boolean hasExit(Direction direction) {
      if (direction == Direction.UP) {
         return this.hasUpExit;
      } else if (direction == Direction.DOWN) {
         return this.hasDownExit;
      } else if (direction == Direction.LEFT) {
         return this.hasLeftExit;
      } else {
         return direction == Direction.RIGHT ? this.hasRightExit : false;
      }
   }

   public int getWidthInTiles() {
      return this.widthInTiles;
   }

   public void setWidthInTiles(int widthInTiles) {
      this.widthInTiles = widthInTiles;
   }

   public int getHeightInTiles() {
      return this.heightInTiles;
   }

   public void setHeightInTiles(int heightInTiles) {
      this.heightInTiles = heightInTiles;
   }

   public int[] getTileIds() {
      return this.tileIds;
   }

   public void setTileIds(int[] tileIds) {
      this.tileIds = tileIds;
   }

   public Random getRandom() {
      return this.random;
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public BufferedImage getImage() {
      return this.image;
   }

   public void setImage(BufferedImage image) {
      this.image = image;
   }

   public Block[][] getSurfaceTiles() {
      return this.surfaceTiles;
   }

   public void setSurfaceTiles(Block[][] surfaceTiles) {
      this.surfaceTiles = surfaceTiles;
   }

   public ArrayList<Block> getBlocks() {
      return this.blocks;
   }

   public int[] getBorderIds() {
      return this.borderIds;
   }

   public void setBorderIds(int[] borderIds) {
      this.borderIds = borderIds;
   }

   public Block[] getTiles() {
      return this.tiles;
   }

   public void setTiles(Block[] tiles) {
      this.tiles = tiles;
   }

   public ExitMap[] getExitMaps() {
      return this.exitMaps;
   }

   public void setExitMaps(ExitMap[] exitMaps) {
      this.exitMaps = exitMaps;
   }

   public void setBlocks(ArrayList<Block> blocks) {
      this.blocks = blocks;
   }

   public Room getType() {
      return this.roomType;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$enumerations$Direction() {
      int[] var10000 = $SWITCH_TABLE$enumerations$Direction;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[Direction.values().length];

         try {
            var0[Direction.DOWN.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[Direction.LEFT.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[Direction.RIGHT.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[Direction.UP.ordinal()] = 1;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$enumerations$Direction = var0;
         return var0;
      }
   }
}
