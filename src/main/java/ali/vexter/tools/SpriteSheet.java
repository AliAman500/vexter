package ali.vexter.tools;

import java.awt.image.BufferedImage;

public class SpriteSheet {
   private BufferedImage image;
   private int rowFactor = 64;
   private int columnFactor = 64;

   public SpriteSheet(String filePath, Loader loader) {
      this.image = loader.loadImage(filePath);
   }

   public BufferedImage get(int column, int row) {
      int x = column * this.columnFactor - this.columnFactor;
      int y = row * this.rowFactor - this.rowFactor;
      return this.image.getSubimage(x, y, this.rowFactor, this.columnFactor);
   }

   public BufferedImage get(int x, int y, int width, int height) {
      return this.image.getSubimage(x, y, width, height);
   }

   public BufferedImage getImage() {
      return this.image;
   }

   public int getRowFactor() {
      return this.rowFactor;
   }

   public void setRowFactor(int rowFactor) {
      this.rowFactor = rowFactor;
   }

   public int getColumnFactor() {
      return this.columnFactor;
   }

   public void setColumnFactor(int columnFactor) {
      this.columnFactor = columnFactor;
   }
}
