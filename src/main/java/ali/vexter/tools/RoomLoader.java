package ali.vexter.tools;

import ali.vexter.entities.Block;
import ali.vexter.enumerations.Direction;
import ali.vexter.framework.ExitMap;
import ali.vexter.framework.GameManager;
import ali.vexter.framework.Room;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RoomLoader {
   private Handler handler;

   public RoomLoader(Handler handler) {
      this.handler = handler;
   }

   public Room loadRoom(String fileName) {
      String line = "";
      int widthInTiles = 0;
      int heightInTiles = 0;
      int[] tileIds = null;
      int[] borderIds = null;
      Block[] tiles = null;
      ArrayList<Block> tilesList = new ArrayList<Block>();
      ArrayList<Block> blocks = new ArrayList<Block>();
      ExitMap[] exitMaps = new ExitMap[4];

      try {
         InputStream in = RoomLoader.class.getResourceAsStream(fileName);
         BufferedReader reader = new BufferedReader(new InputStreamReader(in));

         while(true) {
            while((line = reader.readLine()) != null) {
               String[] currentLine = line.split(" ");
               int id;
               if (line.startsWith("IDs ")) {
                  tileIds = new int[currentLine.length - 1];

                  for(id = 1; id < currentLine.length; ++id) {
                     tileIds[id - 1] = Integer.parseInt(currentLine[id]);
                  }
               } else if (line.startsWith("BorderIDs ")) {
                  borderIds = new int[currentLine.length - 1];

                  for(id = 1; id < currentLine.length; ++id) {
                     borderIds[id - 1] = Integer.parseInt(currentLine[id]);
                  }
               } else if (line.startsWith("GridDimensions ")) {
                  widthInTiles = Integer.parseInt(currentLine[1]);
                  heightInTiles = Integer.parseInt(currentLine[2]);
               } else {
                  int tileX;
                  int tileY;
                  ExitMap exitMap;
                  if (line.startsWith("ExitMap(U) ")) {
                     id = Integer.parseInt(currentLine[1]) * 46;
                     tileX = Integer.parseInt(currentLine[2]) * 46;
                     tileY = widthInTiles / 2 * 23 - id / 2;
                     int y = 0;
                     exitMap = new ExitMap(Direction.UP, new Rectangle(tileY, y, id, tileX));
                     exitMaps[0] = exitMap;
                  } else {
                     int y;
                     if (line.startsWith("ExitMap(D) ")) {
                        id = Integer.parseInt(currentLine[1]) * 46;
                        tileX = Integer.parseInt(currentLine[2]) * 46;
                        tileY = widthInTiles / 2 * 23 - id / 2;
                        y = heightInTiles * 23 - tileX;
                        exitMap = new ExitMap(Direction.DOWN, new Rectangle(tileY, y, id, tileX));
                        exitMaps[1] = exitMap;
                     } else if (line.startsWith("ExitMap(L) ")) {
                        id = Integer.parseInt(currentLine[1]) * 46;
                        tileX = Integer.parseInt(currentLine[2]) * 46;
                        int x = 0;
                        y = heightInTiles / 2 * 23 - tileX / 2;
                        exitMap = new ExitMap(Direction.LEFT, new Rectangle(x, y, id, tileX));
                        exitMaps[2] = exitMap;
                     } else if (line.startsWith("ExitMap(R) ")) {
                        id = Integer.parseInt(currentLine[1]) * 46;
                        tileX = Integer.parseInt(currentLine[2]) * 46;
                        tileY = widthInTiles * 23 - id;
                        y = heightInTiles / 2 * 23 - tileX / 2;
                        exitMap = new ExitMap(Direction.RIGHT, new Rectangle(tileY, y, id, tileX));
                        exitMaps[3] = exitMap;
                     } else if (line.startsWith("BlockInfo ")) {
                        id = Integer.parseInt(currentLine[1]);
                        tileX = Integer.parseInt(currentLine[2]);
                        tileY = Integer.parseInt(currentLine[3]);
                        boolean isTile = Boolean.parseBoolean(currentLine[4]);
                        Block block = new Block(46 * tileX, 46 * tileY, 46, 46, id);
                        if (isTile) {
                           tilesList.add(block);
                        } else {
                           blocks.add(block);
                        }
                     }
                  }
               }
            }

            reader.close();
            break;
         }
      } catch (Exception var19) {
         var19.printStackTrace();
      }

      tiles = new Block[tilesList.size()];

      for(int i = 0; i < tilesList.size(); ++i) {
         tiles[i] = (Block)tilesList.get(i);
      }

      tilesList.clear();
      Room room = new Room(0, 0, widthInTiles, heightInTiles, tileIds, borderIds, tiles, blocks, exitMaps, GameManager.random, this.handler);
      return room;
   }
}
