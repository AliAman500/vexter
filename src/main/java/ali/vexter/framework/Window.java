package ali.vexter.framework;

import java.awt.Component;
import javax.swing.JFrame;

public class Window {
   private int width;
   private int height;
   private JFrame jFrame;

   public Window(int width, int height, String title, GameManager game) {
      this.width = width;
      this.height = height;
      this.jFrame = new JFrame(title);
      this.jFrame.setSize(width, height);
      this.jFrame.add(game);
      this.jFrame.setDefaultCloseOperation(3);
      this.jFrame.setLocationRelativeTo((Component)null);
      this.jFrame.setResizable(false);
      this.jFrame.setVisible(true);
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public JFrame getJFrame() {
      return this.jFrame;
   }
}
