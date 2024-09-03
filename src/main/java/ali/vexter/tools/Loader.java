package ali.vexter.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Loader {
   private BufferedImage imageToLoad;

   public BufferedImage loadImage(String pathToFile) {
      BufferedImage image = null;

      try {
         image = ImageIO.read(this.getClass().getResource(pathToFile));
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      this.imageToLoad = new BufferedImage(image.getWidth(), image.getHeight(), 2);
      Graphics g = this.imageToLoad.getGraphics();
      g.drawImage(image, 0, 0, (ImageObserver)null);
      System.out.println("Resource loaded: " + pathToFile);
      return this.imageToLoad;
   }
}
