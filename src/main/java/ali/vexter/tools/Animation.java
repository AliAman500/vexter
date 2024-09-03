package ali.vexter.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Animation {
   private int speed;
   private int indexSpeed;
   private int index;
   private long lastTime;
   private long timer;
   private BufferedImage[] frames;

   public Animation(int speed, int indexSpeed, BufferedImage[] frames) {
      this.index = 0;
      this.lastTime = System.currentTimeMillis();
      this.timer = 0L;
      this.speed = speed;
      this.indexSpeed = indexSpeed;
      this.frames = frames;
   }

   public Animation(int speed, BufferedImage[] frames) {
      this(speed, 1, frames);
   }

   public void update() {
      this.timer += System.currentTimeMillis() - this.lastTime;
      this.lastTime = System.currentTimeMillis();
      if (this.timer > (long)this.speed) {
         this.index += this.indexSpeed;
         this.timer = 0L;
         if (this.index >= this.frames.length) {
            this.index = 0;
         }
      }

   }

   public BufferedImage getCurrentFrame() {
      return this.frames[this.index];
   }

   public void render(Graphics g, int x, int y, int width, int height) {
      g.drawImage(this.getCurrentFrame(), x, y, width, height, (ImageObserver)null);
   }

   public void render(Graphics g, int x, int y) {
      g.drawImage(this.getCurrentFrame(), x, y, (ImageObserver)null);
   }

   public int getSpeed() {
      return this.speed;
   }

   public void setSpeed(int speed) {
      this.speed = speed;
   }

   public int getIndexSpeed() {
      return this.indexSpeed;
   }

   public void setIndexSpeed(int indexSpeed) {
      this.indexSpeed = indexSpeed;
   }

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public BufferedImage[] getFrames() {
      return this.frames;
   }

   public void setFrames(BufferedImage[] frames) {
      this.frames = frames;
   }
}
