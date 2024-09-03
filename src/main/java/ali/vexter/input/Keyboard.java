package ali.vexter.input;

import ali.vexter.framework.GameManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ali.vexter.playerComponents.PlayerMovementComponent;

public class Keyboard extends KeyAdapter {
   private GameManager gameManager;
   private PlayerMovementComponent playerMovement;

   public Keyboard(GameManager gameManager) {
      this.gameManager = gameManager;
   }

   public void keyPressed(KeyEvent e) {
      this.playerMovement = (PlayerMovementComponent)this.gameManager.player.getComponent("PlayerMovementComponent");
      int key = e.getKeyCode();
      if (key == 38) {
         this.playerMovement.up = true;
      }

      if (key == 40) {
         this.playerMovement.down = true;
      }

      if (key == 37) {
         this.playerMovement.left = true;
      }

      if (key == 39) {
         this.playerMovement.right = true;
      }

   }

   public void keyReleased(KeyEvent e) {
      if (this.playerMovement != null) {
         int key = e.getKeyCode();
         if (key == 38) {
            this.playerMovement.up = false;
         }

         if (key == 40) {
            this.playerMovement.down = false;
         }

         if (key == 37) {
            this.playerMovement.left = false;
         }

         if (key == 39) {
            this.playerMovement.right = false;
         }
      }

   }
}
