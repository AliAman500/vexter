package ali.vexter.tools;

import java.awt.image.BufferedImage;

public class Images {
   public static BufferedImage[] blockImages = new BufferedImage[26];
   public static BufferedImage[] playerIdleImages = new BufferedImage[4];
   public static BufferedImage[] playerRunImages = new BufferedImage[24];
   public static BufferedImage[] playerRunDownImages = new BufferedImage[6];
   public static BufferedImage[] playerRunUpImages = new BufferedImage[6];

   public static void init(Loader loader, SpriteSheet blockSheet, SpriteSheet playerSheet) {
      blockImages[0] = blockSheet.get(1, 1);
      blockImages[1] = blockSheet.get(2, 1);
      blockImages[2] = blockSheet.get(3, 1);
      blockImages[3] = blockSheet.get(4, 1);
      blockImages[4] = blockSheet.get(5, 1);
      blockImages[5] = blockSheet.get(6, 1);
      blockImages[6] = blockSheet.get(7, 1);
      blockImages[7] = blockSheet.get(8, 1);
      blockImages[8] = blockSheet.get(1, 2);
      blockImages[9] = blockSheet.get(2, 2);
      blockImages[10] = blockSheet.get(3, 2);
      blockImages[11] = blockSheet.get(4, 2);
      blockImages[12] = blockSheet.get(5, 2);
      blockImages[13] = blockSheet.get(6, 2);
      blockImages[14] = blockSheet.get(7, 2);
      blockImages[15] = blockSheet.get(8, 2);
      blockImages[16] = blockSheet.get(1, 3);
      blockImages[17] = blockSheet.get(2, 3);
      blockImages[18] = blockSheet.get(3, 3);
      blockImages[19] = blockSheet.get(192, 128, 128, 128);
      blockImages[20] = blockSheet.get(6, 3);
      blockImages[21] = blockSheet.get(7, 3);
      blockImages[22] = blockSheet.get(8, 3);
      blockImages[23] = blockSheet.get(1, 4);
      blockImages[24] = blockSheet.get(2, 4);
      blockImages[25] = blockSheet.get(3, 4);
      playerIdleImages[0] = playerSheet.get(1, 1);
      playerIdleImages[1] = playerSheet.get(2, 1);
      playerIdleImages[2] = playerSheet.get(3, 1);
      playerIdleImages[3] = playerSheet.get(4, 1);
      playerIdleImages = Utils.reverseDuplicateImages(playerIdleImages);
      playerRunImages[0] = playerSheet.get(1, 2);
      playerRunImages[1] = playerSheet.get(2, 2);
      playerRunImages[2] = playerSheet.get(3, 2);
      playerRunImages[3] = playerSheet.get(4, 2);
      playerRunImages[4] = playerSheet.get(5, 2);
      playerRunImages[5] = playerSheet.get(6, 2);
      playerRunImages[6] = playerSheet.get(7, 2);
      playerRunImages[7] = playerSheet.get(8, 2);
      playerRunImages[8] = playerSheet.get(1, 3);
      playerRunImages[9] = playerSheet.get(2, 3);
      playerRunImages[10] = playerSheet.get(3, 3);
      playerRunImages[11] = playerSheet.get(4, 3);
      playerRunImages[12] = playerSheet.get(5, 3);
      playerRunImages[13] = playerSheet.get(6, 3);
      playerRunImages[14] = playerSheet.get(7, 3);
      playerRunImages[15] = playerSheet.get(8, 3);
      playerRunImages[16] = playerSheet.get(1, 4);
      playerRunImages[17] = playerSheet.get(2, 4);
      playerRunImages[18] = playerSheet.get(3, 4);
      playerRunImages[19] = playerSheet.get(4, 4);
      playerRunImages[20] = playerSheet.get(5, 4);
      playerRunImages[21] = playerSheet.get(6, 4);
      playerRunImages[22] = playerSheet.get(7, 4);
      playerRunImages[23] = playerSheet.get(8, 4);
      playerRunImages = Utils.reverseDuplicateImages(playerRunImages);
      playerRunDownImages[0] = playerSheet.get(1, 5);
      playerRunDownImages[1] = playerSheet.get(2, 5);
      playerRunDownImages[2] = playerSheet.get(3, 5);
      playerRunDownImages[3] = playerSheet.get(4, 5);
      playerRunDownImages[4] = playerSheet.get(5, 5);
      playerRunDownImages[5] = playerSheet.get(6, 5);
      playerRunDownImages = Utils.reverseDuplicateImages(playerRunDownImages);
      playerRunUpImages[0] = playerSheet.get(1, 6);
      playerRunUpImages[1] = playerSheet.get(2, 6);
      playerRunUpImages[2] = playerSheet.get(3, 6);
      playerRunUpImages[3] = playerSheet.get(4, 6);
      playerRunUpImages[4] = playerSheet.get(5, 6);
      playerRunUpImages[5] = playerSheet.get(6, 6);
      playerRunUpImages = Utils.reverseDuplicateImages(playerRunUpImages);
   }
}
