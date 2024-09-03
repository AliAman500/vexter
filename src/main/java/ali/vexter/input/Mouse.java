package ali.vexter.input;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter {
   private int px;
   private int py;
   public static int dx;
   public static int dy;

   public void update() {
      int xNow = MouseInfo.getPointerInfo().getLocation().x;
      int yNow = MouseInfo.getPointerInfo().getLocation().y;
      dx = xNow - this.px;
      dy = yNow - this.py;
      this.px = xNow;
      this.py = yNow;
   }

   public void mousePressed(MouseEvent e) {
   }

   public void mouseReleased(MouseEvent e) {
   }

   public void mouseDragged(MouseEvent e) {
   }

   public void mouseWheelMoved(MouseWheelEvent e) {
   }

   public void mouseMoved(MouseEvent e) {
   }
}
