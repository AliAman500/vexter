package ali.vexter.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import ali.vexter.tools.Loader;

public class HUD {
   private BufferedImage image;

   public HUD(Loader loader) {
      this.image = loader.loadImage("/hudImages/HUDImage.png");
   }

   public void render(Graphics g) {
      g.drawImage(this.image, 0, 0, 1250, 700, (ImageObserver)null);
      g.setColor(Color.GREEN);
      g.setFont(new Font("Consolas", 1, 13));
      g.drawString("FPS: " + GameManager.fps, 10, 20);
   }
}
