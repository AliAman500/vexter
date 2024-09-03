package ali.vexter.framework;

import ali.vexter.entities.Player;
import ali.vexter.input.Keyboard;
import ali.vexter.input.Mouse;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;
import ali.vexter.tools.Handler;
import ali.vexter.tools.Images;
import ali.vexter.tools.Loader;
import ali.vexter.tools.RoomLoader;
import ali.vexter.tools.SpriteSheet;

public class GameManager extends Canvas implements Runnable {
   private static final long serialVersionUID = 1L;
   public static final String title = "Game";
   public static final int WIDTH = 1250;
   public static final int HEIGHT = 700;
   public static final int DEFAULT_TILE_SIZE = 46;
   public static int fps;
   public Player player;
   private Window window;
   private Thread thread;
   private Loader loader;
   private RoomLoader roomLoader;
   private Camera camera;
   private Handler handler;
   private RoomManager roomManager;
   private Keyboard keyboard;
   private Mouse mouse;
   private SpriteSheet blockSheet;
   private SpriteSheet playerSheet;
   private Level level;
   private HUD hud;
   public static Random random = new Random();
   private boolean running = false;

   public void init() {
      System.setProperty("sun.java2d.opengl", "True");
      this.window = new Window(1250, 700, "Game", this);
      this.setBackground(Color.black);
      this.camera = new Camera(0.0F, 0.0F, this);
      this.handler = new Handler(this.camera);
      this.loader = new Loader();
      this.hud = new HUD(this.loader);
      this.roomLoader = new RoomLoader(this.handler);
      this.keyboard = new Keyboard(this);
      this.mouse = new Mouse();
      this.blockSheet = new SpriteSheet("/spriteSheets/BlockSheet.png", this.loader);
      this.playerSheet = new SpriteSheet("/spriteSheets/PlayerSheet.png", this.loader);
      Images.init(this.loader, this.blockSheet, this.playerSheet);
      Room.init(this.roomLoader);
      this.roomManager = new RoomManager(this.handler);
      this.level = new Level(16, this.roomManager, this.handler, this);
      this.level.generate();
      this.addKeyListener(this.keyboard);
      this.addMouseListener(this.mouse);
      this.addMouseMotionListener(this.mouse);
      this.addMouseWheelListener(this.mouse);
      this.requestFocus();
   }

   public void start() {
      this.thread = new Thread(this);
      this.running = true;
      this.thread.start();
   }

   public void stop() {
      try {
         this.thread.join();
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      this.running = true;
   }

   public void update() {
      this.handler.update();
      this.camera.update();
      this.mouse.update();
   }

   public void render() {
      BufferStrategy bs = this.getBufferStrategy();
      if (bs == null) {
         this.createBufferStrategy(3);
      } else {
         Graphics g = bs.getDrawGraphics();
         Graphics2D g2d = (Graphics2D)g;
         g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         g.setColor(Color.black);
         g.fillRect(0, 0, this.window.getWidth(), this.window.getHeight());
         g2d.translate((double)(-this.camera.getX()), (double)(-this.camera.getY()));
         this.handler.render(g);
         g2d.translate((double)this.camera.getX(), (double)this.camera.getY());
         this.hud.render(g);
         g.dispose();
         bs.show();
      }
   }

   public void run() {
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0D;
      double ns = 1.0E9D / amountOfTicks;
      double delta = 0.0D;
      long timer = System.currentTimeMillis();
      long now = System.nanoTime();
      int frames = 0;

      while(this.running) {
         now = System.nanoTime();
         delta += (double)(now - lastTime) / ns;

         for(lastTime = now; delta >= 1.0D; --delta) {
            this.update();
         }

         this.render();
         ++frames;
         if (System.currentTimeMillis() - timer > 1000L) {
            timer += 1000L;
            fps = frames;
            frames = 0;
         }
      }

      this.stop();
   }

   public Window getWindow() {
      return this.window;
   }

   public boolean isRunning() {
      return this.running;
   }
}
